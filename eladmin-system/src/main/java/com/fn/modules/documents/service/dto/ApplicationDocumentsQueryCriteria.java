package com.fn.modules.documents.service.dto;

import com.fn.modules.documents.domain.AccountingSubjects;
import com.fn.modules.system.domain.Dept;
import com.fn.modules.system.domain.User;
import com.fn.modules.system.service.dto.DeptQueryCriteria;
import com.fn.modules.system.service.dto.UserQueryCriteria;
import com.sun.deploy.security.ValidationState;
import lombok.Data;

import java.sql.Timestamp;
import java.math.BigDecimal;

import com.fn.annotation.Query;

import javax.persistence.*;

/**
 * @author jie
 * @date 2019-11-04
 */
@Data
public class ApplicationDocumentsQueryCriteria {

    // 精确
    @Query
    private Long id;

    // 精确
    @Query
    private String applicationNo;

    // 审核状态
    @Query
    private Integer status;

    // 精确
    @Query
    private Long deptId;

    // 精确
    @Query
    private Long userId;

    // 精确
    @Query
    private Long accountingSubjectsId;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String applicationDescription;

    // 精确
    @Query
    private BigDecimal amount;

    // 精确
    @Query
    private Timestamp createTime;

    // 精确
    @Query
    private Timestamp updatetime;

    // 精确
    @Query
    private Integer deleted;

    @Query(propName = "username", joinName = "user")
    private String username;

    @Query(propName = "name", joinName = "dept")
    private String name;

    /**
     * 参数说明：propName是指AccountingSubjects对象中的“属性”名,
     *          joinName指的是主对象ApplicationDocuments中对应的“属性"名;
     *          参数String subjectName只需与前端保持一致，用于接收参数
     */
    @Query(propName = "subjectName", joinName = "accountingSubjects", type = Query.Type.INNER_LIKE)
    private String subjectName;

    @Query(propName = "source", type = Query.Type.EQUAL, joinName = "reviewerList", join = Query.Join.LEFT)
    private String source;


}