package com.approve.services.serv.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.approve.persistence.entity.ApproveType;
import com.approve.services.BaseService;
import com.approve.services.serv.ApproveTypeServ;
import com.approve.struts.util.BeanCopyUtil;

public class ApproveTypeImpl extends BaseService implements ApproveTypeServ{

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int update(ApproveType type) {
		int flag = 1;
		try {
			ApproveType temp = (ApproveType)this.baseDAO.findById(ApproveType.class, type.getId());
			if(temp != null){
				BeanCopyUtil.beanCopy(type, temp);
				this.baseDAO.update(temp);
			}
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int insert(ApproveType type) {
		int flag = 1;
		try {
			this.baseDAO.save(type);
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int delete(String id) {
		int flag = 1;
		try{
			ApproveType at = (ApproveType)this.baseDAO.findById(ApproveType.class, id);
			if(at != null){
				this.baseDAO.delete(at);
			}
		}catch(Exception e){
			flag = -1;
		}
		return flag;
	}

	public ApproveType getApproveTypeById(String id) {
		return (ApproveType)this.baseDAO.findById(ApproveType.class, id);
	}

	public List<ApproveType> getList() {
		String hqlName = "getApproveTypeList";
		List<ApproveType> list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName);
		return list;
	}

	public ApproveType getApproveTypeByName(String name) {
		String hqlName = "getApproveTypeByName";
		List<ApproveType> list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName,"name",name);
		ApproveType type = null;
		if(list != null && list.size() > 0){
			type = list.get(0);
		}
		return type;
	}
	
}
