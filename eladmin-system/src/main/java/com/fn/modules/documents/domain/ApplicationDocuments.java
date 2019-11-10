package com.fn.modules.documents.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fn.modules.system.domain.Dept;
import com.fn.modules.system.domain.User;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jie
 * @date 2019-11-04
 */
@Entity
@Data
@Table(name = "application_documents")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
@DynamicInsert(true)
@DynamicUpdate(true)
public class ApplicationDocuments implements Serializable {

    // 自增主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 申请单据号
    @Column(name = "application_no", nullable = false)
    private String applicationNo;

    // 关联部门
    @ManyToOne()
    @JoinColumn(name = "dept_id")
    private Dept dept;

    // 关联用户
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    // 关联会计科目
    @ManyToOne()
    @JoinColumn(name = "accounting_subjects_id")
    private AccountingSubjects accountingSubjects;

    // 申请事项描述
    @Column(name = "application_description")
    private String applicationDescription;

    // 金额
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
    @OneToMany(targetEntity = DocumentReviewer.class, cascade=CascadeType.ALL)
    @JoinColumn(name="document_id")
    @Where(clause = "source = 0")
    private List<DocumentReviewer> reviewerList ;



    public void copy(ApplicationDocuments source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}