/**
 *@filename:IReimbursementDocumentsDao 2019-10-20 20:50:28
 * @project eladmin  V1.0
 * Copyright(c) 2020 smile Co. Ltd.
 * All right reserved.
 */
package com.fn.modules.documents.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.fn.modules.documents.entity.ReimbursementDocuments;

/**
 *
 * <p>说明： 报销单据数据访问层</p>
 * @version: V1.0
 * @author: smile
 *
 */
@Mapper
public interface IReimbursementDocumentsDao extends BaseMapper<ReimbursementDocuments> {

}
