/**
 * @filename:ApplicationDocumentsServiceImpl 2019-10-20 20:51:43
 * @project eladmin  V1.0
 * Copyright(c) 2018 smile Co. Ltd. 
 * All right reserved. 
 */
package com.fn.modules.documents.service.impl;

import com.fn.modules.documents.entity.ApplicationDocuments;

import com.fn.modules.documents.dao.IApplicationDocumentsDao;
import com.fn.modules.documents.service.IApplicationDocumentsService;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**   
 *
 * <p>说明： 申请单据服务实现层</P>
 * @version: V1.0
 * @author: smile
 * 
 */
@Service
public class ApplicationDocumentsServiceImpl  extends ServiceImpl<IApplicationDocumentsDao, ApplicationDocuments> implements IApplicationDocumentsService  {
}