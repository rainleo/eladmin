/**
 * @filename:DocumentReviewerServiceImpl 2019-10-20 20:52:29
 * @project eladmin  V1.0
 * Copyright(c) 2018 smile Co. Ltd. 
 * All right reserved. 
 */
package com.fn.modules.documents.service.impl;

import com.fn.modules.documents.entity.DocumentReviewer;

import com.fn.modules.documents.dao.IDocumentReviewerDao;
import com.fn.modules.documents.service.IDocumentReviewerService;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**   
 *
 * <p>说明： 单据-审核人关系服务实现层</P>
 * @version: V1.0
 * @author: smile
 * 
 */
@Service
public class DocumentReviewerServiceImpl  extends ServiceImpl<IDocumentReviewerDao, DocumentReviewer> implements IDocumentReviewerService  {
}