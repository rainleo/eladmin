package com.fn.modules.system.service.impl;

import com.fn.domain.QiniuContent;
import com.fn.exception.BadRequestException;
import com.fn.modules.system.domain.Dept;
import com.fn.modules.system.domain.DeptDetail;
import com.fn.modules.system.repository.DeptDetailRepository;
import com.fn.modules.system.repository.DeptRepository;
import com.fn.modules.system.service.DeptService;
import com.fn.modules.system.service.dto.DeptDTO;
import com.fn.modules.system.service.dto.DeptQueryCriteria;
import com.fn.modules.system.service.mapper.DeptMapper;
import com.fn.service.QiNiuService;
import com.fn.utils.QueryHelp;
import com.fn.utils.StringUtils;
import com.fn.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author leo
 * @date 2019-03-25
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private DeptDetailRepository deptDetailRepository;

    @Autowired
    private QiNiuService qiNiuService;

    @Override
    public List<DeptDTO> queryAll(DeptQueryCriteria criteria) {
        return deptMapper.toDto(deptRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public DeptDTO findById(Long id) {
        Optional<Dept> dept = deptRepository.findById(id);
        ValidationUtil.isNull(dept, "Dept", "id", id);
        return deptMapper.toDto(dept.get());
    }

    @Override
    public List<Dept> findByPid(long pid) {
        return deptRepository.findByPid(pid);
    }

    @Override
    public Set<Dept> findByRoleIds(Long id) {
        return deptRepository.findByRoles_Id(id);
    }

    @Override
    public Object buildTree(List<DeptDTO> deptDTOS) {
        Set<DeptDTO> trees = new LinkedHashSet<>();
        Set<DeptDTO> depts = new LinkedHashSet<>();
        List<String> deptNames = deptDTOS.stream().map(DeptDTO::getName).collect(Collectors.toList());
        Boolean isChild;
        for (DeptDTO deptDTO : deptDTOS) {
            isChild = false;
            if ("0".equals(deptDTO.getPid().toString())) {
                trees.add(deptDTO);
            }
            for (DeptDTO it : deptDTOS) {
                if (it.getPid().equals(deptDTO.getId())) {
                    isChild = true;
                    if (deptDTO.getChildren() == null) {
                        deptDTO.setChildren(new ArrayList<DeptDTO>());
                    }
                    deptDTO.getChildren().add(it);
                }
            }
            if (isChild)
                depts.add(deptDTO);
            else if (!deptNames.contains(deptRepository.findNameById(deptDTO.getPid())))
                depts.add(deptDTO);
        }

        if (CollectionUtils.isEmpty(trees)) {
            trees = depts;
        }

        Integer totalElements = deptDTOS != null ? deptDTOS.size() : 0;

        Map map = new HashMap();
        map.put("totalElements", totalElements);
        map.put("content", CollectionUtils.isEmpty(trees) ? deptDTOS : trees);
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeptDTO create(Dept resources) {
        // 附件表先删后增
        updateDeptDetail(resources);
        //入库申请单据表
        return deptMapper.toDto(deptRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Dept resources) {
        if (resources.getId().equals(resources.getPid())) {
            throw new BadRequestException("上级不能为自己");
        }
        Optional<Dept> optionalDept = deptRepository.findById(resources.getId());
        ValidationUtil.isNull(optionalDept, "Dept", "id", resources.getId());
        Dept dept = optionalDept.get();
        resources.setId(dept.getId());
        // 更新附件(必须优先更新附表，否则主表更新，数据清零)
        updateDeptDetail(resources);
        deptRepository.save(resources);
    }

    /**
     * 更新附件表
     *
     * @param resources
     */
    private void updateDeptDetail(Dept resources) {
        // 1.根据附件url删除附件表
        List<DeptDetail> deptDetailList = resources.getDeptDetailList();//需要入库的
        if (deptDetailList == null || deptDetailList.isEmpty()) {
            return;
        }
        // 未提交服务器，附件不作更新
        for (DeptDetail deptDetail : deptDetailList) {
            if (StringUtils.isEmpty(deptDetail.getAttachment())) return;
        }
        // 要删除的
        List<DeptDetail> deleteDeptDetailList = deptDetailRepository.findByDeptId(resources.getId());
        for (DeptDetail deptDetail : deleteDeptDetailList) {
            QiniuContent qiniuContent = new QiniuContent();
            qiniuContent.setKey(deptDetail.getName());
            qiniuContent.setBucket(deptDetail.getBucket());
            // 删除七牛云
            qiNiuService.delete(qiniuContent, qiNiuService.find());
        }
        // 删除本地附件
        deptDetailRepository.deleteInBatch(deleteDeptDetailList);
        // 2.新增附件
        deptDetailList.stream().filter(deptDetail -> {
            deptDetail.setDeleted(0);
            deptDetail.setDeptId(resources.getId());
            return true;
        }).collect(Collectors.toList());
        deptDetailRepository.saveAll(deptDetailList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        deptRepository.deleteById(id);
    }
}