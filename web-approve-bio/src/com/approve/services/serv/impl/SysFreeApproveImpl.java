package com.approve.services.serv.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.approve.persistence.entity.SysFreeApprove;
import com.approve.services.BaseService;
import com.approve.services.serv.SysFreeApproveServ;

public class SysFreeApproveImpl extends BaseService implements SysFreeApproveServ{

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)	
	public int deleteAll() {
		int flag = 1;
		try {
			String hql = "delete SysFreeApprove fa where 1 = 1 ";
			Session session = this.baseDAO.getBaseSession();
			Query query = session.createQuery(hql);
			query.executeUpdate();
			
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	public List<SysFreeApprove> getAllFreeApproveList() {
		return this.baseDAO.findAll(SysFreeApprove.class);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int insert(SysFreeApprove fa) {
		int flag = 1;
		try {
			this.baseDAO.save(fa);
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	public SysFreeApprove getSysFreeApproveByUserId(String userId) {
		String hqlName = "getSysFreeApproveByUserId";
		List<SysFreeApprove> list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName,"userId",userId);
		SysFreeApprove fa = null;
		if(list != null && list.size()>0){
			fa = list.get(0);
		}
		return fa;
	}

}
