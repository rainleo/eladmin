package com.fn.modules.documents.service.dto;

import lombok.Data;

import java.sql.Timestamp;

import com.fn.annotation.Query;

/**
 * @author jie
 * @date 2019-11-04
 */
@Data
public class AccountingSubjectsQueryCriteria {

    // 精确
    @Query
    private Long id;

    // 精确
    @Query
    private String subjectCode;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String subjectName;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String auxiliaryAccountType;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String itemDetails;

    // 精确
    @Query
    private Timestamp createTime;

    // 精确
    @Query
    private Timestamp updateTime;

    // 精确
    @Query
    private Integer deleted;

    // 精确
    @Query
    private Long companyId;

    @Query(propName = "name", joinName = "company", type = Query.Type.INNER_LIKE)
    private String companyName;
}