package com.fn.modules.documents.domain;

import com.fn.modules.system.domain.Job;
import com.fn.modules.system.domain.User;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author jie
 * @date 2019-11-11
 */
@Entity
@Data
@Table(name = "audit_chain")
public class AuditChain implements Serializable {

    // 自增主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "job_id")
    private Long jobId;

    // 关联岗位
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "job_id", insertable = false, updatable = false)
    private Job job;

    // 审核顺序，从1开始
    @Column(name = "sorted")
    private Integer sorted;

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

    public void copy(AuditChain source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}