package com.approve.services.serv.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.approve.persistence.entity.UserApprove;
import com.approve.services.BaseService;
import com.approve.services.serv.UserApproveServ;
import com.approve.struts.util.BeanCopyUtil;
import com.approve.struts.util.StringUtil;

public class UserApproveImpl extends BaseService implements UserApproveServ{

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int delete(String id) {
		int flag = 1;
		try {
			UserApprove ua = (UserApprove)this.baseDAO.findById(UserApprove.class, id);
			if(ua != null){
				this.baseDAO.delete(ua);
			}
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	public UserApprove getUserApproveById(String id) {
		return (UserApprove)this.baseDAO.findById(UserApprove.class, id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int insert(UserApprove ua) {
		int flag = 1;
		try {
			int max = getMaxApproveIndex();
			int index = max + 1;
			ua.setIndex(index);
			String date = StringUtil.getDateToSmallString(new Date());
			String num = date + "-"+index;
			ua.setApproveNum(num);
			
			
			
			this.baseDAO.save(ua);
			
		} catch (Exception e) {
			e.printStackTrace();
			flag = -1;
		}
		return flag;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int update(UserApprove ua) {
		int flag = 1;
		try {
			UserApprove temp = (UserApprove)this.baseDAO.findById(UserApprove.class, ua.getId());
			if(temp != null){
				BeanCopyUtil.beanCopy(ua, temp);
				this.baseDAO.update(temp);
			}
		} catch (Exception e) {
			flag = -1;
		}
		return flag;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public UserApprove insert2(UserApprove ua) {
		int max = getMaxApproveIndex();
		int index = max + 1;
		ua.setIndex(index);
		String date = StringUtil.getDateToSmallString(new Date());
		String num = date + "-"+index;
		ua.setApproveNum(num);
		this.baseDAO.save(ua);
		return ua;
	}

	public int getMaxApproveIndex() {
		String sql = "select IFNULL(max(approve_index),0) from tbl_user_approve ";
		int max = 0;
		Session session = this.baseDAO.getBaseSession();
		Query query = session.createSQLQuery(sql);
		max = (new Integer(query.uniqueResult().toString())).intValue();
		return max;
	}

	public List<UserApprove> getListToMaxStage(int maxStage) {
		String hqlName = "getListToMaxStage";
		List list = this.baseDAO.findByNamedQueryAndNamedParam(hqlName, "maxStage", maxStage);
		return list;
	}

	public int getUserApproveCount() {
		String sql = "select COUNT(*) from tbl_user_approve ";
		int count = 0;
		Session session = this.baseDAO.getBaseSession();
		Query query = session.createSQLQuery(sql);
		count = new Integer(query.uniqueResult().toString()).intValue();
		return count;
	}

	public int getUserApproveCountByStatus(String status) {
		String sql = "select count(*) from (select alal.ua_id,alal.status from tbl_approve_log alal,(select ua_id,max(stage) as stage from tbl_approve_log al group by al.ua_id) t  where alal.ua_id = t.ua_id and alal.stage = t.stage) tt where tt.status = '"+status+"' ";
		int count = 0;
		Session session = this.baseDAO.getBaseSession();
		Query query = session.createSQLQuery(sql);
		count = new Integer(query.uniqueResult().toString()).intValue();
		return count;
	}

	public int getUserApproveCountByUserIdAndStatus(String userId, String status) {
		//TODO 去除被删除的部分
		String sql = "select count(*) from (select alal.ua_id,alal.status,alal.to_user_id from tbl_approve_log alal,(select ua_id,max(stage) as stage from tbl_approve_log al group by al.ua_id) t  where alal.ua_id = t.ua_id and alal.stage = t.stage) tt left join tbl_user_approve ua on tt.ua_id=ua.id where ua.status = 'Y' and tt.status = '"+status+"' and tt.to_user_id = '"+userId+"'";
		int count = 0;
		Session session = this.baseDAO.getBaseSession();
		Query query = session.createSQLQuery(sql);
		count = new Integer(query.uniqueResult().toString()).intValue();
		return count;
	}
	
}
