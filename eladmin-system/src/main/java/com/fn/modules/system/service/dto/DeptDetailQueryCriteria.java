package com.fn.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import com.fn.annotation.Query;

/**
* @author jie
* @date 2019-11-18
*/
@Data
public class DeptDetailQueryCriteria{

    // 精确
    @Query
    private Long id;

    // 精确
    @Query
    private Long deptId;

    // 精确
    @Query
    private String attachment;

    // 精确
    @Query
    private Timestamp createTime;

    // 精确
    @Query
    private Timestamp updateTime;

    // 精确
    @Query
    private Integer deleted;
}