package com.fn.modules.documents.service.dto;

import lombok.Data;

import java.sql.Timestamp;

import com.fn.annotation.Query;

/**
 * @author jie
 * @date 2019-11-11
 */
@Data
public class AuditChainQueryCriteria {

    // 精确
    @Query
    private Long id;

    // 精确
    @Query
    private Long jobId;

    // 精确
    @Query
    private Integer sorted;

    // 精确
    @Query
    private Integer source;

    // 精确
    @Query
    private Timestamp createTime;

    // 精确
    @Query
    private Timestamp updateTime;

    // 精确
    @Query
    private Integer deleted;

    @Query(propName = "name", joinName = "job", type = Query.Type.INNER_LIKE)
    private String jobName;
}