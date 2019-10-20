/**
 * @filename:ReimbursementDocumentsServiceImpl 2019-10-20 20:50:28
 * @project eladmin  V1.0
 * Copyright(c) 2018 smile Co. Ltd. 
 * All right reserved. 
 */
package com.fn.modules.documents.service.impl;

import com.fn.modules.documents.entity.ReimbursementDocuments;

import com.fn.modules.documents.dao.IReimbursementDocumentsDao;
import com.fn.modules.documents.service.IReimbursementDocumentsService;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**   
 *
 * <p>说明： 报销单据服务实现层</P>
 * @version: V1.0
 * @author: smile
 * 
 */
@Service
public class ReimbursementDocumentsServiceImpl  extends ServiceImpl<IReimbursementDocumentsDao, ReimbursementDocuments> implements IReimbursementDocumentsService  {
}