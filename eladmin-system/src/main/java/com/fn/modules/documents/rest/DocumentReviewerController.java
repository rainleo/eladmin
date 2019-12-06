package com.fn.modules.documents.rest;

import com.fn.aop.log.Log;
import com.fn.modules.documents.domain.DocumentReviewer;
import com.fn.modules.documents.service.DocumentReviewerService;
import com.fn.modules.documents.service.dto.DocumentReviewerQueryCriteria;
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
* @date 2019-11-13
*/
@Api(tags = "DocumentReviewer管理")
@RestController
@RequestMapping("api")
public class DocumentReviewerController {

    @Autowired
    private DocumentReviewerService documentReviewerService;

    @Log("查询DocumentReviewer")
    @ApiOperation(value = "查询DocumentReviewer")
    @GetMapping(value = "/documentReviewer")
    @PreAuthorize("hasAnyRole('ADMIN','DOCUMENTREVIEWER_ALL','DOCUMENTREVIEWER_SELECT')")
    public ResponseEntity getDocumentReviewers(DocumentReviewerQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(documentReviewerService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增DocumentReviewer")
    @ApiOperation(value = "新增DocumentReviewer")
    @PostMapping(value = "/documentReviewer")
    @PreAuthorize("hasAnyRole('ADMIN','DOCUMENTREVIEWER_ALL','DOCUMENTREVIEWER_CREATE')")
    public ResponseEntity create(@Validated @RequestBody DocumentReviewer resources){
        resources.setDeleted(0);
        return new ResponseEntity(documentReviewerService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改DocumentReviewer")
    @ApiOperation(value = "修改DocumentReviewer")
    @PutMapping(value = "/documentReviewer")
    @PreAuthorize("hasAnyRole('ADMIN','DOCUMENTREVIEWER_ALL','DOCUMENTREVIEWER_EDIT')")
    public ResponseEntity update(@Validated @RequestBody DocumentReviewer resources){
        documentReviewerService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除DocumentReviewer")
    @ApiOperation(value = "删除DocumentReviewer")
    @DeleteMapping(value = "/documentReviewer/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DOCUMENTREVIEWER_ALL','DOCUMENTREVIEWER_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        documentReviewerService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("查询所有单据Documents")
    @ApiOperation(value = "查询所有单据Documents")
    @GetMapping(value = "/documentReviewer/allDocuments")
    @PreAuthorize("hasAnyRole('ADMIN','DOCUMENTREVIEWER_ALL','DOCUMENTREVIEWER_SELECT')")
    public ResponseEntity getAllDocuments(DocumentReviewerQueryCriteria criteria){
        return new ResponseEntity(documentReviewerService.queryAllDocuments(criteria),HttpStatus.OK);
    }

    @Log("查询所有禁用Sorted")
    @ApiOperation(value = "查询所有禁用Sorted")
    @GetMapping(value = "/documentReviewer/allDisableSorted")
    @PreAuthorize("hasAnyRole('ADMIN','DOCUMENTREVIEWER_ALL','DOCUMENTREVIEWER_SELECT')")
    public ResponseEntity getDisableSorted(DocumentReviewerQueryCriteria criteria){
        return new ResponseEntity(documentReviewerService.getDisableSorted(criteria),HttpStatus.OK);
    }

}