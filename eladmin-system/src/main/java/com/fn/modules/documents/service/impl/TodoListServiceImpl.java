package com.fn.modules.documents.service.impl;

import com.fn.modules.documents.domain.TodoList;
import com.fn.modules.documents.repository.TodoListRepository;
import com.fn.modules.documents.service.TodoListService;
import com.fn.modules.documents.service.dto.TodoListDTO;
import com.fn.modules.documents.service.dto.TodoListQueryCriteria;
import com.fn.modules.documents.service.mapper.TodoListMapper;
import com.fn.modules.security.security.JwtUser;
import com.fn.utils.PageUtil;
import com.fn.utils.QueryHelp;
import com.fn.utils.SecurityUtils;
import com.fn.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author jie
 * @date 2019-11-10
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TodoListServiceImpl implements TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private TodoListMapper todoListMapper;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    public Object queryAll(TodoListQueryCriteria criteria, Pageable pageable) {
        Page<TodoList> page = todoListRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(todoListMapper::toDto));
    }

    @Override
    public Object queryAll(TodoListQueryCriteria criteria) {
        return todoListMapper.toDto(todoListRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public TodoListDTO findById(Long id) {
        Optional<TodoList> todoList = todoListRepository.findById(id);
        ValidationUtil.isNull(todoList, "TodoList", "id", id);
        return todoListMapper.toDto(todoList.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TodoListDTO create(TodoList resources) {
        // 加入companyId
        if (resources.getCompanyId() == null) {
            JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
            resources.setCompanyId(jwtUser.getCompanyId());
        }
        return todoListMapper.toDto(todoListRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(TodoList resources) {
        Optional<TodoList> optionalTodoList = todoListRepository.findById(resources.getId());
        ValidationUtil.isNull(optionalTodoList, "TodoList", "id", resources.getId());
        TodoList todoList = optionalTodoList.get();
        todoList.copy(resources);
        todoListRepository.save(todoList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        todoListRepository.deleteById(id);
    }
}