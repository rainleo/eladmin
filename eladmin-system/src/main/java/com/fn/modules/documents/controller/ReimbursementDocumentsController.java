/**
 * @filename:ReimbursementDocumentsController 2019-10-20 20:50:28
 * @project eladmin  V1.0
 * Copyright(c) 2020 smile Co. Ltd. 
 * All right reserved. 
 */
package com.fn.modules.documents.controller;

import com.fn.modules.documents.base.BaseController;
import com.fn.modules.documents.entity.ReimbursementDocuments;
import com.fn.modules.documents.service.IReimbursementDocumentsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**   
 *
 * <p>说明： 报销单据API接口层</P>
 * @version: V1.0
 * @author: smile
 * @time    2019-10-20 20:50:28
 *
 */
@Api(description = "报销单据",value="报销单据" )
@RestController
@RequestMapping("/reimbursementDocuments")
public class ReimbursementDocumentsController extends BaseController<IReimbursementDocumentsService,ReimbursementDocuments>{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
}