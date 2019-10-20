/**
 * @filename:DocumentReviewer 2019-10-20 20:52:29
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
 * <p>说明： 单据-审核人关系实体类</P>
 * @version: V1.0
 * @author: smile
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DocumentReviewer extends Model<DocumentReviewer> {

	private static final long serialVersionUID = 1571575949930L;
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "自增主键ID")
	private Long id;

	@ApiModelProperty(name = "documentId" , value = "申请单据id,来自application_documents.id")
	private Long documentId;

	@ApiModelProperty(name = "userId" , value = "申请人，来自user.id")
	private Long userId;

	@ApiModelProperty(name = "userName" , value = "审核人，来自user.name")
	private String userName;

	@ApiModelProperty(name = "sort" , value = "审核级数，从1开始")
	private Integer sort;

	@ApiModelProperty(name = "source" , value = "来源（0:申请流程,1:报销流程）")
	private Integer source;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createTime" , value = "创建时间")
	private Date createTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "更新时间")
	private Date updateTime;

	@Override
    protected Serializable pkVal() {
        return this.id;
    }
}
