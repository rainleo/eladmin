package com.fn.modules.documents.rest;
import com.alibaba.fastjson.JSON;
import com.fn.modules.system.domain.Dept;
import java.sql.Timestamp;
import com.fn.modules.system.domain.User;

import com.fn.aop.log.Log;
import com.fn.modules.documents.domain.ApplicationDocuments;
import com.fn.modules.documents.domain.DocumentReviewer;
import com.fn.modules.documents.service.ApplicationDocumentsService;
import com.fn.modules.documents.service.dto.ApplicationDocumentsQueryCriteria;
import com.fn.utils.SecurityUtils;
import com.fn.utils.twitter.SnowflakeIdUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
* @author jie
* @date 2019-11-04
*/
@Api(tags = "ApplicationDocuments管理")
@RestController
@RequestMapping("api")
@Log4j2
public class ApplicationDocumentsController {

    @Autowired
    private ApplicationDocumentsService applicationDocumentsService;

    @Log("查询ApplicationDocuments")
    @ApiOperation(value = "查询ApplicationDocuments")
    @GetMapping(value = "/applicationDocuments")
    @PreAuthorize("hasAnyRole('ADMIN','APPLICATIONDOCUMENTS_ALL','APPLICATIONDOCUMENTS_SELECT')")
    public ResponseEntity getApplicationDocumentss(ApplicationDocumentsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(applicationDocumentsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增ApplicationDocuments")
    @ApiOperation(value = "新增ApplicationDocuments")
    @PostMapping(value = "/applicationDocuments")
    @PreAuthorize("hasAnyRole('ADMIN','APPLICATIONDOCUMENTS_ALL','APPLICATIONDOCUMENTS_CREATE')")
    public ResponseEntity create(@Validated @RequestBody ApplicationDocuments resources){
        resources.setDeleted(0);
        //生成单据号(雪花算法)
        resources.setApplicationNo(SnowflakeIdUtils.nextId());
        return new ResponseEntity(applicationDocumentsService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改ApplicationDocuments")
    @ApiOperation(value = "修改ApplicationDocuments")
    @PutMapping(value = "/applicationDocuments")
    @PreAuthorize("hasAnyRole('ADMIN','APPLICATIONDOCUMENTS_ALL','APPLICATIONDOCUMENTS_EDIT')")
    public ResponseEntity update(@Validated @RequestBody ApplicationDocuments resources){
        applicationDocumentsService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除ApplicationDocuments")
    @ApiOperation(value = "删除ApplicationDocuments")
    @DeleteMapping(value = "/applicationDocuments/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','APPLICATIONDOCUMENTS_ALL','APPLICATIONDOCUMENTS_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        applicationDocumentsService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/appAapplicationDocuments")
    public ResponseEntity appAapplicationDocuments(@Validated @RequestBody ApplicationDocuments resources){
        resources.setDeleted(0);
        //生成单据号(雪花算法)
        resources.setApplicationNo(SnowflakeIdUtils.nextId());
        resources.setCreateTime(new Timestamp(System.currentTimeMillis()));
        resources.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        resources.setUserId(SecurityUtils.getUserId());
        resources.setStatus(0);
        List<DocumentReviewer> reviewerList =resources.getReviewerList().stream().map(e->{
            DocumentReviewer documentReviewer = new DocumentReviewer();
            documentReviewer.setUserId(e.getUserId());
            documentReviewer.setCompanyId(resources.getCompanyId());
            documentReviewer.setAuditStatus(0);
            documentReviewer.setSource(0);
            documentReviewer.setCreateTime(new Timestamp(new java.util.Date().getTime()));
            documentReviewer.setUpdateTime(new Timestamp(new java.util.Date().getTime()));
            documentReviewer.setDeleted(0);
            return documentReviewer;
        }).collect(Collectors.toList());
        resources.setReviewerList(reviewerList);
        log.error(JSON.toJSONString(reviewerList));
        return new ResponseEntity(applicationDocumentsService.create(resources),HttpStatus.CREATED);
    }

}