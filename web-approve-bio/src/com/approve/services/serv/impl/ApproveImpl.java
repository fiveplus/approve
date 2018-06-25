package com.approve.services.serv.impl;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.approve.persistence.entity.Approve;
import com.approve.services.BaseService;
import com.approve.services.serv.ApproveServ;
import com.approve.struts.util.BeanCopyUtil;

public class ApproveImpl extends BaseService implements ApproveServ{

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int delete(String id) {
		int flag = 1;
		try {
			Approve a = (Approve)this.baseDAO.findById(Approve.class, id);
			if(a != null){
				this.baseDAO.delete(a);
			}
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	public Approve getApproveById(String id) {
		return (Approve)this.baseDAO.findById(Approve.class, id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int insert(Approve approve) {
		int flag = 1;
		try {
			this.baseDAO.save(approve);
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int update(Approve approve) {
		int flag = 1;
		try {
			Approve temp = (Approve)this.baseDAO.findById(Approve.class, approve.getId());
			if(temp != null){
				BeanCopyUtil.beanCopy(approve, temp);
				
				if(approve.getApprovePeople()!=null){
					if(approve.getApprovePeople().getId().equals("")){
						temp.setApprovePeople(null);
					}
				}
				
				if(approve.getManager()!=null){
					if(approve.getManager().getId().equals("")){
						temp.setManager(null);
					}
				}
				if(approve.getChairman()!=null){
					if(approve.getChairman().getId().equals("")){
						temp.setChairman(null);
					}
				}
				
				if(approve.getLayera()!=null){
					if(approve.getLayera().getId().equals("")){
						temp.setLayera(null);
					}
				}
				
				if(approve.getLayerb()!=null){
					if(approve.getLayerb().getId().equals("")){
						temp.setLayerb(null);
					}
				}
				if(approve.getOffice()!=null){
					if(approve.getOffice().getId().equals("")){
						temp.setOffice(null);
					}
				}
				
				if(approve.getAtype()!=null){
					if(approve.getAtype().getId().equals("")){
						temp.setAtype(null);
					}
				}
				
				this.baseDAO.update(temp);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			flag = -1;
		}
		return flag;
	}

	public List<Approve> getList() {
		String hqlName = "getApproveList";
		return this.baseDAO.findByNamedQueryAndNamedParam(hqlName);
	}

	public Approve getApproveByName(String name) {
		String hqlName = "getApproveByName";
		List<Approve> list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName,"name",name);
		Approve a = null;
		if(list != null && list.size() > 0){
			a = list.get(0);
		}
		return a;
	}

}
