package com.fn.modules.documents.service.dto;

import com.fn.modules.system.domain.User;
import lombok.Data;

import java.sql.Timestamp;

import com.fn.annotation.Query;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author jie
 * @date 2019-11-10
 */
@Data
public class TodoListQueryCriteria {

    // 精确
    @Query
    private Long id;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String todoAbstract;

    // 精确
    @Query
    private Timestamp expectedCompletionTime;

    // 精确
    @Query
    private Long copyPersonId;

    // 精确
    @Query
    private Long assistantId;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String content;

    // 精确
    @Query
    private Timestamp createTime;

    // 精确
    @Query
    private Timestamp updateTime;

    // 精确
    @Query
    private Integer deleted;

    @Query(propName = "username", joinName = "copyPerson")
    private String copyPersonName;

    @Query(propName = "username", joinName = "assistantPerson")
    private String assistantPersonName;
}