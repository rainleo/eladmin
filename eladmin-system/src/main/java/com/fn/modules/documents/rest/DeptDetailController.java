package com.fn.modules.documents.rest;

import com.fn.aop.log.Log;
import com.fn.modules.documents.domain.DeptDetail;
import com.fn.modules.documents.service.DeptDetailService;
import com.fn.modules.documents.service.dto.DeptDetailQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author jie
* @date 2019-11-11
*/
@Api(tags = "DeptDetail管理")
@RestController
@RequestMapping("api")
public class DeptDetailController {

    @Autowired
    private DeptDetailService deptDetailService;

    @Log("查询DeptDetail")
    @ApiOperation(value = "查询DeptDetail")
    @GetMapping(value = "/deptDetail")
    @PreAuthorize("hasAnyRole('ADMIN','DEPTDETAIL_ALL','DEPTDETAIL_SELECT')")
    public ResponseEntity getDeptDetails(DeptDetailQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(deptDetailService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增DeptDetail")
    @ApiOperation(value = "新增DeptDetail")
    @PostMapping(value = "/deptDetail")
    @PreAuthorize("hasAnyRole('ADMIN','DEPTDETAIL_ALL','DEPTDETAIL_CREATE')")
    public ResponseEntity create(@Validated @RequestBody DeptDetail resources){
        return new ResponseEntity(deptDetailService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改DeptDetail")
    @ApiOperation(value = "修改DeptDetail")
    @PutMapping(value = "/deptDetail")
    @PreAuthorize("hasAnyRole('ADMIN','DEPTDETAIL_ALL','DEPTDETAIL_EDIT')")
    public ResponseEntity update(@Validated @RequestBody DeptDetail resources){
        deptDetailService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除DeptDetail")
    @ApiOperation(value = "删除DeptDetail")
    @DeleteMapping(value = "/deptDetail/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DEPTDETAIL_ALL','DEPTDETAIL_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        deptDetailService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}