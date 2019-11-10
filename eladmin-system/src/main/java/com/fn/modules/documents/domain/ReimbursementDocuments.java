package com.fn.modules.documents.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fn.modules.system.domain.Dept;
import com.fn.modules.system.domain.User;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@Table(name="reimbursement_documents")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
@DynamicInsert(true)
@DynamicUpdate(true)
public class ReimbursementDocuments implements Serializable {

    // 自增主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 报销单据号
    @Column(name = "reimbursement_no",nullable = false)
    private String reimbursementNo;

    // 关联部门
    @ManyToOne()
    @JoinColumn(name = "dept_id")
    private Dept dept;

    // 关联用户
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    // 报销摘要
    @Column(name = "reimbursement_abstract")
    private String reimbursementAbstract;

    // 报销金额
    @Column(name = "amount")
    private BigDecimal amount;

    // 附件
    @Column(name = "attachment")
    private String attachment;

    // 创建时间
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    // 更新时间
    @Column(name = "updatetime",nullable = false)
    private Timestamp updatetime;

    //删除位
    @Column(name = "deleted")
    private Integer deleted;

    //单据-审核人中间表
    @OneToMany(targetEntity = DocumentReviewer.class, cascade=CascadeType.ALL)
    @JoinColumn(name="document_id")
    private List<DocumentReviewer> reviewerList;

    public void copy(ReimbursementDocuments source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}