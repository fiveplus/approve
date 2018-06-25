package com.approve.persistence;

import java.util.ArrayList;
import java.util.List;

import com.approve.persistence.entity.Approve;
import com.approve.persistence.entity.ApproveLog;
import com.approve.persistence.entity.ApproveType;
import com.approve.persistence.entity.ArchiveLog;
import com.approve.persistence.entity.Department;
import com.approve.persistence.entity.Setting;
import com.approve.persistence.entity.StaticSetting;
import com.approve.persistence.entity.SysFreeApprove;
import com.approve.persistence.entity.SysUser;
import com.approve.persistence.entity.UserApprove;
import com.approve.persistence.entity.UserDept;

public class SysMapping {
	private static final Class[] annotatedClasses = {SysUser.class,Department.class,Approve.class,UserApprove.class,ApproveLog.class,ArchiveLog.class,UserDept.class,Setting.class,SysFreeApprove.class,StaticSetting.class,ApproveType.class};
	private static final String[] hqlMapping = {"SysUser.hbm.xml","Department.hbm.xml","Approve.hbm.xml","UserApprove.hbm.xml","ApproveLog.hbm.xml","ArchiveLog.hbm.xml","UserDept.hbm.xml","Setting.hbm.xml","SysFreeApprove.hbm.xml","StaticSetting.hbm.xml","ApproveType.hbm.xml"};
	private static final String hqlPath = "com/approve/persistence/hql/"; 
	
	public Class[] getAnnotatedClasses(){
		return annotatedClasses;
	}
	
	public String[] getMappingResources(){
		List<String> mapping = new ArrayList<String>();
		for(int i = 0;i<hqlMapping.length;i++){
			mapping.add(hqlPath+hqlMapping[i]);
		}
		String[] totalMapping = new String[mapping.size()];
		totalMapping = mapping.toArray(totalMapping);
		return totalMapping;
	}
}
