package com.fn.modules.documents.service.dto;

import lombok.Data;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author jie
* @date 2019-11-04
*/
@Data
public class AccountingSubjectsDTO implements Serializable {

    // 自增主键ID
    private Long id;

    // 科目代码
    private String subjectCode;

    // 科目名称
    private String subjectName;

    // 辅助账类型
    private String auxiliaryAccountType;

    // 辅助核算项目明细
    private String itemDetails;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updatetime;

    //删除位
    private Integer deleted;
}