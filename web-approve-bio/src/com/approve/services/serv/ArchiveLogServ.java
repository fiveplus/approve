package com.approve.services.serv;

import com.approve.persistence.entity.ArchiveLog;
import com.approve.services.BaseInterface;

public interface ArchiveLogServ extends BaseInterface{
	public int insert(ArchiveLog al);
	public ArchiveLog getArchiveLogById(String id);
	public int update(ArchiveLog al);
	public int delete(String id);
}
