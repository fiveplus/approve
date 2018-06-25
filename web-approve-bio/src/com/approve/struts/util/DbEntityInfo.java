package com.approve.struts.util;
/**
 * 类拷贝实体对应类
 * @author Mr.zhang
 *
 */
public class DbEntityInfo {
	private String name;
	private String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public DbEntityInfo(){}
	public DbEntityInfo(String name,String type){
		this.name=name;
		this.type=type;
	}
}
