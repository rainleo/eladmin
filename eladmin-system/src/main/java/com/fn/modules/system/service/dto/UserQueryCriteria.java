package com.fn.modules.system.service.dto;

import lombok.Data;
import com.fn.annotation.Query;
import java.io.Serializable;
import java.util.Set;

/**
 * @author leo
 * @date 2019-09-23
 */
@Data
public class UserQueryCriteria implements Serializable {

    @Query
    private Long id;

    @Query(propName = "id", type = Query.Type.IN, joinName = "dept")
    private Set<Long> deptIds;

    // 多字段模糊
    @Query(blurry = "email,username")
    private String blurry;

    @Query
    private Boolean enabled;

    private Long deptId;

    @Query
    private Long companyId;


    @Query
    private Long jobId;
}
