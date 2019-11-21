package com.fn.modules.documents.service.dto;

import lombok.Data;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author jie
* @date 2019-11-18
*/
@Data
public class ReimbursementDetailDTO implements Serializable {

    // 自增主键ID
    private Long id;

    // 报销单据id
    private Long reimbursementDocumentsId;

    // 图片等附件地址
    private String attachment;

    /**
     * 文件名，如qiniu.jpg
     */
    private String name;

    /**
     * 空间名
     */
    private String bucket;

    /**
     * 大小
     */
    private String size;

    /**
     * 空间类型：公开/私有
     */
    private String type;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;

    // 删除位（0:未删除,1:已删除）
    private Integer deleted;
}