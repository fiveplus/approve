package com.approve.services.serv;

import com.approve.persistence.entity.StaticSetting;
import com.approve.services.BaseInterface;

public interface StaticSettingServ extends BaseInterface{
	public int insert(StaticSetting staticSetting);
	public int update(StaticSetting staticSetting);
	public StaticSetting getStaticSettingByColumnName(String columnName);
}
