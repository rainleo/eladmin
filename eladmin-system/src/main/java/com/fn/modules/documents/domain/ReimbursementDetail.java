package com.fn.modules.documents.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-11-18
*/
@Entity
@Data
@Table(name="reimbursement_detail")
public class ReimbursementDetail implements Serializable {

    // 自增主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 报销单据id
    @Column(name = "reimbursement_documents_id")
    private Long reimbursementDocumentsId;

    // 图片等附件地址
    @Column(name = "attachment")
    private String attachment;

    /**
     * 文件名，如qiniu.jpg
     */
    @Column(name = "name",unique = false)
    private String name;

    /**
     * 空间名
     */
    @Column(name = "bucket")
    private String bucket;

    /**
     * 大小
     */
    @Column(name = "size")
    private String size;

    /**
     * 空间类型：公开/私有
     */
    @Column(name = "type")
    private String type = "公开";

    // 创建时间
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    // 更新时间
    @Column(name = "update_time",nullable = false)
    private Timestamp updateTime;

    // 删除位（0:未删除,1:已删除）
    @Column(name = "deleted",nullable = false)
    private Integer deleted;

    public void copy(ReimbursementDetail source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}