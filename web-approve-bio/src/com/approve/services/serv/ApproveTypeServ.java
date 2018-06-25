package com.approve.services.serv;

import java.util.List;

import com.approve.persistence.entity.ApproveType;
import com.approve.services.BaseInterface;

public interface ApproveTypeServ extends BaseInterface{
	public int update(ApproveType type);
	public int insert(ApproveType type);
	public int delete(String id);
	public ApproveType getApproveTypeById(String id);
	public List<ApproveType> getList();
	public ApproveType getApproveTypeByName(String name);
	
}
