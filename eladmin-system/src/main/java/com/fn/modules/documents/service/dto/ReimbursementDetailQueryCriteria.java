package com.fn.modules.documents.service.dto;

import lombok.Data;

import java.sql.Timestamp;

import com.fn.annotation.Query;

/**
 * @author jie
 * @date 2019-11-18
 */
@Data
public class ReimbursementDetailQueryCriteria {

    // 精确
    @Query
    private Long id;

    // 精确
    @Query
    private Long reimbursementDocumentsId;

//    // 精确
//    @Query
//    private String attachment;

    @Query
    private String name;

    @Query
    private String bucket;

    @Query
    private String size;

    @Query
    private String type;

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

    @Query(propName = "name", joinName = "dept")
    private String companyName;
}