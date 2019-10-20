/**
 * @filename:ReimbursementDocumentsController 2019-10-20 20:50:28
 * @project eladmin  V1.0
 * Copyright(c) 2020 smile Co. Ltd. 
 * All right reserved. 
 */
package com.fn.modules.documents.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.flying.cattle.mdg.aid.JsonResult;
import com.github.flying.cattle.mdg.aid.PageParam;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**   
 *
 * <p>说明： 报销单据API接口层</P>
 * @version: V1.0
 * @author:  smile
 * @time     2019-10-20 20:50:28
 */
public class BaseController<S extends IService<T>,T>{
	
	@Autowired
    protected S baseService;

	protected JsonResult<T> result = new JsonResult<T>();
	/**
	 * @explain 查询对象  <swagger GET请求>
	 * @param   id
	 * @return  JsonResult
	 * @author  smile
	 * @time    2019-10-20 20:50:28
	 */
	@GetMapping("/getById/{id}")
	@ApiOperation(value = "获取对象", notes = "作者：smile")
	@ApiImplicitParam(paramType="path", name = "id", value = "对象id", required = true, dataType = "Long")
	public JsonResult<T> getById(@PathVariable("id")Long id){
		T obj=baseService.getById(id);
		if (null!=obj ) {
			 result.success(obj);
		}else {
			 result.error("查询对象不存在！");
		}
		return result;
	}
	
	/**
	 * @explain 删除对象
	 * @param   id
	 * @return  JsonResult
	 * @author  smile
	 * @time    2019-10-20 20:50:28
	 */
	@PostMapping("/deleteById")
	@ApiOperation(value = "删除", notes = "作者：smile")
	@ApiImplicitParam(paramType="query", name = "id", value = "对象id", required = true, dataType = "Long")
	public JsonResult<T> deleteById(Long id){
		JsonResult<T> result=new JsonResult<T>();
		T obj=baseService.getById(id);
		if (null!=obj) {
		  boolean rsg = baseService.removeById(id);
		  if (rsg) {
			    result.success("删除成功");
		  }else {
			   result.error("删除失败！");
		  }
		}else {
			 result.error("删除的对象不存在！");
		}
		return result;
	}
	
	/**
	 * @explain 添加
	 * @param   entity
	 * @return  Boolean
	 * @author  smile
	 * @time    2019-10-20 20:50:28
	 */
	@PostMapping("/insert")
	@ApiOperation(value = "添加", notes = "作者：smile")
	public JsonResult<T> insert(T entity){
		JsonResult<T> result=new JsonResult<T>();
		if (null!=entity) {
			boolean rsg = baseService.save(entity);
			if (rsg) {
				  result.success("添加成功");
			  }else {
				  result.error("添加失败！");
			  }
		}else {
			result.error("请传入正确参数！");
		}
		return result;
	}
	
	/**
	 * @explain 修改
	 * @param   entity
	 * @return  Boolean
	 * @author  smile
	 * @time    2019-10-20 20:50:28
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "作者：smile")
	public JsonResult<T> update(T entity){
		JsonResult<T> result=new JsonResult<T>();
		if (null!=entity) {
			boolean rsg = baseService.updateById(entity);
			if (rsg) {
				  result.success("修改成功");
			  }else {
				  result.error("修改失败！");
			  }
		}else {
			result.error("请传入正确参数！");
		}
		return result;
	}
	
	/**
	 * @explain 分页条件查询用户   
	 * @param   param
	 * @return  PageInfo<ReimbursementDocuments>
	 * @author  smile
	 * @time    2019-10-20 20:50:28
	 */
	@GetMapping("/getPages")
	@ApiOperation(value = "分页查询", notes = "分页查询返回[IPage<T>],作者：smile")
	public JsonResult<IPage<T>> getPages(PageParam<T> param){
		JsonResult<IPage<T>> returnPage=new JsonResult<IPage<T>>();
		Page<T> page=new Page<T>(param.getPageNum(),param.getPageSize());
		QueryWrapper<T> queryWrapper =new QueryWrapper<T>();
		queryWrapper.setEntity(param.getParam());
		//分页数据
		IPage<T> pageData=baseService.page(page, queryWrapper);
		returnPage.success(pageData);
		
		return returnPage;
	}
}
