package com.jeecg.nutrition.service.impl;
import com.jeecg.nutrition.service.ZbSolutionTypeServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.nutrition.entity.ZbSolutionTypeEntity;
import com.jeecg.nutrition.entity.ZbSolutionEntity;

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


@Service("zbSolutionTypeService")
@Transactional
public class ZbSolutionTypeServiceImpl extends CommonServiceImpl implements ZbSolutionTypeServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ZbSolutionTypeEntity)entity);
 	}
	
	public void addMain(ZbSolutionTypeEntity zbSolutionType,
	        List<ZbSolutionEntity> zbSolutionList){
			//保存主信息
			zbSolutionType.setDr("0");
			this.save(zbSolutionType);
		
			/**保存-健康解决方案详情*/
			for(ZbSolutionEntity zbSolution:zbSolutionList){
				//外键设置
				zbSolution.setPkSolutionType(zbSolutionType.getId());
				zbSolution.setDr("0");
				this.save(zbSolution);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(zbSolutionType);
	}

	
	public void updateMain(ZbSolutionTypeEntity zbSolutionType,
	        List<ZbSolutionEntity> zbSolutionList) {
		//保存主表信息
		this.saveOrUpdate(zbSolutionType);
		//===================================================================================
		//获取参数
		Object pK_SOLUTION_TYPE0 = zbSolutionType.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-健康解决方案详情
	    String hql0 = "from ZbSolutionEntity where dr = '0' AND pK_SOLUTION_TYPE = ? ";
	    List<ZbSolutionEntity> zbSolutionOldList = this.findHql(hql0,pK_SOLUTION_TYPE0);
		//2.筛选更新明细数据-健康解决方案详情
//		if(zbSolutionList！=null&&zbSolutionList.size()>0){
		for(ZbSolutionEntity oldE:zbSolutionOldList){
			boolean isUpdate = false;
				for(ZbSolutionEntity sendE:zbSolutionList){
					//需要更新的明细数据-健康解决方案详情
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-健康解决方案详情
	    			oldE.setDr("1");
	    			this.saveOrUpdate(oldE);
//		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-健康解决方案详情
			for(ZbSolutionEntity zbSolution:zbSolutionList){
				if(oConvertUtils.isEmpty(zbSolution.getId())){
					//外键设置
					zbSolution.setPkSolutionType(zbSolutionType.getId());
					zbSolution.setDr("0");
					this.save(zbSolution);
				}
			}
//		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(zbSolutionType);
	}

	
	public void delMain(ZbSolutionTypeEntity zbSolutionType) {
		//删除主表信息
		this.delete(zbSolutionType);
		//===================================================================================
		//获取参数
		Object pK_SOLUTION_TYPE0 = zbSolutionType.getId();
		//===================================================================================
		//删除-健康解决方案详情
	    String hql0 = "from ZbSolutionEntity where dr = '0' AND pK_SOLUTION_TYPE = ? ";
	    List<ZbSolutionEntity> zbSolutionOldList = this.findHql(hql0,pK_SOLUTION_TYPE0);
		this.deleteAllEntitie(zbSolutionOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZbSolutionTypeEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZbSolutionTypeEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZbSolutionTypeEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,ZbSolutionTypeEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{code}",String.valueOf(t.getCode()));
 		sql  = sql.replace("#{type_name}",String.valueOf(t.getTypeName()));
 		sql  = sql.replace("#{dr}",String.valueOf(t.getDr()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}