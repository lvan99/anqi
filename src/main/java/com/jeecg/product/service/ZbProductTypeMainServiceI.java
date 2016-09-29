package com.jeecg.product.service;
import com.jeecg.product.entity.ZbProductTypeMainEntity;
import com.jeecg.product.entity.ZbProductTypeHEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface ZbProductTypeMainServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ZbProductTypeMainEntity zbProductTypeMain,
	        List<ZbProductTypeHEntity> zbProductTypeHList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ZbProductTypeMainEntity zbProductTypeMain,
	        List<ZbProductTypeHEntity> zbProductTypeHList);
	public void delMain (ZbProductTypeMainEntity zbProductTypeMain);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZbProductTypeMainEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZbProductTypeMainEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZbProductTypeMainEntity t);
}
