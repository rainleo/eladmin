package com.fn.modules.system.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fn.modules.system.domain.DeptDetail;
import com.fn.modules.system.domain.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.List;

/**
* @author leo
* @date 2019-03-25
*/
@Data
public class DeptDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    @NotNull
    private Boolean enabled;

    /**
     * 上级部门
     */
    private Long pid;

    /**
     * 创建人
     */
    private User createdByUser;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptDTO> children;

    private Timestamp createTime;

    private List<DeptDetail> deptDetailList;

    public String getLabel() {
        return name;
    }
}