package com.approve.services.serv;

import java.util.List;

import com.approve.persistence.entity.UserApprove;
import com.approve.services.BaseInterface;

public interface UserApproveServ extends BaseInterface{
	public int insert(UserApprove ua);
	public UserApprove insert2(UserApprove ua);
	public int delete(String id);
	public UserApprove getUserApproveById(String id);
	public int update(UserApprove ua);
	
	public int getMaxApproveIndex();
	
	public List<UserApprove> getListToMaxStage(int maxStage);
	
	public int getUserApproveCount();
	
	public int getUserApproveCountByStatus(String status);
	
	public int getUserApproveCountByUserIdAndStatus(String userId,String status);
	
}
