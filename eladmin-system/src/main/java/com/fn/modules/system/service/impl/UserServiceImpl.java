package com.fn.modules.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.fn.exception.EntityExistException;
import com.fn.exception.EntityNotFoundException;
import com.fn.modules.monitor.service.RedisService;
import com.fn.modules.system.domain.Dept;
import com.fn.modules.system.domain.Job;
import com.fn.modules.system.domain.Role;
import com.fn.modules.system.domain.User;
import com.fn.modules.system.repository.DeptRepository;
import com.fn.modules.system.repository.JobRepository;
import com.fn.modules.system.repository.UserRepository;
import com.fn.modules.system.service.UserService;
import com.fn.modules.system.service.dto.UserDTO;
import com.fn.modules.system.service.dto.UserQueryCriteria;
import com.fn.modules.system.service.mapper.UserMapper;
import com.fn.utils.*;
import com.mchange.v1.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author leo
 * @date 2019-09-23
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private RedisService redisService;

    @Override
    public Object queryAll(UserQueryCriteria criteria, Pageable pageable) {
        Page<User> page = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(userMapper::toDto));
    }

    @Override
    public UserDTO findById(long id) {
        Optional<User> user = userRepository.findById(id);
        ValidationUtil.isNull(user, "User", "id", id);
        return userMapper.toDto(user.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDTO create(User resources) {

        if (userRepository.findByUsername(resources.getUsername()) != null) {
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }

        if (userRepository.findByEmail(resources.getEmail()) != null) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }

        // 默认密码 123456，此密码是加密后的字符
        resources.setPassword("e10adc3949ba59abbe56e057f20f883e");
        resources.setAvatar("https://i.loli.net/2019/04/04/5ca5b971e1548.jpeg");
        return userMapper.toDto(userRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(User resources) {
        Optional<User> userOptional = userRepository.findById(resources.getId());
        ValidationUtil.isNull(userOptional, "User", "id", resources.getId());

        User user = userOptional.get();

        User user1 = userRepository.findByUsername(user.getUsername());
        User user2 = userRepository.findByEmail(user.getEmail());

        if (user1 != null && !user.getId().equals(user1.getId())) {
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }

        if (user2 != null && !user.getId().equals(user2.getId())) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }

        // 如果用户的角色改变了，需要手动清理下缓存
        if (!resources.getRoles().equals(user.getRoles())) {
            String key = "role::loadPermissionByUser:" + user.getUsername();
            redisService.delete(key);
            key = "role::findByUsers_Id:" + user.getId();
            redisService.delete(key);
        }

        user.setUsername(resources.getUsername());
        user.setEmail(resources.getEmail());
        user.setEnabled(resources.getEnabled());
        user.setRoles(resources.getRoles());
//        user.setDept(resources.getDept());
//        user.setJob(resources.getJob());
        user.setPhone(resources.getPhone());
        user.setDeptId(resources.getDeptId());
        user.setJobId(resources.getJobId());
        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO findByName(String userName) {
        User user = null;
        if (ValidationUtil.isEmail(userName)) {
            user = userRepository.findByEmail(userName);
        } else if (ValidationUtil.isPhone(userName)) {
            user = userRepository.findByPhone(userName);
        } else {
            user = userRepository.findByUsername(userName);
        }
        if (user == null) {
            throw new EntityNotFoundException(User.class, "name", userName);
        } else {
            return userMapper.toDto(user);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePass(String username, String pass) {
        userRepository.updatePass(username, pass, new Date());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAvatar(String username, String url) {
        userRepository.updateAvatar(username, url);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEmail(String username, String email) {
        userRepository.updateEmail(username, email);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity saveImportUser(List<String[]> usersList) {
        List<String> userNameList = new ArrayList<>();//保存返回未成功用户名
        String company;
        String username;
        String phone;
        String email;
        String deptName;
        String jobName;
        for (int i = 0; i < usersList.size(); i++) {
            User user = new User();
            // 公司
            company = usersList.get(i)[0];
            log.info("解析公司company[{}]:[{}]", i, company);
            if (usersList.get(i).length != 6) {
                userNameList.add("参数不合法！");
                continue;
            }
            if (StringUtils.isBlank(company)) {
                userNameList.add(company + ":格式错误!");
                continue;
            }
            List<Dept> companies = deptRepository.findAll();
            if (companies == null || companies.isEmpty()) {
                log.error("没有可用公司部门,请先设置公司部门！");
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            boolean flag = false;
            for (Dept dept : companies) {
                if (company.equals(dept.getName()) && dept.getPid() == 1 && dept.getEnabled()) {
                    user.setCompanyId(dept.getId());
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                userNameList.add(company + ":没有找到对应公司,请先设置公司！");
                continue;
            }
            // 用户名
            username = usersList.get(i)[1];
            log.info("解析用户名username[{}]:[{}]", i, username);
            if (StringUtils.isBlank(username)) {
                userNameList.add(username + ":不合法！");
                continue;
            }
            user.setUsername(username);
            // 手机号
            phone = usersList.get(i)[2];
            if (StringUtils.isBlank(phone) || !ValidateUtils.validateMobileNumber(phone)) {
                userNameList.add(username + ":格式错误!");
                continue;
            }
            user.setPhone(phone);
            // email
            email = usersList.get(i)[3];
            if (StringUtils.isBlank(email) || !ValidateUtils.validateEmail(email)) {
                userNameList.add(username + ":格式错误!");
                continue;
            }
            user.setEmail(email);
            // 部门
            deptName = usersList.get(i)[4];
            if (StringUtils.isBlank(deptName)) {
                userNameList.add(username + ":格式错误!");
                continue;
            }
            List<Dept> depts = deptRepository.findAll();
            if (depts == null || depts.isEmpty()) {
                log.error("没有可用部门,请先设置部门！");
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            flag = false;
            for (Dept dept : depts) {
                if (deptName.equals(dept.getName()) && user.getCompanyId() == dept.getPid() && dept.getEnabled()) {
                    user.setDeptId(dept.getId());
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                userNameList.add(username + ":没有找到对应部门,请先设置部门！");
                continue;
            }
            // 职位
            jobName = usersList.get(i)[5];
            if (StringUtils.isBlank(jobName)) {
                userNameList.add(username + ":格式错误!");
                continue;
            }
            List<Job> jobs = jobRepository.findAll();
            if (jobs == null || jobs.isEmpty()) {
                log.error("没有可用岗位,请先设置岗位！");
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            flag = false;
            for (Job job : jobs) {
                if (jobName.equals(job.getName())) {
                    user.setJobId(job.getId());
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                userNameList.add(username + ":没有找到对应岗位,请先设置岗位！");
                continue;
            }
            if (!userNameList.isEmpty()) {
                log.error("导入失败列表userNameList:{}" + userNameList);
            }
            user.setEnabled(false);
            // 设置默认角色——普通用户
            Role role = new Role();
            role.setId(2L);
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            userRepository.save(user);
        }
        if (CollectionUtil.isEmpty(userNameList)) {
            return new ResponseEntity(userNameList, HttpStatus.CHECKPOINT);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
