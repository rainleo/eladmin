package com.fn.modules.documents.rest;

import com.fn.aop.log.Log;
import com.fn.modules.documents.domain.DocumentReviewer;
import com.fn.modules.documents.domain.ReimbursementDocuments;
import com.fn.modules.documents.service.ReimbursementDocumentsService;
import com.fn.modules.documents.service.dto.ReimbursementDocumentsQueryCriteria;
import com.fn.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author jie
* @date 2019-11-04
*/
@Api(tags = "ReimbursementDocuments管理")
@RestController
@RequestMapping("api")
public class ReimbursementDocumentsController {

    @Autowired
    private ReimbursementDocumentsService reimbursementDocumentsService;

    @Log("查询ReimbursementDocuments")
    @ApiOperation(value = "查询ReimbursementDocuments")
    @GetMapping(value = "/reimbursementDocuments")
    @PreAuthorize("hasAnyRole('ADMIN','REIMBURSEMENTDOCUMENTS_ALL','REIMBURSEMENTDOCUMENTS_SELECT')")
    public ResponseEntity getReimbursementDocumentss(ReimbursementDocumentsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(reimbursementDocumentsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增ReimbursementDocuments")
    @ApiOperation(value = "新增ReimbursementDocuments")
    @PostMapping(value = "/reimbursementDocuments")
    @PreAuthorize("hasAnyRole('ADMIN','REIMBURSEMENTDOCUMENTS_ALL','REIMBURSEMENTDOCUMENTS_CREATE')")
    public ResponseEntity create(@Validated @RequestBody ReimbursementDocuments resources){
        resources.setDeleted(0);
        return new ResponseEntity(reimbursementDocumentsService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改ReimbursementDocuments")
    @ApiOperation(value = "修改ReimbursementDocuments")
    @PutMapping(value = "/reimbursementDocuments")
    @PreAuthorize("hasAnyRole('ADMIN','REIMBURSEMENTDOCUMENTS_ALL','REIMBURSEMENTDOCUMENTS_EDIT')")
    public ResponseEntity update(@Validated @RequestBody ReimbursementDocuments resources){
        reimbursementDocumentsService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除ReimbursementDocuments")
    @ApiOperation(value = "删除ReimbursementDocuments")
    @DeleteMapping(value = "/reimbursementDocuments/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','REIMBURSEMENTDOCUMENTS_ALL','REIMBURSEMENTDOCUMENTS_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        reimbursementDocumentsService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping(value = "/appReimbursementDocuments")
    public ResponseEntity appReimbursementDocuments(@Validated @RequestBody ReimbursementDocuments resources){
        resources.setDeleted(0);
        resources.setCreateTime(new Timestamp(new java.util.Date().getTime()));
        resources.setUpdateTime(new Timestamp(new java.util.Date().getTime()));
        resources.setUserId(SecurityUtils.getUserId());
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

        return new ResponseEntity(reimbursementDocumentsService.create(resources),HttpStatus.CREATED);
    }
    @GetMapping(value = "/appReimbursementDocumentsQuery")
    public ResponseEntity appReimbursementDocumentsQuery(ReimbursementDocumentsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(reimbursementDocumentsService.queryAll(criteria,pageable),HttpStatus.OK);
    }
}