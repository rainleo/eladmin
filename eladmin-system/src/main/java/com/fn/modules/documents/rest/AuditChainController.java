package com.fn.modules.documents.rest;

import com.fn.aop.log.Log;
import com.fn.modules.documents.domain.AuditChain;
import com.fn.modules.documents.service.AuditChainService;
import com.fn.modules.documents.service.dto.AuditChainQueryCriteria;
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
@Api(tags = "AuditChain管理")
@RestController
@RequestMapping("api")
public class AuditChainController {

    @Autowired
    private AuditChainService auditChainService;

    @Log("查询AuditChain")
    @ApiOperation(value = "查询AuditChain")
    @GetMapping(value = "/auditChain")
    @PreAuthorize("hasAnyRole('ADMIN','AUDITCHAIN_ALL','AUDITCHAIN_SELECT')")
    public ResponseEntity getAuditChains(AuditChainQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(auditChainService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增AuditChain")
    @ApiOperation(value = "新增AuditChain")
    @PostMapping(value = "/auditChain")
    @PreAuthorize("hasAnyRole('ADMIN','AUDITCHAIN_ALL','AUDITCHAIN_CREATE')")
    public ResponseEntity create(@Validated @RequestBody AuditChain resources){
        resources.setDeleted(0);
        return new ResponseEntity(auditChainService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改AuditChain")
    @ApiOperation(value = "修改AuditChain")
    @PutMapping(value = "/auditChain")
    @PreAuthorize("hasAnyRole('ADMIN','AUDITCHAIN_ALL','AUDITCHAIN_EDIT')")
    public ResponseEntity update(@Validated @RequestBody AuditChain resources){
        auditChainService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除AuditChain")
    @ApiOperation(value = "删除AuditChain")
    @DeleteMapping(value = "/auditChain/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','AUDITCHAIN_ALL','AUDITCHAIN_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        auditChainService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}