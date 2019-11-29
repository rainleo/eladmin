package com.fn.modules.documents.domain;

import com.fn.modules.system.domain.Dept;
import com.fn.modules.system.domain.User;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.List;

/**
 * @author jie
 * @date 2019-11-13
 */
@Entity
@Data
@Table(name = "document_reviewer")
public class DocumentReviewer implements Serializable {

    // 自增主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 单据id,来自application_documents.id或reimbursement_documents.id
    @Column(name = "document_id")
    private Long documentId;

    @Column(name = "user_id")
    private Long userId;

    // 审核级数，从1开始
    @Column(name = "sorted")
    private Integer sorted;

    // 审核状态(0:审核中,1:已审核)
    @Column(name = "audit_status", nullable = false)
    private Integer auditStatus;

    // 来源（0:申请流程,1:报销流程）
    @Column(name = "source")
    private Integer source;

    // 创建时间
    @Column(name = "create_time", nullable = false)
    private Timestamp createTime;

    // 更新时间
    @Column(name = "update_time", nullable = false)
    private Timestamp updateTime;

    // 删除位（0:未删除,1:已删除）
    @Column(name = "deleted", nullable = false)
    private Integer deleted;

    //单据-审核人中间表
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public void copy(DocumentReviewer source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}