/**
 * @filename:ApplicationDocuments 2019-10-20 20:51:43
 * @project eladmin  V1.0
 * Copyright(c) 2020 smile Co. Ltd. 
 * All right reserved. 
 */
package com.fn.modules.documents.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**   
 *
 * <p>说明： 申请单据实体类</P>
 * @version: V1.0
 * @author: smile
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ApplicationDocuments extends Model<ApplicationDocuments> {

	private static final long serialVersionUID = 1571575903657L;
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "自增主键ID")
	private Long id;

	@ApiModelProperty(name = "applicationNo" , value = "申请单据号")
	private String applicationNo;

	@ApiModelProperty(name = "deptId" , value = "部门ID，来自dept.id")
	private Long deptId;

	@ApiModelProperty(name = "userId" , value = "申请人，来自user.id")
	private Long userId;

	@ApiModelProperty(name = "accountingSubjectsId" , value = "事项，关联会计科目ID,来自accounting_subjects.id")
	private Long accountingSubjectsId;

	@ApiModelProperty(name = "applicationDescription" , value = "申请事项描述")
	private String applicationDescription;

	@ApiModelProperty(name = "amount" , value = "金额")
	private BigDecimal amount;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createTime" , value = "创建时间")
	private Date createTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updatetime" , value = "更新时间")
	private Date updatetime;

	@Override
    protected Serializable pkVal() {
        return this.id;
    }
}
