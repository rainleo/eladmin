package com.fn.modules.documents.service.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.math.BigDecimal;

import com.fn.annotation.Query;

/**
 * @author jie
 * @date 2019-11-04
 */
@Data
public class ReimbursementDocumentsQueryCriteria {

    // 精确
    @Query
    private Long id;

    // 精确
    @Query
    private String reimbursementNo;

    // 审核状态
    @Query
    private Integer status;

    // 精确
    @Query
    private Long deptId;

    // 精确
    @Query
    private Long userId;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String reimbursementAbstract;

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

    @Query(propName = "name", joinName = "dept")
    private String deptName;

    @Query(propName = "username", joinName = "user")
    private String userName;

    @Query(propName = "source", type = Query.Type.EQUAL, joinName = "reviewerList", join = Query.Join.LEFT)
    private String source;

    @Query(propName = "attachment", type = Query.Type.EQUAL, joinName = "reimbursementDetailList", join = Query.Join.LEFT)
    private String attachment;
}