package com.fn.modules.documents.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import com.fn.annotation.Query;

/**
* @author jie
* @date 2019-11-04
*/
@Data
public class DocumentReviewerQueryCriteria{

    // 精确
    @Query
    private Long id;

    // 精确
    @Query
    private Long documentId;

    // 精确
    @Query
    private Long userId;

    // 精确
    @Query
    private Integer auditStatus;

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
    private Integer deleted;
}