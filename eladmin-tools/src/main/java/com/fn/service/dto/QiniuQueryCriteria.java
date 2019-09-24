package com.fn.service.dto;

import com.fn.annotation.Query;
import lombok.Data;
import com.fn.annotation.Query;

/**
 * @author leo
 * @date 2019-6-4 09:54:37
 */
@Data
public class QiniuQueryCriteria{

    @Query(type = Query.Type.INNER_LIKE)
    private String key;
}
