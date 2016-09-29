package com.jeecg.nutrition.controller;
import com.jeecg.nutrition.entity.ZbSolutionTypeEntity;
import com.jeecg.nutrition.service.ZbSolutionTypeServiceI;
import com.jeecg.nutrition.page.ZbSolutionTypePage;
import com.jeecg.nutrition.entity.ZbSolutionEntity;
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
 * @Description: 健康解决方案分类
 * @author onlineGenerator
 * @date 2016-09-28 16:54:30
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zbSolutionTypeController")
public class ZbSolutionTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZbSolutionTypeController.class);

	@Autowired
	private ZbSolutionTypeServiceI zbSolutionTypeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 健康解决方案分类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/nutrition/zbSolutionTypeList");
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
	public void datagrid(ZbSolutionTypeEntity zbSolutionType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZbSolutionTypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zbSolutionType);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.eq("dr", "0");
		cq.add();
		this.zbSolutionTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除健康解决方案分类
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZbSolutionTypeEntity zbSolutionType, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		zbSolutionType = systemService.getEntity(ZbSolutionTypeEntity.class, zbSolutionType.getId());
		String message = "健康解决方案分类删除成功";
		try{
			//获取参数
			Object pK_SOLUTION_TYPE0 = zbSolutionType.getId();
		    String hql0 = "from ZbSolutionEntity where dr = '0' AND pK_SOLUTION_TYPE = ? ";
		    List<ZbSolutionEntity> zbSolutionOldList = zbSolutionTypeService.findHql(hql0,pK_SOLUTION_TYPE0);
		    if(zbSolutionOldList.size() == 0) {
		    	zbSolutionType.setDr("1");
		    	zbSolutionTypeService.saveOrUpdate(zbSolutionType);
		    	systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		    } else {
		    	message = "健康解决方案分类下还有解决方案详情，请先删除详情！";
		    }
		}catch(Exception e){
			e.printStackTrace();
			message = "健康解决方案分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除健康解决方案分类
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "健康解决方案分类删除成功";
		try{
			for(String id:ids.split(",")){
				ZbSolutionTypeEntity zbSolutionType = systemService.getEntity(ZbSolutionTypeEntity.class,id);
				//获取参数
				Object pK_SOLUTION_TYPE0 = zbSolutionType.getId();
			    String hql0 = "from ZbSolutionEntity where dr = '0' AND pK_SOLUTION_TYPE = ? ";
			    List<ZbSolutionEntity> zbSolutionOldList = zbSolutionTypeService.findHql(hql0,pK_SOLUTION_TYPE0);
			    if(zbSolutionOldList.size() == 0) {
			    	zbSolutionType.setDr("1");
			    	zbSolutionTypeService.saveOrUpdate(zbSolutionType);
			    	systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			    } else {
			    	message = "健康解决方案分类下还有解决方案详情，请先删除详情！";
			    }
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "健康解决方案分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加健康解决方案分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZbSolutionTypeEntity zbSolutionType,ZbSolutionTypePage zbSolutionTypePage, HttpServletRequest request) {
		List<ZbSolutionEntity> zbSolutionList =  zbSolutionTypePage.getZbSolutionList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			zbSolutionTypeService.addMain(zbSolutionType, zbSolutionList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "健康解决方案分类添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新健康解决方案分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZbSolutionTypeEntity zbSolutionType,ZbSolutionTypePage zbSolutionTypePage, HttpServletRequest request) {
		List<ZbSolutionEntity> zbSolutionList =  zbSolutionTypePage.getZbSolutionList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			zbSolutionTypeService.updateMain(zbSolutionType, zbSolutionList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新健康解决方案分类失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 健康解决方案分类新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZbSolutionTypeEntity zbSolutionType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zbSolutionType.getId())) {
			zbSolutionType = zbSolutionTypeService.getEntity(ZbSolutionTypeEntity.class, zbSolutionType.getId());
			req.setAttribute("zbSolutionTypePage", zbSolutionType);
		}
		return new ModelAndView("com/jeecg/nutrition/zbSolutionType-add");
	}
	
	/**
	 * 健康解决方案分类编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZbSolutionTypeEntity zbSolutionType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zbSolutionType.getId())) {
			zbSolutionType = zbSolutionTypeService.getEntity(ZbSolutionTypeEntity.class, zbSolutionType.getId());
			req.setAttribute("zbSolutionTypePage", zbSolutionType);
		}
		return new ModelAndView("com/jeecg/nutrition/zbSolutionType-update");
	}
	
	
	/**
	 * 加载明细列表[健康解决方案详情]
	 * 
	 * @return
	 */
	@RequestMapping(params = "zbSolutionList")
	public ModelAndView zbSolutionList(ZbSolutionTypeEntity zbSolutionType, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object pK_SOLUTION_TYPE0 = zbSolutionType.getId();
		//===================================================================================
		//查询-健康解决方案详情
	    String hql0 = "from ZbSolutionEntity where dr = '0' AND pK_SOLUTION_TYPE = ? ";
	    try{
	    	List<ZbSolutionEntity> zbSolutionEntityList = systemService.findHql(hql0,pK_SOLUTION_TYPE0);
				req.setAttribute("zbSolutionList", zbSolutionEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/nutrition/zbSolutionList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(ZbSolutionTypeEntity zbSolutionType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(ZbSolutionTypeEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zbSolutionType);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<ZbSolutionTypeEntity> list=this.zbSolutionTypeService.getListByCriteriaQuery(cq, false);
    	List<ZbSolutionTypePage> pageList=new ArrayList<ZbSolutionTypePage>();
        if(list!=null&&list.size()>0){
        	for(ZbSolutionTypeEntity entity:list){
        		try{
        		ZbSolutionTypePage page=new ZbSolutionTypePage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
           		    Object pK_SOLUTION_TYPE0 = entity.getId();
				    String hql0 = "from ZbSolutionEntity where dr = '0' AND pK_SOLUTION_TYPE = ? ";
        	        List<ZbSolutionEntity> zbSolutionEntityList = systemService.findHql(hql0,pK_SOLUTION_TYPE0);
            		page.setZbSolutionList(zbSolutionEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"健康解决方案分类");
        map.put(NormalExcelConstants.CLASS,ZbSolutionTypePage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("健康解决方案分类列表", "导出人:Jeecg",
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
				List<ZbSolutionTypePage> list =  ExcelImportUtil.importExcel(file.getInputStream(), ZbSolutionTypePage.class, params);
				ZbSolutionTypeEntity entity1=null;
				for (ZbSolutionTypePage page : list) {
					entity1=new ZbSolutionTypeEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            zbSolutionTypeService.addMain(entity1, page.getZbSolutionList());
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
		map.put(NormalExcelConstants.FILE_NAME,"健康解决方案分类");
		map.put(NormalExcelConstants.CLASS,ZbSolutionTypePage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("健康解决方案分类列表", "导出人:"+ ResourceUtil.getSessionUserName().getRealName(),
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
		req.setAttribute("controller_name", "zbSolutionTypeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZbSolutionTypeEntity> list() {
		List<ZbSolutionTypeEntity> listZbSolutionTypes=zbSolutionTypeService.getList(ZbSolutionTypeEntity.class);
		return listZbSolutionTypes;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZbSolutionTypeEntity task = zbSolutionTypeService.get(ZbSolutionTypeEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZbSolutionTypePage zbSolutionTypePage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZbSolutionTypePage>> failures = validator.validate(zbSolutionTypePage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<ZbSolutionEntity> zbSolutionList =  zbSolutionTypePage.getZbSolutionList();
		
		ZbSolutionTypeEntity zbSolutionType = new ZbSolutionTypeEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(zbSolutionType,zbSolutionTypePage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		zbSolutionTypeService.addMain(zbSolutionType, zbSolutionList);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zbSolutionTypePage.getId();
		URI uri = uriBuilder.path("/rest/zbSolutionTypeController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZbSolutionTypePage zbSolutionTypePage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZbSolutionTypePage>> failures = validator.validate(zbSolutionTypePage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<ZbSolutionEntity> zbSolutionList =  zbSolutionTypePage.getZbSolutionList();
		
		ZbSolutionTypeEntity zbSolutionType = new ZbSolutionTypeEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(zbSolutionType,zbSolutionTypePage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		zbSolutionTypeService.updateMain(zbSolutionType, zbSolutionList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ZbSolutionTypeEntity zbSolutionType = zbSolutionTypeService.get(ZbSolutionTypeEntity.class, id);
		zbSolutionTypeService.delMain(zbSolutionType);
	}
}
