package com.approve.services.serv;

import java.util.List;

import com.approve.persistence.entity.Department;
import com.approve.services.BaseInterface;

public interface DepartmentServ extends BaseInterface{
	public int delete(String id);
	public int update(Department d);
	public Department getDepartmentById(String id);
	public int insert(Department d);
	public List<Department> getList();
	
	public Department getDepartmentByName(String name);
	
}
