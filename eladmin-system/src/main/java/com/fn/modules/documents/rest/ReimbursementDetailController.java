package com.fn.modules.documents.rest;

import com.fn.aop.log.Log;
import com.fn.modules.documents.domain.ReimbursementDetail;
import com.fn.modules.documents.service.ReimbursementDetailService;
import com.fn.modules.documents.service.dto.ReimbursementDetailQueryCriteria;
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
* @date 2019-11-18
*/
@Api(tags = "ReimbursementDetail管理")
@RestController
@RequestMapping("api")
public class ReimbursementDetailController {

    @Autowired
    private ReimbursementDetailService reimbursementDetailService;

    @Log("查询ReimbursementDetail")
    @ApiOperation(value = "查询ReimbursementDetail")
    @GetMapping(value = "/reimbursementDetail")
    @PreAuthorize("hasAnyRole('ADMIN','REIMBURSEMENTDETAIL_ALL','REIMBURSEMENTDETAIL_SELECT')")
    public ResponseEntity getReimbursementDetails(ReimbursementDetailQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(reimbursementDetailService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增ReimbursementDetail")
    @ApiOperation(value = "新增ReimbursementDetail")
    @PostMapping(value = "/reimbursementDetail")
    @PreAuthorize("hasAnyRole('ADMIN','REIMBURSEMENTDETAIL_ALL','REIMBURSEMENTDETAIL_CREATE')")
    public ResponseEntity create(@Validated @RequestBody ReimbursementDetail resources){
        return new ResponseEntity(reimbursementDetailService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改ReimbursementDetail")
    @ApiOperation(value = "修改ReimbursementDetail")
    @PutMapping(value = "/reimbursementDetail")
    @PreAuthorize("hasAnyRole('ADMIN','REIMBURSEMENTDETAIL_ALL','REIMBURSEMENTDETAIL_EDIT')")
    public ResponseEntity update(@Validated @RequestBody ReimbursementDetail resources){
        reimbursementDetailService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除ReimbursementDetail")
    @ApiOperation(value = "删除ReimbursementDetail")
    @DeleteMapping(value = "/reimbursementDetail/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','REIMBURSEMENTDETAIL_ALL','REIMBURSEMENTDETAIL_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        reimbursementDetailService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}