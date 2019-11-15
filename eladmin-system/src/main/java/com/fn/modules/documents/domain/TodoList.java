package com.fn.modules.documents.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fn.modules.system.domain.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author jie
 * @date 2019-11-10
 */
@Entity
@Data
@Table(name = "todo_list")
public class TodoList implements Serializable {

    // 自增主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 摘要
    @Column(name = "todo_abstract")
    private String todoAbstract;

    // 预期完成时间
    @Column(name = "expected_completion_time", nullable = false)
    private Timestamp expectedCompletionTime;

    // 抄送人id
    @Column(name = "copy_person_id")
    private Long copyPersonId;

    // 抄送人，关联用户
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "copy_person_id", insertable = false, updatable = false)
    private User copyPerson;

    // 协助人员id
    @Column(name = "assistant_id")
    private Long assistantPersonId;

    // 协助人员
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "assistant_id", insertable = false, updatable = false)
    private User assistantPerson;

    // 待办内容
    @Column(name = "content")
    private String content;

    // 创建时间
    @Column(name = "create_time", nullable = false)
    private Timestamp createTime;

    // 更新时间
    @Column(name = "update_time", nullable = false)
    private Timestamp updateTime;

    // 删除位（0:未删除,1:已删除）
    @Column(name = "deleted", nullable = false)
    private Integer deleted;

    public void copy(TodoList source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}