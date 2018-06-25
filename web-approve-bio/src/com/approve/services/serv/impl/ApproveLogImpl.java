package com.approve.services.serv.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.approve.persistence.entity.ApproveLog;
import com.approve.services.BaseService;
import com.approve.services.serv.ApproveLogServ;
import com.approve.struts.util.BeanCopyUtil;

public class ApproveLogImpl extends BaseService implements ApproveLogServ{

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int delete(String id) {
		int flag = 1;
		try {
			ApproveLog log = (ApproveLog)this.baseDAO.findById(ApproveLog.class, id);
			if(log != null){
				this.baseDAO.delete(log);
			}
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	public ApproveLog getApproveLogById(String id) {
		return (ApproveLog)this.baseDAO.findById(ApproveLog.class, id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int insert(ApproveLog log) {
		int flag = 1;
		try {
			this.baseDAO.save(log);
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int update(ApproveLog log) {
		int flag = 1;
		try {
			ApproveLog temp = (ApproveLog)this.baseDAO.findById(ApproveLog.class, log.getId());
			if(temp != null){
				BeanCopyUtil.beanCopy(log, temp);
				this.baseDAO.update(temp);
			}
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	public List<ApproveLog> getListToUser(String userId) {
		String hqlName = "getListToUser";
		List list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName,"userId",userId);
		return list;
	}

	public List<ApproveLog> getListToUaidAndStage(String uaid,Integer stage) {
		String hqlName = "getListToUaidAndStage";
		List list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName,new String[]{"uaid","stage","status"},new Object[]{uaid,stage,"W"});
		return list;
	}
	
	

	public List<ApproveLog> getListByUaid(String uaid) {
		String hqlName = "getListByUaid";
		List list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName,"uaid",uaid);
		return list;
	}

	public List<ApproveLog> getListToUaidAndStageForYorN(String uaid,
			Integer stage) {
		String hqlName = "getListToUaidAndStageForYorN";
		List list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName,new String[]{"uaid","stage"},new Object[]{uaid,stage});
		return list;
	}

	public List<ApproveLog> getListToUaidForYorN(String uaid) {
		String hqlName = "getListToUaidForYorN";
		List list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName,"uaid",uaid);
		return list;
	}

	public List<ApproveLog> getListByUaidAndMaxStage(String uaid) {
		String hqlName = "getListByUaidAndMaxStage";
		List list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName,"uaid",uaid);
		return list;
	}


}
