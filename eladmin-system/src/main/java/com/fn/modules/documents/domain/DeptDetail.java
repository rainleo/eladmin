package com.fn.modules.documents.domain;

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
@Table(name="dept_detail")
public class DeptDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 部门id
    @Column(name = "dept_id")
    private Long deptId;

    // 图片等附件地址
    @Column(name = "attachment")
    private String attachment;

    // 创建时间
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    // 更新时间
    @Column(name = "update_time",nullable = false)
    private Timestamp updateTime;

    // 删除位（0:未删除,1:已删除）
    @Column(name = "deleted",nullable = false)
    private Integer deleted;

    public void copy(DeptDetail source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}