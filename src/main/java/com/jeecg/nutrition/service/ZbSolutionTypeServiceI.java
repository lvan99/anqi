package com.jeecg.nutrition.service;
import com.jeecg.nutrition.entity.ZbSolutionTypeEntity;
import com.jeecg.nutrition.entity.ZbSolutionEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface ZbSolutionTypeServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ZbSolutionTypeEntity zbSolutionType,
	        List<ZbSolutionEntity> zbSolutionList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ZbSolutionTypeEntity zbSolutionType,
	        List<ZbSolutionEntity> zbSolutionList);
	public void delMain (ZbSolutionTypeEntity zbSolutionType);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZbSolutionTypeEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZbSolutionTypeEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZbSolutionTypeEntity t);
}
