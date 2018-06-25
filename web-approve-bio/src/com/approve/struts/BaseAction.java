package com.approve.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.approve.persistence.entity.ApproveType;
import com.approve.persistence.entity.SysUser;
import com.approve.services.serv.ApproveLogServ;
import com.approve.services.serv.ApproveServ;
import com.approve.services.serv.ApproveTypeServ;
import com.approve.services.serv.ArchiveLogServ;
import com.approve.services.serv.DepartmentServ;
import com.approve.services.serv.SettingServ;
import com.approve.services.serv.StaticSettingServ;
import com.approve.services.serv.SysFreeApproveServ;
import com.approve.services.serv.SysUserServ;
import com.approve.services.serv.UserApproveServ;
import com.approve.services.serv.UserDeptServ;
import com.approve.struts.util.PageUtil;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction<T> extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	protected String selectValue;
	protected String typeId;
	
	protected String backURL;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected String returnURL;
	protected String message;
	protected String id;
	protected PageUtil pu;
	
	protected SysUserServ sysUserServ;
	protected DepartmentServ departmentServ;
	protected ApproveServ approveServ;
	
	protected UserApproveServ userApproveServ;
	protected ApproveLogServ approveLogServ;
	
	protected ArchiveLogServ archiveLogServ;
	
	
	protected UserDeptServ userDeptServ;
	
	protected SettingServ settingServ;
	
	protected SysFreeApproveServ sysFreeApproveServ;
	
	protected StaticSettingServ staticSettingServ;
	
	protected ApproveTypeServ approveTypeServ;
	
	
	public ApproveTypeServ getApproveTypeServ() {
		return approveTypeServ;
	}

	public void setApproveTypeServ(ApproveTypeServ approveTypeServ) {
		this.approveTypeServ = approveTypeServ;
	}

	public StaticSettingServ getStaticSettingServ() {
		return staticSettingServ;
	}

	public void setStaticSettingServ(StaticSettingServ staticSettingServ) {
		this.staticSettingServ = staticSettingServ;
	}

	public ArchiveLogServ getArchiveLogServ() {
		return archiveLogServ;
	}

	public void setArchiveLogServ(ArchiveLogServ archiveLogServ) {
		this.archiveLogServ = archiveLogServ;
	}

	public UserApproveServ getUserApproveServ() {
		return userApproveServ;
	}

	public void setUserApproveServ(UserApproveServ userApproveServ) {
		this.userApproveServ = userApproveServ;
	}

	public ApproveLogServ getApproveLogServ() {
		return approveLogServ;
	}

	public void setApproveLogServ(ApproveLogServ approveLogServ) {
		this.approveLogServ = approveLogServ;
	}

	public DepartmentServ getDepartmentServ() {
		return departmentServ;
	}

	public void setDepartmentServ(DepartmentServ departmentServ) {
		this.departmentServ = departmentServ;
	}

	public SysUser getCurrentUser(){
		return (SysUser)request.getSession().getAttribute("user");
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getBackURL() {
		return backURL;
	}

	public void setBackURL(String backURL) {
		this.backURL = backURL;
	}

	public String getReturnURL() {
		return returnURL;
	}

	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PageUtil getPu() {
		return pu;
	}

	public void setPu(PageUtil pu) {
		this.pu = pu;
	}

	public SysUserServ getSysUserServ() {
		return sysUserServ;
	}

	public void setSysUserServ(SysUserServ sysUserServ) {
		this.sysUserServ = sysUserServ;
	}

	public void setApproveServ(ApproveServ approveServ) {
		this.approveServ = approveServ;
	}

	public ApproveServ getApproveServ() {
		return approveServ;
	}

	public void setUserDeptServ(UserDeptServ userDeptServ) {
		this.userDeptServ = userDeptServ;
	}

	public UserDeptServ getUserDeptServ() {
		return userDeptServ;
	}

	public void setSettingServ(SettingServ settingServ) {
		this.settingServ = settingServ;
	}

	public SettingServ getSettingServ() {
		return settingServ;
	}

	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}

	public String getSelectValue() {
		return selectValue;
	}

	public void setSysFreeApproveServ(SysFreeApproveServ sysFreeApproveServ) {
		this.sysFreeApproveServ = sysFreeApproveServ;
	}

	public SysFreeApproveServ getSysFreeApproveServ() {
		return sysFreeApproveServ;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	
}
