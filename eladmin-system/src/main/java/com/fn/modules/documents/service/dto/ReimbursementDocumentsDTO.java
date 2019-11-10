package com.fn.modules.documents.service.dto;

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
public class ReimbursementDocumentsDTO implements Serializable {

    // 自增主键ID
    private Long id;

    // 报销单据号
    private String reimbursementNo;

    // 部门
    private Dept dept;

    // 申请人
    private User user;

    // 报销摘要
    private String reimbursementAbstract;

    // 报销金额
    private BigDecimal amount;

    // 附件
    private String attachment;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updatetime;

    //删除位
    private Integer deleted;

    //单据-审核人中间表
    private List<DocumentReviewer> reviewerList;
}