package com.approve.services.serv;

import com.approve.persistence.entity.Setting;
import com.approve.services.BaseInterface;

public interface SettingServ extends BaseInterface{
	public int insert(Setting setting);
	public int update(Setting setting);
	public Setting getSetting();
}
