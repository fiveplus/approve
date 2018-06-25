package com.approve.struts.dept;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.approve.persistence.entity.Department;
import com.approve.services.KeyValue;
import com.approve.struts.BaseAction;
import com.approve.struts.util.StringUtil;

public class DepartmentAction extends BaseAction{
	
	private List<Department> depts;
	private Department dept;
	
	public List<Department> getDepts() {
		return depts;
	}

	public void setDepts(List<Department> depts) {
		this.depts = depts;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}
	
	public String list() throws Exception{
		List<KeyValue> ands = new ArrayList<KeyValue>();
		ands.add(new KeyValue("status", "Y"));
		
		int count = departmentServ.getCount(Department.class, ands, null);
		pu.initPage(Department.class, "dept", count);
		depts = departmentServ.getList(pu, ands, null);
		request.setAttribute("pu", pu);
		return SUCCESS;
	}
	
	public String addinit() throws Exception{
		request.setAttribute("dept", dept);
		return SUCCESS;
	}
	
	public String add() throws Exception{
		try {
			Department d = departmentServ.getDepartmentByName(dept.getName());
			if(d!= null){
				message = "× 部门名称重复，请检查后重新输入!";
			}else{
				
				if(dept.getName().equals("")){
					message = "× 请输入部门名称！";
				}else{
					dept.setCreateTime(StringUtil.getDateToLong(new Date()));
					dept.setStatus("Y");
					int flag = departmentServ.insert(dept);
					if(flag > 0){
						message = "√ 恭喜您，部门创建成功!";
						returnURL = "dept/list.htm?pu.pageNum=1";
					}else{
						message = "× 很抱歉，部门创建失败!";
						request.setAttribute("dept", dept);
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			message = e.toString();
			request.setAttribute("dept", dept);
		}
		return SUCCESS;
	}
	
	public String updateInit() throws Exception{
		dept = departmentServ.getDepartmentById(id);
		return SUCCESS;
	}
	
	public String update() throws Exception{
		try {
			Department d = departmentServ.getDepartmentByName(dept.getName());
			if(d!= null && !d.getName().equals(dept.getName())){
				message = "× 部门名称重复，请检查后重新输入!";
				request.setAttribute("dept", dept);
			}else{
				
				if(dept.getName().equals("")){
					message = "× 请输入部门名称！";
				}else{
					int flag = departmentServ.update(dept);
					if(flag > 0){
						message = "√ 部门修改成功!";
						returnURL = "dept/list.htm?pu.pageNum=1";
					}else{
						message = "× 很抱歉，部门修改失败!";
						request.setAttribute("dept", dept);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = e.toString();
			request.setAttribute("dept", dept);
		}
		return SUCCESS;
	}
	
	public void delete() throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		message = "√ 恭喜您，部门删除成功!";

		dept = departmentServ.getDepartmentById(id);
		
		if(dept.getStatus().equals("Y")){
			dept.setStatus("N");
			departmentServ.update(dept);
		}else{
			message = "× 部门已经删除!";
		}
		
		out.print(message);
		
		out.flush();
		out.close();
	}
	
	
	
	
	
	
}
