package com.fn.modules.documents.rest;

import com.fn.aop.log.Log;
import com.fn.modules.documents.domain.AccountingSubjects;
import com.fn.modules.documents.service.AccountingSubjectsService;
import com.fn.modules.documents.service.dto.AccountingSubjectsQueryCriteria;
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
* @date 2019-11-04
*/
@Api(tags = "AccountingSubjects管理")
@RestController
@RequestMapping("api")
public class AccountingSubjectsController {

    @Autowired
    private AccountingSubjectsService accountingSubjectsService;

    @Log("查询AccountingSubjects")
    @ApiOperation(value = "查询AccountingSubjects")
    @GetMapping(value = "/accountingSubjects")
    @PreAuthorize("hasAnyRole('ADMIN','ACCOUNTINGSUBJECTS_ALL','ACCOUNTINGSUBJECTS_SELECT')")
    public ResponseEntity getAccountingSubjectss(AccountingSubjectsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(accountingSubjectsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增AccountingSubjects")
    @ApiOperation(value = "新增AccountingSubjects")
    @PostMapping(value = "/accountingSubjects")
    @PreAuthorize("hasAnyRole('ADMIN','ACCOUNTINGSUBJECTS_ALL','ACCOUNTINGSUBJECTS_CREATE')")
    public ResponseEntity create(@Validated @RequestBody AccountingSubjects resources){
        resources.setDeleted(0);
        return new ResponseEntity(accountingSubjectsService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改AccountingSubjects")
    @ApiOperation(value = "修改AccountingSubjects")
    @PutMapping(value = "/accountingSubjects")
    @PreAuthorize("hasAnyRole('ADMIN','ACCOUNTINGSUBJECTS_ALL','ACCOUNTINGSUBJECTS_EDIT')")
    public ResponseEntity update(@Validated @RequestBody AccountingSubjects resources){
        accountingSubjectsService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除AccountingSubjects")
    @ApiOperation(value = "删除AccountingSubjects")
    @DeleteMapping(value = "/accountingSubjects/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ACCOUNTINGSUBJECTS_ALL','ACCOUNTINGSUBJECTS_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        accountingSubjectsService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}