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
@Table(name="accounting_subjects")
public class AccountingSubjects implements Serializable {

    // 自增主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 科目代码
    @Column(name = "subject_code",nullable = false)
    private String subjectCode;

    // 科目名称
    @Column(name = "subject_name",nullable = false)
    private String subjectName;

    // 辅助账类型
    @Column(name = "auxiliary_account_type")
    private String auxiliaryAccountType;

    // 辅助核算项目明细
    @Column(name = "item_details")
    private String itemDetails;

    // 创建时间
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    // 更新时间
    @Column(name = "updatetime",nullable = false)
    private Timestamp updatetime;

    //删除位
    @Column(name = "deleted")
    private Integer deleted;

    public void copy(AccountingSubjects source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}