package com.jeecg.product.service.impl;
import com.jeecg.product.service.ZbProductTypeMainServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.product.entity.ZbProductTypeMainEntity;
import com.jeecg.product.entity.ZbProductTypeHEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;


@Service("zbProductTypeMainService")
@Transactional
public class ZbProductTypeMainServiceImpl extends CommonServiceImpl implements ZbProductTypeMainServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ZbProductTypeMainEntity)entity);
 	}
	
	public void addMain(ZbProductTypeMainEntity zbProductTypeMain,
	        List<ZbProductTypeHEntity> zbProductTypeHList){
			//保存主信息
			zbProductTypeMain.setDr("0");
			this.save(zbProductTypeMain);
		
			/**保存-子分类*/
			for(ZbProductTypeHEntity zbProductTypeH:zbProductTypeHList){
				//外键设置
				zbProductTypeH.setMainType(zbProductTypeMain.getId());
				zbProductTypeH.setDr("0");
				this.save(zbProductTypeH);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(zbProductTypeMain);
	}

	
	public void updateMain(ZbProductTypeMainEntity zbProductTypeMain,
	        List<ZbProductTypeHEntity> zbProductTypeHList) {
		//保存主表信息
		this.saveOrUpdate(zbProductTypeMain);
		//===================================================================================
		//获取参数
		Object main_type0 = zbProductTypeMain.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-子分类
	    String hql0 = "from ZbProductTypeHEntity where dr = '0' AND main_type = ? ";
	    List<ZbProductTypeHEntity> zbProductTypeHOldList = this.findHql(hql0,main_type0);
		//2.筛选更新明细数据-子分类
//		if(zbProductTypeHList!=null&&zbProductTypeHList.size()>0){
		for(ZbProductTypeHEntity oldE:zbProductTypeHOldList){
			boolean isUpdate = false;
				for(ZbProductTypeHEntity sendE:zbProductTypeHList){
					//需要更新的明细数据-子分类
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-子分类
		    		oldE.setDr("1");
		    		this.saveOrUpdate(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-子分类
			for(ZbProductTypeHEntity zbProductTypeH:zbProductTypeHList){
				if(oConvertUtils.isEmpty(zbProductTypeH.getId())){
					//外键设置
					zbProductTypeH.setMainType(zbProductTypeMain.getId());
					zbProductTypeH.setDr("0");
					this.save(zbProductTypeH);
				}
			}
//		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(zbProductTypeMain);
	}

	
	public void delMain(ZbProductTypeMainEntity zbProductTypeMain) {
		//删除主表信息
		this.delete(zbProductTypeMain);
		//===================================================================================
		//获取参数
		Object main_type0 = zbProductTypeMain.getId();
		//===================================================================================
		//删除-子分类
	    String hql0 = "from ZbProductTypeHEntity where 1 = 1 AND main_type = ? ";
	    List<ZbProductTypeHEntity> zbProductTypeHOldList = this.findHql(hql0,main_type0);
		this.deleteAllEntitie(zbProductTypeHOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZbProductTypeMainEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZbProductTypeMainEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZbProductTypeMainEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,ZbProductTypeMainEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{code}",String.valueOf(t.getCode()));
 		sql  = sql.replace("#{main_type_name}",String.valueOf(t.getMainTypeName()));
 		sql  = sql.replace("#{dr}",String.valueOf(t.getDr()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}