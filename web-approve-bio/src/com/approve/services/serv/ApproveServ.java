package com.approve.services.serv;

import java.util.List;

import com.approve.persistence.entity.Approve;
import com.approve.services.BaseInterface;

public interface ApproveServ extends BaseInterface{
	public int insert(Approve approve);
	public int update(Approve approve);
	public int delete(String id);
	public Approve getApproveById(String id);
	public List<Approve> getList();
	public Approve getApproveByName(String name);
	
}
