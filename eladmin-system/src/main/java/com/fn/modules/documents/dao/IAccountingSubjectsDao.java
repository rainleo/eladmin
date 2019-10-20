/**
 *@filename:IAccountingSubjectsDao 2019-10-20 20:52:56
 * @project eladmin  V1.0
 * Copyright(c) 2020 smile Co. Ltd.
 * All right reserved.
 */
package com.fn.modules.documents.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.fn.modules.documents.entity.AccountingSubjects;

/**
 *
 * <p>说明： 会计科目数据访问层</p>
 * @version: V1.0
 * @author: smile
 *
 */
@Mapper
public interface IAccountingSubjectsDao extends BaseMapper<AccountingSubjects> {

}
