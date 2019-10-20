/**
 * @filename:AccountingSubjectsServiceImpl 2019-10-20 20:52:56
 * @project eladmin  V1.0
 * Copyright(c) 2018 smile Co. Ltd. 
 * All right reserved. 
 */
package com.fn.modules.documents.service.impl;

import com.fn.modules.documents.entity.AccountingSubjects;

import com.fn.modules.documents.dao.IAccountingSubjectsDao;
import com.fn.modules.documents.service.IAccountingSubjectsService;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**   
 *
 * <p>说明： 会计科目服务实现层</P>
 * @version: V1.0
 * @author: smile
 * 
 */
@Service
public class AccountingSubjectsServiceImpl  extends ServiceImpl<IAccountingSubjectsDao, AccountingSubjects> implements IAccountingSubjectsService  {
}