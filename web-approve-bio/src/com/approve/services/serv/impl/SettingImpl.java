package com.approve.services.serv.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.approve.persistence.entity.Setting;
import com.approve.services.BaseService;
import com.approve.services.serv.SettingServ;
import com.approve.struts.util.BeanCopyUtil;

public class SettingImpl extends BaseService implements SettingServ{

	
	public Setting getSetting() {
		Setting set = null;
		List<Setting> list = this.baseDAO.findAll(Setting.class);
		if(list != null && list.size() > 0){
			set = list.get(0);
		}
		return set;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int insert(Setting setting) {
		int flag = 1;
		try {
			this.baseDAO.save(setting);
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int update(Setting setting) {
		int flag = 1;
		try {
			Setting temp = (Setting) this.baseDAO.findById(Setting.class, setting.getId());
			if(temp != null){
				BeanCopyUtil.beanCopy(setting, temp);
				this.baseDAO.update(temp);
			}
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}
	
	
	
}
