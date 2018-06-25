package com.approve.services.serv.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.approve.persistence.entity.SysUser;
import com.approve.services.BaseService;
import com.approve.services.serv.SysUserServ;
import com.approve.struts.util.BeanCopyUtil;
import com.approve.struts.util.MD5;


public class SysUserImpl extends BaseService implements SysUserServ{

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int delete(String id) {
		int flag = 1;
		try {
			SysUser user = (SysUser)this.baseDAO.findById(SysUser.class, id);
			if(user != null){
				this.baseDAO.delete(user);
			}
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	public SysUser getSysUserById(String id) {
		return (SysUser)this.baseDAO.findById(SysUser.class, id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int insert(SysUser sysUser) {
		int flag = 1;
		try {
			this.baseDAO.save(sysUser);
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int update(SysUser sysUser) {
		int flag = 1;
		try {
			SysUser temp = (SysUser)this.baseDAO.findById(SysUser.class, sysUser.getId());
			if(sysUser.getPassword() != null && !sysUser.getPassword().equals("")){
				sysUser.setPassword(MD5.GetMD5Password(sysUser.getPassword()));
			}
			if(temp != null){
				BeanCopyUtil.beanCopy(sysUser, temp);
				this.baseDAO.update(temp);
			}
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	public SysUser getSysUserByLoginNameAndPassword(String loginName,
			String password) {
		SysUser sysUser = null;
		try {
			
			String hqlName = "getSysUserByLoginNameAndPassword";
			//√‹¬Îº”√‹
			password = MD5.GetMD5Password(password);
			List<SysUser> list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName,new String[]{"loginName","password"},new Object[]{loginName,password});
			if(list != null && list.size() > 0){
				sysUser = list.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sysUser;
	}

	public List<SysUser> getSysUserByDeptId(String deptId) {
		String hqlName = "getSysUserByDeptId";
		List<SysUser> list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName,"deptId",deptId);
		return list;
	}

	public List<SysUser> getSysUserByPost(String post) {
		String hqlName = "getSysUserByPost";
		List<SysUser> users = this.baseDAO.findByNamedQueryAndNamedParam(hqlName,"post",post);
		return users;
	}

	public List<SysUser> getSysUserByPostAndDeptId(String post, String deptId) {
		String hqlName = "getSysUserByPostAndDeptId";
		List list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName,new String[]{"post","deptId"},new Object[]{post,deptId});
		return list;
	}

	public SysUser getSysUserByLoginName(String loginName) {
		String hqlName = "getSysUserByLoginName";
		List<SysUser> list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName, "loginName", loginName);
		SysUser user = null;
		if(list!=null && list.size() > 0){
			user = list.get(0);
		}
		return user;
	}

	public int getUserCountByDeptId(String deptId) {
		int count = 0;
		String hql = "SELECT COUNT(*) FROM SysUser u where u.dept.id = '"+deptId+"' AND u.status = 'Y' ";
		Session session = this.baseDAO.getBaseSession();
		Query query = session.createQuery(hql);
		count = (new Integer(query.uniqueResult().toString())).intValue(); 
		return count;
	}

}
