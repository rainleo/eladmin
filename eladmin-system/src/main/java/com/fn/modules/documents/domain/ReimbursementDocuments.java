package com.fn.modules.documents.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fn.modules.system.domain.Dept;
import com.fn.modules.system.domain.User;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.List;

/**
 * @author jie
 * @date 2019-11-04
 */
@Entity
@Data
@Table(name = "reimbursement_documents")
public class ReimbursementDocuments implements Serializable {

    // 自增主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 报销单据号
    @Column(name = "reimbursement_no", nullable = false)
    private String reimbursementNo;

    // 审批状态
    @Column(name = "status", nullable = false)
    private Integer status;

    // 部门id
    @Column(name = "dept_id")
    private Long deptId;

    // 关联部门
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "dept_id", insertable = false, updatable = false)
    private Dept dept;

    // 报销人id
    @Column(name = "user_id")
    private Long userId;

    // 关联用户
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    // 报销摘要
    @Column(name = "reimbursement_abstract")
    private String reimbursementAbstract;

    // 报销金额
    @Column(name = "amount")
    private BigDecimal amount;

    // 创建时间
    @Column(name = "create_time", nullable = false)
    private Timestamp createTime;

    // 更新时间
    @Column(name = "updatetime", nullable = false)
    private Timestamp updatetime;

    //删除位
    @Column(name = "deleted")
    private Integer deleted;

    //单据-审核人中间表
    @OneToMany(targetEntity = DocumentReviewer.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    @Where(clause = "source = 1 and deleted = 0")
    private List<DocumentReviewer> reviewerList;

    //附件表
    @OneToMany(targetEntity = ReimbursementDetail.class, cascade = {CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "reimbursement_documents_id", insertable = false, updatable = false)
    @Where(clause = "deleted = 0")
    private List<ReimbursementDetail> reimbursementDetailList;

    public void copy(ReimbursementDocuments source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}