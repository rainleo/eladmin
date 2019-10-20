/**
 * @filename:AccountingSubjects 2019-10-20 20:52:56
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
import java.util.Date;

/**   
 *
 * <p>说明： 会计科目实体类</P>
 * @version: V1.0
 * @author: smile
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AccountingSubjects extends Model<AccountingSubjects> {

	private static final long serialVersionUID = 1571575976471L;
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "自增主键ID")
	private Long id;

	@ApiModelProperty(name = "subjectCode" , value = "科目代码")
	private String subjectCode;

	@ApiModelProperty(name = "subjectName" , value = "科目名称")
	private String subjectName;

	@ApiModelProperty(name = "auxiliaryAccountType" , value = "辅助账类型")
	private String auxiliaryAccountType;

	@ApiModelProperty(name = "itemDetails" , value = "辅助核算项目明细")
	private String itemDetails;

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
