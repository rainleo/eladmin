package com.fn.modules.system.service.dto;

import com.fn.annotation.Query;
import lombok.Data;

import java.util.Set;

/**
 * @author leo
 * @date 2019-03-25
 */
@Data
public class DeptQueryCriteria {

    @Query(type = Query.Type.IN, propName = "id")
    private Set<Long> ids;

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query
    private Long pid;

    @Query
    private Long createdBy;

    @Query(propName = "username", joinName = "createdByUser")
    private String userName;

    @Query(propName = "attachment", type = Query.Type.EQUAL, joinName = "deptDetailList", join = Query.Join.LEFT)
    private String attachment;
}