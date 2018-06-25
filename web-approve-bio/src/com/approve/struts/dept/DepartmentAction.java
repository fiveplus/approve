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
				message = "�� ���������ظ����������������!";
			}else{
				
				if(dept.getName().equals("")){
					message = "�� �����벿�����ƣ�";
				}else{
					dept.setCreateTime(StringUtil.getDateToLong(new Date()));
					dept.setStatus("Y");
					int flag = departmentServ.insert(dept);
					if(flag > 0){
						message = "�� ��ϲ�������Ŵ����ɹ�!";
						returnURL = "dept/list.htm?pu.pageNum=1";
					}else{
						message = "�� �ܱ�Ǹ�����Ŵ���ʧ��!";
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
				message = "�� ���������ظ����������������!";
				request.setAttribute("dept", dept);
			}else{
				
				if(dept.getName().equals("")){
					message = "�� �����벿�����ƣ�";
				}else{
					int flag = departmentServ.update(dept);
					if(flag > 0){
						message = "�� �����޸ĳɹ�!";
						returnURL = "dept/list.htm?pu.pageNum=1";
					}else{
						message = "�� �ܱ�Ǹ�������޸�ʧ��!";
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

		message = "�� ��ϲ��������ɾ���ɹ�!";

		dept = departmentServ.getDepartmentById(id);
		
		if(dept.getStatus().equals("Y")){
			dept.setStatus("N");
			departmentServ.update(dept);
		}else{
			message = "�� �����Ѿ�ɾ��!";
		}
		
		out.print(message);
		
		out.flush();
		out.close();
	}
	
	
	
	
	
	
}
