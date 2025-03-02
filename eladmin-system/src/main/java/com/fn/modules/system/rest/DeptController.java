package com.fn.modules.system.rest;

import com.fn.aop.log.Log;
import com.fn.config.DataScope;
import com.fn.exception.BadRequestException;
import com.fn.modules.system.domain.Dept;
import com.fn.modules.system.domain.DeptDetail;
import com.fn.modules.system.service.DeptService;
import com.fn.modules.system.service.dto.DeptDTO;
import com.fn.modules.system.service.dto.DeptQueryCriteria;
import com.fn.utils.SecurityUtils;
import com.fn.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leo
 * @date 2019-03-25
 */
@RestController
@RequestMapping("api")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DataScope dataScope;

    private static final String ENTITY_NAME = "dept";

    @Log("查询部门")
    @GetMapping(value = "/dept")
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_SELECT','DEPT_ALL','DEPT_SELECT')")
    public ResponseEntity getDepts(DeptQueryCriteria criteria) {
        // 数据权限
        criteria.setIds(dataScope.getDeptIds());
        List<DeptDTO> deptDTOS = deptService.queryAll(criteria);
        if (StringUtils.isNotBlank(criteria.getName())) {
            // 根据部门或公司名查询
            deptDTOS.stream().filter(deptDTO -> {
                DeptQueryCriteria deptQueryCriteria = new DeptQueryCriteria();
                deptQueryCriteria.setPid(deptDTO.getId());
                deptDTO.setChildren(deptService.queryAll(deptQueryCriteria));
                return true;
            }).collect(Collectors.toList());
        }
        return new ResponseEntity(deptService.buildTree(deptDTOS), HttpStatus.OK);
    }

    @Log("新增部门")
    @PostMapping(value = "/dept")
    @PreAuthorize("hasAnyRole('ADMIN','DEPT_ALL','DEPT_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Dept resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        return new ResponseEntity(deptService.create(resources), HttpStatus.CREATED);
    }

    @Log("修改部门")
    @PutMapping(value = "/dept")
    @PreAuthorize("hasAnyRole('ADMIN','DEPT_ALL','DEPT_EDIT')")
    public ResponseEntity update(@Validated(Dept.Update.class) @RequestBody Dept resources) {
        deptService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除部门")
    @DeleteMapping(value = "/dept/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DEPT_ALL','DEPT_DELETE')")
    public ResponseEntity delete(@PathVariable Long id) {
        deptService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }




    @GetMapping(value = "/appDeptQuery")
    public ResponseEntity appDeptQuery(DeptQueryCriteria criteria) {
      return    getDepts(criteria);
    }


    @PostMapping(value = "/appDeptAdd")
    public ResponseEntity appDeptAdd(@Validated @RequestBody Dept resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        resources.setCreateTime(new Timestamp(System.currentTimeMillis()));
        resources.setCreatedBy(SecurityUtils.getUserId());
        List<DeptDetail> deptDetailList = resources.getDeptDetailList().stream().map(e->{
            DeptDetail deptDetail = new DeptDetail();
            deptDetail.setDeptId(e.getDeptId());
            deptDetail.setAttachment(e.getAttachment());
            deptDetail.setName(e.getName());
            deptDetail.setBucket(e.getBucket());
            deptDetail.setSize(e.getSize());
            deptDetail.setType(e.getType());
            deptDetail.setCreateTime(new Timestamp(new java.util.Date().getTime()));
            deptDetail.setUpdateTime(new Timestamp(new java.util.Date().getTime()));
            deptDetail.setDeleted(0);
            return  deptDetail;
        }).collect(Collectors.toList());
        resources.setDeptDetailList(deptDetailList);
        return new ResponseEntity(deptService.create(resources), HttpStatus.CREATED);
    }
}