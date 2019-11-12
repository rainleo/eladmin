package com.fn.modules.documents.repository;

import com.fn.modules.documents.domain.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-11-10
*/
public interface TodoListRepository extends JpaRepository<TodoList, Long>, JpaSpecificationExecutor {
}