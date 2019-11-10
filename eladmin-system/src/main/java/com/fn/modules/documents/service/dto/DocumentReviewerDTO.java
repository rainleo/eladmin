package com.fn.modules.documents.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author jie
* @date 2019-11-04
*/
@Data
public class DocumentReviewerDTO implements Serializable {

    // 自增主键ID
    private Long id;

    // 申请单据id,来自application_documents.id
    private Long documentId;

    // 申请人，来自user.id
    private Long userId;

    // 审核人，来自user.name
    private String userName;

    // 审核级数，从1开始
    private Integer sorted;

    // 来源（0:申请流程,1:报销流程）
    private Integer source;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;

    //删除位
    private Integer deleted;
}