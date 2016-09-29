package com.jeecg.product.controller;
import com.jeecg.product.entity.ZbProductTypeMainEntity;
import com.jeecg.product.service.ZbProductTypeMainServiceI;
import com.jeecg.product.page.ZbProductTypeMainPage;
import com.jeecg.product.entity.ZbProductTypeHEntity;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 产品主分类表
 * @author onlineGenerator
 * @date 2016-09-27 22:17:59
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zbProductTypeMainController")
public class ZbProductTypeMainController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZbProductTypeMainController.class);

	@Autowired
	private ZbProductTypeMainServiceI zbProductTypeMainService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 产品主分类表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/product/zbProductTypeMainList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ZbProductTypeMainEntity zbProductTypeMain,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZbProductTypeMainEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zbProductTypeMain);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		//追加查询条件，状态为未删除的
		cq.eq("dr", "0");
		cq.add();
		this.zbProductTypeMainService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除产品主分类表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZbProductTypeMainEntity zbProductTypeMain, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		zbProductTypeMain = systemService.getEntity(ZbProductTypeMainEntity.class, zbProductTypeMain.getId());
		String message = "产品主分类表删除成功";
		try{
			//获取参数
			Object main_type0 = zbProductTypeMain.getId();
		    String hql0 = "from ZbProductTypeHEntity where dr = '0' AND main_type = ? ";
		    List<ZbProductTypeHEntity> zbProductTypeHOldList = zbProductTypeMainService.findHql(hql0,main_type0);
			if(zbProductTypeHOldList.size() == 0) {
				zbProductTypeMain.setDr("1");
				zbProductTypeMainService.saveOrUpdate(zbProductTypeMain);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			} else {
				message = "该分类还有子分类，请先删除子分类！";
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "产品主分类表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除产品主分类表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "产品主分类表删除成功";
		try{
			for(String id:ids.split(",")){
				ZbProductTypeMainEntity zbProductTypeMain = systemService.getEntity(ZbProductTypeMainEntity.class,id);
				//获取参数
				Object main_type0 = zbProductTypeMain.getId();
			    String hql0 = "from ZbProductTypeHEntity where dr = '0' AND main_type = ? ";
			    List<ZbProductTypeHEntity> zbProductTypeHOldList = zbProductTypeMainService.findHql(hql0,main_type0);
				if(zbProductTypeHOldList.size() == 0) {
					zbProductTypeMain.setDr("1");
					zbProductTypeMainService.saveOrUpdate(zbProductTypeMain);
					systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
				} else {
					message = "该分类还有子分类，请先删除子分类！";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "产品主分类表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加产品主分类表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZbProductTypeMainEntity zbProductTypeMain,ZbProductTypeMainPage zbProductTypeMainPage, HttpServletRequest request) {
		List<ZbProductTypeHEntity> zbProductTypeHList =  zbProductTypeMainPage.getZbProductTypeHList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			zbProductTypeMainService.addMain(zbProductTypeMain, zbProductTypeHList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "产品主分类表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新产品主分类表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZbProductTypeMainEntity zbProductTypeMain,ZbProductTypeMainPage zbProductTypeMainPage, HttpServletRequest request) {
		List<ZbProductTypeHEntity> zbProductTypeHList =  zbProductTypeMainPage.getZbProductTypeHList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			zbProductTypeMainService.updateMain(zbProductTypeMain, zbProductTypeHList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新产品主分类表失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 产品主分类表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZbProductTypeMainEntity zbProductTypeMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zbProductTypeMain.getId())) {
			zbProductTypeMain = zbProductTypeMainService.getEntity(ZbProductTypeMainEntity.class, zbProductTypeMain.getId());
			req.setAttribute("zbProductTypeMainPage", zbProductTypeMain);
		}
		return new ModelAndView("com/jeecg/product/zbProductTypeMain-add");
	}
	
	/**
	 * 产品主分类表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZbProductTypeMainEntity zbProductTypeMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zbProductTypeMain.getId())) {
			zbProductTypeMain = zbProductTypeMainService.getEntity(ZbProductTypeMainEntity.class, zbProductTypeMain.getId());
			req.setAttribute("zbProductTypeMainPage", zbProductTypeMain);
		}
		return new ModelAndView("com/jeecg/product/zbProductTypeMain-update");
	}
	
	
	/**
	 * 加载明细列表[子分类]
	 * 
	 * @return
	 */
	@RequestMapping(params = "zbProductTypeHList")
	public ModelAndView zbProductTypeHList(ZbProductTypeMainEntity zbProductTypeMain, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object main_type0 = zbProductTypeMain.getId();
		//===================================================================================
		//查询-子分类
	    String hql0 = "from ZbProductTypeHEntity where dr = '0' AND mainType = ? ";
	    try{
	    	List<ZbProductTypeHEntity> zbProductTypeHEntityList = systemService.findHql(hql0,main_type0);
			req.setAttribute("zbProductTypeHList", zbProductTypeHEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/product/zbProductTypeHList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(ZbProductTypeMainEntity zbProductTypeMain,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(ZbProductTypeMainEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zbProductTypeMain);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<ZbProductTypeMainEntity> list=this.zbProductTypeMainService.getListByCriteriaQuery(cq, false);
    	List<ZbProductTypeMainPage> pageList=new ArrayList<ZbProductTypeMainPage>();
        if(list!=null&&list.size()>0){
        	for(ZbProductTypeMainEntity entity:list){
        		try{
        		ZbProductTypeMainPage page=new ZbProductTypeMainPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
           		    Object main_type0 = entity.getMainTypeName();
				    String hql0 = "from ZbProductTypeHEntity where dr = '0' AND mainType = ? ";
        	        List<ZbProductTypeHEntity> zbProductTypeHEntityList = systemService.findHql(hql0,main_type0);
            		page.setZbProductTypeHList(zbProductTypeHEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"产品主分类表");
        map.put(NormalExcelConstants.CLASS,ZbProductTypeMainPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("产品主分类表列表", "导出人:Jeecg",
            "导出信息"));
        map.put(NormalExcelConstants.DATA_LIST,pageList);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

    /**
	 * 通过excel导入数据
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(2);
			params.setNeedSave(true);
			try {
				List<ZbProductTypeMainPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), ZbProductTypeMainPage.class, params);
				ZbProductTypeMainEntity entity1=null;
				for (ZbProductTypeMainPage page : list) {
					entity1=new ZbProductTypeMainEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            zbProductTypeMainService.addMain(entity1, page.getZbProductTypeHList());
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			}
			return j;
	}
	/**
	* 导出excel 使模板
	*/
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ModelMap map) {
		map.put(NormalExcelConstants.FILE_NAME,"产品主分类表");
		map.put(NormalExcelConstants.CLASS,ZbProductTypeMainPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("产品主分类表列表", "导出人:"+ ResourceUtil.getSessionUserName().getRealName(),
		"导出信息"));
		map.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	* 导入功能跳转
	*
	* @return
	*/
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "zbProductTypeMainController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZbProductTypeMainEntity> list() {
		List<ZbProductTypeMainEntity> listZbProductTypeMains=zbProductTypeMainService.getList(ZbProductTypeMainEntity.class);
		return listZbProductTypeMains;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZbProductTypeMainEntity task = zbProductTypeMainService.get(ZbProductTypeMainEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZbProductTypeMainPage zbProductTypeMainPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZbProductTypeMainPage>> failures = validator.validate(zbProductTypeMainPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<ZbProductTypeHEntity> zbProductTypeHList =  zbProductTypeMainPage.getZbProductTypeHList();
		
		ZbProductTypeMainEntity zbProductTypeMain = new ZbProductTypeMainEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(zbProductTypeMain,zbProductTypeMainPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		zbProductTypeMainService.addMain(zbProductTypeMain, zbProductTypeHList);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zbProductTypeMainPage.getId();
		URI uri = uriBuilder.path("/rest/zbProductTypeMainController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZbProductTypeMainPage zbProductTypeMainPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZbProductTypeMainPage>> failures = validator.validate(zbProductTypeMainPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<ZbProductTypeHEntity> zbProductTypeHList =  zbProductTypeMainPage.getZbProductTypeHList();
		
		ZbProductTypeMainEntity zbProductTypeMain = new ZbProductTypeMainEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(zbProductTypeMain,zbProductTypeMainPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		zbProductTypeMainService.updateMain(zbProductTypeMain, zbProductTypeHList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ZbProductTypeMainEntity zbProductTypeMain = zbProductTypeMainService.get(ZbProductTypeMainEntity.class, id);
		zbProductTypeMainService.delMain(zbProductTypeMain);
	}
}