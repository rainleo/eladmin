package com.fn.modules.documents.rest;

import com.fn.aop.log.Log;
import com.fn.modules.documents.domain.ApplicationDocuments;
import com.fn.modules.documents.service.ApplicationDocumentsService;
import com.fn.modules.documents.service.dto.ApplicationDocumentsQueryCriteria;
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
@Api(tags = "ApplicationDocuments管理")
@RestController
@RequestMapping("api")
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


    @Log("查询ApplicationDocuments(Post)")
    @ApiOperation(value = "查询ApplicationDocuments(Post)")
    @PostMapping(value = "/applicationDocumentsPost")
    @PreAuthorize("hasAnyRole('ADMIN','APPLICATIONDOCUMENTS_ALL','APPLICATIONDOCUMENTS_SELECT')")
    public ResponseEntity getApplicationDocumentssPost(@RequestBody ApplicationDocumentsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(applicationDocumentsService.queryAll(criteria,pageable),HttpStatus.OK);
    }



    @Log("新增ApplicationDocuments")
    @ApiOperation(value = "新增ApplicationDocuments")
    @PostMapping(value = "/applicationDocuments")
    @PreAuthorize("hasAnyRole('ADMIN','APPLICATIONDOCUMENTS_ALL','APPLICATIONDOCUMENTS_CREATE')")
    public ResponseEntity create(@Validated @RequestBody ApplicationDocuments resources){
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
}