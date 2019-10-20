/**
 * @filename:ApplicationDocumentsController 2019-10-20 20:51:43
 * @project eladmin  V1.0
 * Copyright(c) 2020 smile Co. Ltd. 
 * All right reserved. 
 */
package com.fn.modules.documents.controller;

import com.fn.modules.documents.base.BaseController;
import com.fn.modules.documents.entity.ApplicationDocuments;
import com.fn.modules.documents.service.IApplicationDocumentsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**   
 *
 * <p>说明： 申请单据API接口层</P>
 * @version: V1.0
 * @author: smile
 * @time    2019-10-20 20:51:43
 *
 */
@Api(description = "申请单据",value="申请单据" )
@RestController
@RequestMapping("/applicationDocuments")
public class ApplicationDocumentsController extends BaseController<IApplicationDocumentsService,ApplicationDocuments>{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
}