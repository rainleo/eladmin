package com.fn.modules.documents.service.dto;

import com.fn.modules.system.domain.Job;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author jie
* @date 2019-11-11
*/
@Data
public class AuditChainDTO implements Serializable {

    // 自增主键ID
    private Long id;

    // 岗位，来自job.id
    private Job job;

    // 审核顺序，从1开始
    private Integer sorted;

    // 来源（0:申请流程,1:报销流程）
    private Integer source;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;

    // 删除位（0:未删除,1:已删除）
    private Integer deleted;

}