package com.fn.modules.documents.service.dto;

import com.fn.modules.system.domain.User;
import com.fn.modules.system.service.dto.DeptQueryCriteria;
import com.fn.modules.system.service.dto.UserQueryCriteria;
import com.sun.deploy.security.ValidationState;
import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import com.fn.annotation.Query;

/**
* @author jie
* @date 2019-11-04
*/
@Data
public class ApplicationDocumentsQueryCriteria{

    // 精确
    @Query
    private Long id;

    // 精确
    @Query
    private String applicationNo;

    // 精确
    @Query
    private Long deptId;

    // 精确
    @Query
    private Long userId;

    // 精确
    @Query
    private Long accountingSubjectsId;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String applicationDescription;

    // 精确
    @Query
    private BigDecimal amount;

    // 精确
    @Query
    private Timestamp createTime;

    // 精确
    @Query
    private Timestamp updatetime;

    // 精确
    @Query
    private Integer deleted;

    @Query
    private UserQueryCriteria userQueryCriteria;

    @Query
    private DeptQueryCriteria deptQueryCriteria;

    @Query
    private AccountingSubjectsQueryCriteria accountingSubjectsQueryCriteria;

    @Query(propName = "source",type = Query.Type.EQUAL,joinName = "reviewerList",join = Query.Join.LEFT)
    private  String source ;


}