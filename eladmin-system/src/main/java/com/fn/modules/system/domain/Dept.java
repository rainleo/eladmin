package com.fn.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
* @author leo
* @date 2019-03-25
*/
@Entity
@Data
@Table(name="dept")
public class Dept implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(groups = Update.class)
    private Long id;

    /**
     * 名称
     */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    @NotNull
    private Boolean enabled;

    /**
     * 上级部门
     */
    @Column(name = "pid",nullable = false)
    @NotNull
    private Long pid;

    @JsonIgnore
    @ManyToMany(mappedBy = "depts")
    private Set<Role> roles;

    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    //附件表
    @OneToMany(targetEntity = DeptDetail.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    @Where(clause = "deleted = 0")
    private List<DeptDetail> deptDetailList;

    public @interface Update {}
}