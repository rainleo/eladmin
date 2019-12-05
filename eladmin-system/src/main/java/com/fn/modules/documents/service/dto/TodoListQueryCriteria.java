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

    // 精确
    @Query
    private Long user_id;

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

    // 状态
    @Query
    private Integer status;

    // 精确
    @Query
    private Timestamp createTime;

    // 精确
    @Query
    private Timestamp updateTime;

    // 精确
    @Query
    private Integer deleted;

    @Query(propName = "username", joinName = "copyPerson", type = Query.Type.INNER_LIKE)
    private String copyPersonName;

    @Query(propName = "username", joinName = "assistantPerson", type = Query.Type.INNER_LIKE)
    private String assistantPersonName;

    // 精确
    @Query
    private Long companyId;

    @Query(propName = "name", joinName = "company", type = Query.Type.INNER_LIKE)
    private String companyName;
}