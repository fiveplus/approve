package com.approve.services.serv;

import java.util.List;

import com.approve.persistence.entity.UserDept;

public interface UserDeptServ {
	public int delete(String id);
	public int insert(UserDept ud);
	public UserDept getUserDeptByDeptId(String deptId);
	public List<UserDept> getUserDeptByUserId(String userId);
	public int deleteByUserid(String userId);
}
