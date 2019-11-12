package com.fn.modules.documents.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author jie
* @date 2019-11-10
*/
@Data
public class TodoListDTO implements Serializable {

    // 自增主键ID
    private Long id;

    // 摘要
    private String todoAbstract;

    // 预期完成时间
    private Timestamp expectedCompletionTime;

    // 抄送人，来自user.name
    private Long copyPersonId;

    // 协助人员
    private Long assistantId;

    // 待办内容
    private String content;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;

    // 删除位（0:未删除,1:已删除）
    private Integer deleted;
}