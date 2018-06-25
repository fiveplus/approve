package com.approve.services.serv;

import java.util.List;

import com.approve.persistence.entity.ApproveLog;
import com.approve.services.BaseInterface;

public interface ApproveLogServ extends BaseInterface{
	public int insert(ApproveLog log);
	public int delete(String id);
	public ApproveLog getApproveLogById(String id);
	public int update(ApproveLog log);
	public List<ApproveLog> getListToUser(String userId);
	public List<ApproveLog> getListToUaidAndStage(String uaid,Integer stage);
	public List<ApproveLog> getListToUaidAndStageForYorN(String uaid,Integer stage);
	
	public List<ApproveLog> getListToUaidForYorN(String uaid);
	
	public List<ApproveLog> getListByUaid(String uaid);
	
	public List<ApproveLog> getListByUaidAndMaxStage(String uaid);
	
}
