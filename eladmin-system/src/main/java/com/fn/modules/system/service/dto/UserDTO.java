package com.fn.modules.system.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fn.modules.system.domain.Dept;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 * @author leo
 * @date 2019-09-23
 */
@Data
public class UserDTO implements Serializable {

    @ApiModelProperty(hidden = true)
    private Long id;

    private String username;

    private String avatar;

    private String email;

    private String phone;

    private Boolean enabled;

    @JsonIgnore
    private String password;

    private Timestamp createTime;

    private Date lastPasswordResetTime;

    @ApiModelProperty(hidden = true)
    private Set<RoleSmallDTO> roles;

    @ApiModelProperty(hidden = true)
    private JobSmallDTO job;

    private DeptSmallDTO dept;

    private Long deptId;

    private Long companyId;

    private Dept company;

    private String deptName;

    private String companyName;

    private String jobName;

    // 用户导出
    public String getDeptName() {
        return dept.getName();
    }

    public String getCompanyName() {
        return company.getName();
    }

    public String getJobName() {
        return job.getName();
    }
}
