/**
 * @filename:DocumentReviewerController 2019-10-20 20:52:29
 * @project eladmin  V1.0
 * Copyright(c) 2020 smile Co. Ltd. 
 * All right reserved. 
 */
package com.fn.modules.documents.controller;

import com.fn.modules.documents.base.BaseController;
import com.fn.modules.documents.entity.DocumentReviewer;
import com.fn.modules.documents.service.IDocumentReviewerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**   
 *
 * <p>说明： 单据-审核人关系API接口层</P>
 * @version: V1.0
 * @author: smile
 * @time    2019-10-20 20:52:29
 *
 */
@Api(description = "单据-审核人关系",value="单据-审核人关系" )
@RestController
@RequestMapping("/documentReviewer")
public class DocumentReviewerController extends BaseController<IDocumentReviewerService,DocumentReviewer>{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
}