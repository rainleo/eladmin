package com.fn.modules.system.service.dto;

import lombok.Data;
import com.fn.annotation.Query;

/**
 * 公共查询类
 */
@Data
public class MenuQueryCriteria {

    // 多字段模糊
    @Query(blurry = "name,path,component")
    private String blurry;
}
