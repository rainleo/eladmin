package com.fn.modules.documents.rest;

import com.fn.aop.log.Log;
import com.fn.modules.documents.domain.TodoList;
import com.fn.modules.documents.service.TodoListService;
import com.fn.modules.documents.service.dto.TodoListQueryCriteria;
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
* @date 2019-11-10
*/
@Api(tags = "TodoList管理")
@RestController
@RequestMapping("api")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @Log("查询TodoList")
    @ApiOperation(value = "查询TodoList")
    @GetMapping(value = "/todoList")
    @PreAuthorize("hasAnyRole('ADMIN','TODOLIST_ALL','TODOLIST_SELECT')")
    public ResponseEntity getTodoLists(TodoListQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(todoListService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增TodoList")
    @ApiOperation(value = "新增TodoList")
    @PostMapping(value = "/todoList")
    @PreAuthorize("hasAnyRole('ADMIN','TODOLIST_ALL','TODOLIST_CREATE')")
    public ResponseEntity create(@Validated @RequestBody TodoList resources){
        resources.setDeleted(0);
        return new ResponseEntity(todoListService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改TodoList")
    @ApiOperation(value = "修改TodoList")
    @PutMapping(value = "/todoList")
    @PreAuthorize("hasAnyRole('ADMIN','TODOLIST_ALL','TODOLIST_EDIT')")
    public ResponseEntity update(@Validated @RequestBody TodoList resources){
        todoListService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除TodoList")
    @ApiOperation(value = "删除TodoList")
    @DeleteMapping(value = "/todoList/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TODOLIST_ALL','TODOLIST_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        todoListService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}