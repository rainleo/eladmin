package com.fn.modules.documents.service.dto;

import com.fn.modules.documents.domain.AccountingSubjects;
import com.fn.modules.documents.domain.DocumentReviewer;
import com.fn.modules.system.domain.Dept;
import com.fn.modules.system.domain.User;
import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.List;


/**
* @author jie
* @date 2019-11-04
*/
@Data
public class ApplicationDocumentsDTO implements Serializable {

    // 自增主键ID
    private Long id;

    // 申请单据号
    private String applicationNo;

    // 部门
    private Dept dept;

    // 申请人
    private User user;

    // 申请事项
    private AccountingSubjects accountingSubjects;

    // 申请事项描述
    private String applicationDescription;

    // 金额
    private BigDecimal amount;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updatetime;

    //删除位
    private Integer deleted;

    //单据-审核人中间表
    private List<DocumentReviewer> reviewerList;
}