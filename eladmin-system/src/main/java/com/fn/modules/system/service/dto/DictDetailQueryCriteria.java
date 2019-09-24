package com.fn.modules.system.service.dto;

import lombok.Data;
import com.fn.annotation.Query;

/**
* @author leo
* @date 2019-04-10
*/
@Data
public class DictDetailQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String label;

    @Query(propName = "name",joinName = "dict")
    private String dictName;
}