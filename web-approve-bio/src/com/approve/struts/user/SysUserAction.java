package com.approve.struts.user;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.approve.persistence.entity.Department;
import com.approve.persistence.entity.SysUser;
import com.approve.persistence.entity.UserDept;
import com.approve.services.KeyValue;
import com.approve.struts.BaseAction;
import com.approve.struts.util.MD5;
import com.approve.struts.util.StringUtil;

public class SysUserAction extends BaseAction{
	private List<SysUser> users;
	private SysUser us;
	private List<Department> depts;
	
	private String oldpass;
	private String newpass;
	
	public String getOldpass() {
		return oldpass;
	}
	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}
	public String getNewpass() {
		return newpass;
	}
	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}
	public List<SysUser> getUsers() {
		return users;
	}
	public void setUsers(List<SysUser> users) {
		this.users = users;
	}
	public SysUser getUs() {
		return us;
	}
	public void setUs(SysUser us) {
		this.us = us;
	}
	public List<Department> getDepts() {
		return depts;
	}
	public void setDepts(List<Department> depts) {
		this.depts = depts;
	}
	
	public String list() throws Exception{
		List<KeyValue> ands = new ArrayList<KeyValue>();
		ands.add(new KeyValue("status", "Y"));
		ands.add(new KeyValue("isAdmin","N"));
		
		List<KeyValue> likes = new ArrayList<KeyValue>();
		
		selectValue = selectValue==null?null:selectValue.trim();
		if(selectValue !=null && !selectValue.equals("")){
			likes.add(new KeyValue("userName", "%"+selectValue+"%"));
			request.setAttribute("selectValue", selectValue);
		}
		
		int count = sysUserServ.getCount(SysUser.class, ands, likes);
		pu.initPage(SysUser.class, "user", count);
		
		
		users = sysUserServ.getList(pu, ands,likes);
		request.setAttribute("pu", pu);
		return SUCCESS;
	}
	
	public String addinit() throws Exception{
		depts = departmentServ.getList();
		request.setAttribute("us", us);
		return SUCCESS;
	}
	public String add() throws Exception{
		try {
			SysUser u = sysUserServ.getSysUserByLoginName(us.getLoginName());
			if(u!=null){
				message = "× 用户账号重复，请检查后重新输入!";
				request.setAttribute("us", us);
			}else{
				
				if(us.getUserName().equals("")){
					message = "× 请输入用户账号！";
					return SUCCESS;
				}
				if(us.getLoginName().equals("")){
					message = "× 请输入用户姓名！";
					return SUCCESS;
				}
				if(us.getDept() == null){
					message = "× 请选择用户部门!";
					return SUCCESS;
				}
				
				us.setCreateTime(StringUtil.getDateToLong(new Date()));
				us.setIsAdmin("N");
				us.setStatus("Y");
				us.setRemark("");
				us.setPassword(MD5.GetMD5Password("123456"));
				int flag = sysUserServ.insert(us);
				if(flag > 0){
					message = "√ 恭喜您，用户创建成功!";
					returnURL = "user/list.htm?pu.pageNum=1";
				}else{
					message = "× 很抱歉，用户创建失败!";
					request.setAttribute("us", us);
				}
				

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			message = e.toString();
		}
		return SUCCESS;
	}
	
	public String updateInit() throws Exception{
		depts = departmentServ.getList();
		
		us = sysUserServ.getSysUserById(id);
		
		return SUCCESS;
	}
	
	public String update() throws Exception{
		try {
			SysUser u = sysUserServ.getSysUserByLoginName(us.getLoginName());
			if(u!=null && !u.getLoginName().equals(us.getLoginName())){
				message = "× 用户名重复，请检查后重新输入!";
			}else{
					
				if(us.getUserName().equals("")){
					message = "× 请输入用户账号！";
					return SUCCESS;
				}
				if(us.getLoginName().equals("")){
					message = "× 请输入用户姓名！";
					return SUCCESS;
				}
				if(us.getDept() == null){
					message = "× 请选择用户部门!";
					return SUCCESS;
				}
				
				if(us.getPassword().equals("")){
					us.setPassword(null);
				}
				int flag = sysUserServ.update(us);
				if(flag > 0){
					message = "√ 恭喜您，用户修改成功!";
					returnURL = "user/list.htm?pu.pageNum=1";
				}else{
					message = "× 很抱歉，用户修改失败!";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = e.toString();
		}
		return SUCCESS;
	}
	
	public String update_this_init() throws Exception{
		us = sysUserServ.getSysUserById(id);
		return SUCCESS;
	}
	
	public String update_user() throws Exception{
		try {
			String oldmd = MD5.GetMD5Password(oldpass);
			SysUser old = sysUserServ.getSysUserById(us.getId());
			if(old.getPassword().equals(oldmd)){
				us.setPassword(newpass);
				int flag = sysUserServ.update(us);
				if(flag > 0){
					message = "√ 恭喜您，密码修改成功!";
					returnURL = "system/index.htm";
				}else{
					message = "× 很抱歉，密码修改失败!";
				}
			}else{
				message = "× 很抱歉，原密码输入错误，修改失败!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = e.toString();
		}
		return SUCCESS;
	}
	
	
	public void delete() throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		message = "√ 恭喜您，用户删除成功!";
		
		us = sysUserServ.getSysUserById(id);
		if(us.getStatus().equals("Y")){
			us.setStatus("N");
			sysUserServ.update(us);
		}else if(us.getStatus().equals("N")){
			message = "× 用户已经删除!";
		}
		
		out.print(message);
		
		out.flush();
		out.close();
	}
	
	public String user_dept_init() throws Exception{
		
		List<Department> depts = departmentServ.getList();
		
		List<UserDept> uds = userDeptServ.getUserDeptByUserId(id);
		
		us = sysUserServ.getSysUserById(id);
		
		request.setAttribute("depts", depts);
		request.setAttribute("uds", uds);
		request.setAttribute("us", us);
		return SUCCESS;
	}
	
	private String ids;
	private String userId;
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getIds() {
		return ids;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	
	
	public String user_dept() throws Exception{
		
		message = "√ 恭喜您，部门分配成功!";
		int flag = userDeptServ.deleteByUserid(userId);
		if(flag > 0){
			boolean f = true;
			if(ids != null){
				String[] deptids = ids.split(",");
				for(String deptid:deptids){
					UserDept ud = userDeptServ.getUserDeptByDeptId(deptid);
					if(ud !=null){
						message = "× 分配失败，有部门已被分配，请重新分配!";
						f = false;
						break;
					}
				}
			}
			if(f && ids != null){
				String[] deptids = ids.split(",");
				for(String deptid:deptids){
					UserDept ud = new UserDept();
					ud.setDept(departmentServ.getDepartmentById(deptid.trim()));
					ud.setUser(sysUserServ.getSysUserById(userId));
					userDeptServ.insert(ud);
					message = "√ 恭喜您，部门分配成功!";
					returnURL = "user/user_dept_init.htm?id="+userId;
				}
			}
		}else{
			message = "× 系统繁忙，请稍后重试!";
		}
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
}
