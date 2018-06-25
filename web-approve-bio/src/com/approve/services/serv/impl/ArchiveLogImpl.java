package com.approve.services.serv.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.approve.persistence.entity.ArchiveLog;
import com.approve.services.BaseService;
import com.approve.services.serv.ArchiveLogServ;
import com.approve.struts.util.BeanCopyUtil;

public class ArchiveLogImpl extends BaseService implements ArchiveLogServ{

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int insert(ArchiveLog al) {
		int flag = 1;
		try {
			this.baseDAO.save(al);
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	public ArchiveLog getArchiveLogById(String id) {
		return (ArchiveLog)this.baseDAO.findById(ArchiveLog.class, id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int update(ArchiveLog al) {
		int flag = 1;
		try {
			ArchiveLog temp = (ArchiveLog)this.baseDAO.findById(ArchiveLog.class, al.getId());
			if(temp != null){
				BeanCopyUtil.beanCopy(al, temp);
				this.baseDAO.update(temp);
			}
			
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int delete(String id) {
		int flag = 1;
		try {
			ArchiveLog al = (ArchiveLog)this.baseDAO.findById(ArchiveLog.class, id);
			if(al != null){
				this.baseDAO.delete(al);
			}
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}
	
}
