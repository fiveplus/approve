package com.approve.services.serv.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.approve.persistence.entity.Department;
import com.approve.services.BaseService;
import com.approve.services.serv.DepartmentServ;
import com.approve.struts.util.BeanCopyUtil;

public class DepartmentImpl extends BaseService implements DepartmentServ{

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int delete(String id) {
		int flag = 1;
		try {
			Department d = (Department)this.baseDAO.findById(Department.class, id);
			if(d != null){
				this.baseDAO.delete(d);
			}
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	public Department getDepartmentById(String id) {
		return (Department)this.baseDAO.findById(Department.class, id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int insert(Department d) {
		int flag = 1;
		try {
			this.baseDAO.save(d);
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int update(Department d) {
		int flag = 1;
		try {
			Department temp = (Department)this.baseDAO.findById(Department.class, d.getId());
			if(temp != null){
				BeanCopyUtil.beanCopy(d, temp);
				this.baseDAO.update(temp);
			}
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	public List<Department> getList(){
		String hqlName = "getDepartmentList";
		return this.baseDAO.findByNamedQueryAndNamedParam(hqlName);
	}

	

	public Department getDepartmentByName(String name) {
		String hqlName = "getDepartmentByName";
		List<Department> list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName, "name", name);
		Department d = null;
		if(list != null && list.size() > 0){
			d = list.get(0);
		}
		return d;
	}
	
}
