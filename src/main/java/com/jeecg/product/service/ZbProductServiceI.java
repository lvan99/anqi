package com.jeecg.product.service;
import com.jeecg.product.entity.ZbProductEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ZbProductServiceI extends CommonService{
	
 	public void delete(ZbProductEntity entity) throws Exception;
 	
 	public Serializable save(ZbProductEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZbProductEntity entity) throws Exception;
 	
}
