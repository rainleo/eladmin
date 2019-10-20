/**
 * @filename:AccountingSubjectsController 2019-10-20 20:52:56
 * @project eladmin  V1.0
 * Copyright(c) 2020 smile Co. Ltd. 
 * All right reserved. 
 */
package com.fn.modules.documents.controller;

import com.fn.modules.documents.base.BaseController;
import com.fn.modules.documents.entity.AccountingSubjects;
import com.fn.modules.documents.service.IAccountingSubjectsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**   
 *
 * <p>说明： 会计科目API接口层</P>
 * @version: V1.0
 * @author: smile
 * @time    2019-10-20 20:52:56
 *
 */
@Api(description = "会计科目",value="会计科目" )
@RestController
@RequestMapping("/accountingSubjects")
public class AccountingSubjectsController extends BaseController<IAccountingSubjectsService,AccountingSubjects>{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
}