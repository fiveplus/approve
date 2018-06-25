package com.approve.services.serv;

import java.util.List;

import com.approve.persistence.entity.SysFreeApprove;
import com.approve.services.BaseInterface;

public interface SysFreeApproveServ extends BaseInterface{
	public int insert(SysFreeApprove fa);
	public int deleteAll();
	public List<SysFreeApprove> getAllFreeApproveList();
	public SysFreeApprove getSysFreeApproveByUserId(String userId);
}
