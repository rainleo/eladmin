package com.fn.modules.documents.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-11-04
*/
@Entity
@Data
@Table(name="document_reviewer")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DocumentReviewer implements Serializable {

    // 自增主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 申请单据id,来自application_documents.id
    @Column(name = "document_id")
    private Long documentId;

    // 申请人，来自user.id
    @Column(name = "user_id")
    private Long userId;

    // 审核人，来自user.name
    @Column(name = "user_name")
    private String userName;

    // 审核级数，从1开始
    @Column(name = "sorted")
    private Integer sorted;

    // 来源（0:申请流程,1:报销流程）
    @Column(name = "source")
    private Integer source;

    // 创建时间
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    // 更新时间
    @Column(name = "update_time",nullable = false)
    private Timestamp updateTime;

    //删除位
    @Column(name = "deleted")
    private Integer deleted;

    public void copy(DocumentReviewer source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}