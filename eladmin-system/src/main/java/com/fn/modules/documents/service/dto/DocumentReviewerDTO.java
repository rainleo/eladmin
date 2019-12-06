package com.fn.modules.documents.service.dto;

import com.fn.modules.system.domain.Dept;
import com.fn.modules.system.domain.User;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author jie
* @date 2019-11-13
*/
@Data
public class DocumentReviewerDTO implements Serializable {

    // 自增主键ID
    private Long id;

    // 单据id,来自application_documents.id或reimbursement_documents.id
    private Long documentId;

    // 申请（报销）人，来自user.id
    private Long userId;

    // 审核级数，从1开始
    private Integer sorted;

    // 审核状态(0:审核中,1:已审核)
    private Integer auditStatus;

    // 来源（0:申请流程,1:报销流程）
    private Integer source;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;

    // 删除位（0:未删除,1:已删除）
    private Integer deleted;

    // 审批人
    private User user;

    private Long companyId;

    // 公司
    private Dept company;
}