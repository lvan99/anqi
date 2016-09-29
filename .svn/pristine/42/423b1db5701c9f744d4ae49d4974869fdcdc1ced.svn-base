package com.jeecg.nutrition.service;
import com.jeecg.nutrition.entity.ZbFaqTypeEntity;
import com.jeecg.nutrition.entity.ZbFaqEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface ZbFaqTypeServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ZbFaqTypeEntity zbFaqType,
	        List<ZbFaqEntity> zbFaqList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ZbFaqTypeEntity zbFaqType,
	        List<ZbFaqEntity> zbFaqList);
	public void delMain (ZbFaqTypeEntity zbFaqType);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZbFaqTypeEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZbFaqTypeEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZbFaqTypeEntity t);
}
