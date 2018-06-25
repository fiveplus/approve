package com.approve.services.serv.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.approve.persistence.entity.UserDept;
import com.approve.services.BaseService;
import com.approve.services.serv.UserDeptServ;

public class UserDeptImpl extends BaseService implements UserDeptServ{

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int delete(String id) {
		int flag = 1;
		try {
			UserDept ud = (UserDept) this.baseDAO.findById(UserDept.class, id);
			if(ud != null){
				this.baseDAO.delete(ud);
			}
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	public UserDept getUserDeptByDeptId(String deptId) {
		String hqlName = "getUserDeptByDeptId";
		List<UserDept> list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName,"deptId",deptId);
		UserDept ud = null;
		if(list != null && list.size() > 0){
			ud = list.get(0);
		}
		return ud;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int insert(UserDept ud) {
		int flag = 1;
		try {
			this.baseDAO.save(ud);
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	public List<UserDept> getUserDeptByUserId(String userId) {
		String hqlName = "getUserDeptByUserId";
		List list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName,"userId",userId);
		return list;
	}

	public int deleteByUserid(String userId) {
		int flag = 1;
		try {
			String hql = "delete UserDept ud where ud.user.id=:userId";
			Session session = this.baseDAO.getBaseSession();
			Query query = session.createQuery(hql);
			query.setParameter("userId", userId);
			query.executeUpdate();
			
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

}
