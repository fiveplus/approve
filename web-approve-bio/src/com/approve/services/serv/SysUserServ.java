package com.approve.services.serv;

import java.util.List;

import com.approve.persistence.entity.SysUser;
import com.approve.services.BaseInterface;

public interface SysUserServ extends BaseInterface{
	public SysUser getSysUserById(String id);
	public int update(SysUser sysUser);
	public int delete(String id);
	public int insert(SysUser sysUser);
	public SysUser getSysUserByLoginNameAndPassword(String loginName,String password);
	public List<SysUser> getSysUserByDeptId(String deptId);
	public List<SysUser> getSysUserByPost(String post);

	public List<SysUser> getSysUserByPostAndDeptId(String post,String deptId);
	
	public SysUser getSysUserByLoginName(String loginName);
	
	public int getUserCountByDeptId(String id);
}
