package com.fn.modules.documents.service.dto;

import com.fn.annotation.Query;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * @author jie
 * @date 2019-11-04
 */
@Data
public class ApplicationDocumentsQueryCriteriaApprove extends ApplicationDocumentsQueryCriteria {

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
    @Query(propName = "userId", type = Query.Type.EQUAL, joinName = "reviewerList", join = Query.Join.LEFT)
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
    private Timestamp updateTime;

    // 精确
    @Query
    private Integer deleted;

    @Query(propName = "username", joinName = "user")
    private String userName;

    @Query(propName = "name", joinName = "dept")
    private String deptName;

    /**
     * 参数说明：propName是指AccountingSubjects对象中的“属性”名,
     * joinName指的是主对象ApplicationDocuments中对应的“属性"名;
     * 参数String subjectName只需与前端保持一致，用于接收参数
     */
    @Query(propName = "subjectName", joinName = "accountingSubjects", type = Query.Type.INNER_LIKE)
    private String subjectName;

    @Query(propName = "source", type = Query.Type.EQUAL, joinName = "reviewerList", join = Query.Join.LEFT)
    private String source;

    // 精确
    @Query
    private Long companyId;

    @Query(propName = "name", joinName = "company", type = Query.Type.INNER_LIKE)
    private String companyName;
}