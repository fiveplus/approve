package com.approve.services.serv.impl;


import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.approve.persistence.entity.StaticSetting;
import com.approve.services.BaseService;
import com.approve.services.serv.StaticSettingServ;
import com.approve.struts.util.BeanCopyUtil;

public class StaticSettingImpl extends BaseService implements StaticSettingServ{

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int insert(StaticSetting staticSetting) {
		int flag = 1;
		try{
			this.baseDAO.save(staticSetting);
		}catch(Exception e){
			flag = -1;
		}
		return flag;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int update(StaticSetting staticSetting) {
		int flag = 1;
		try{
			StaticSetting temp = (StaticSetting)this.baseDAO.findById(StaticSetting.class, staticSetting.getId());
			if(temp != null){
				BeanCopyUtil.beanCopy(staticSetting, temp);
				this.baseDAO.update(temp);
			}
		}catch(Exception e){
			flag = -1;
		}
		return flag;
	}

	public StaticSetting getStaticSettingByColumnName(String columnName) {
		String hqlName = "getStaticSettingByColumnName";
		List list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName,"columnName",columnName);
		StaticSetting staticSetting = null;
		if(list != null && list.size()>0){
			staticSetting = (StaticSetting)list.get(0);
		}
		return staticSetting;
	}

}
