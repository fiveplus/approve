package com.approve.struts.approve;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.approve.persistence.entity.Approve;
import com.approve.persistence.entity.ApproveLog;
import com.approve.persistence.entity.ApproveType;
import com.approve.persistence.entity.ArchiveLog;
import com.approve.persistence.entity.SysUser;
import com.approve.persistence.entity.UserApprove;
import com.approve.persistence.entity.UserDept;
import com.approve.services.KeyValue;
import com.approve.struts.BaseAction;
import com.approve.struts.util.EAResource;
import com.approve.struts.util.JsonUtil;
import com.approve.struts.util.PageUtil;
import com.approve.struts.util.StringUtil;
import com.approve.struts.util.jpush.JPushUtil;

public class UserApproveAction extends BaseAction{
	
	private ApproveLog log;
	private UserApprove userApprove;
	private List<UserApprove> userApproves;
	public UserApprove getUserApprove() {
		return userApprove;
	}
	public void setUserApprove(UserApprove userApprove) {
		this.userApprove = userApprove;
	}
	public List<UserApprove> getUserApproves() {
		return userApproves;
	}
	public void setUserApproves(List<UserApprove> userApproves) {
		this.userApproves = userApproves;
	}
	public ApproveLog getLog() {
		return log;
	}
	public void setLog(ApproveLog log) {
		this.log = log;
	}
	
	//TODO ���������б�
	public String del_list() throws Exception{
		List<KeyValue> ands = new ArrayList<KeyValue>();
		ands.add(new KeyValue("dstatus", "N"));
		
		/*
		if(getCurrentUser().getIsAdmin().equals("N")){
			ands.add(new KeyValue("user.id", getCurrentUser().getId()));
		}*/
		
		List<KeyValue> likes = new ArrayList<KeyValue>();
		
		selectValue = selectValue==null?null:selectValue.trim();
		if(selectValue != null && !selectValue.equals("")){
			likes.add(new KeyValue("title", "%"+selectValue+"%"));
			likes.add(new KeyValue("user.userName", "%"+selectValue+"%"));
			request.setAttribute("selectValue", selectValue);
		}
		
		
		int count = userApproveServ.getCount(UserApprove.class, ands, likes);
		pu.initPage(UserApprove.class, "userapprove", count);
		
		userApproves = userApproveServ.getList(pu, ands,likes);
		for(UserApprove uua:userApproves){
			List<ApproveLog> temps = approveLogServ.getListByUaidAndMaxStage(uua.getId());
			String status = "Y";
			for(ApproveLog a:temps){
				if(a.getStatus().equals("N")){
					status = "N";
				}else if(a.getStatus().equals("W")){
					status = "W";
				}
			}
			uua.setStatus(status);
		}
		
		request.setAttribute("pu", pu);
		
		return SUCCESS;
	}
	
	//TODO �������ָ̻�
	public String recover() throws Exception{
		//TODO �������ָ̻�
		UserApprove ua = userApproveServ.getUserApproveById(id);
		if(ua != null){
			ua.setDstatus("Y");
			userApproveServ.update(ua);
		}
		message = "��ϲ�����ָ��ɹ�!";
		returnURL ="userapprove/del_list.htm?pu.pageNum=1";
		return SUCCESS;
	}
	
	
	public void approve_delete() throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//TODO ��������ɾ��
		UserApprove ua = userApproveServ.getUserApproveById(id);
		//System.out.println(ua);
		if(ua != null){
			ua.setDstatus("N");
			userApproveServ.update(ua);
		}
		message = "��ϲ�������ϳɹ�!";
		
		out.print(message);
		out.flush();
		out.close();
	}
	
	public String list_all() throws Exception{
		List<KeyValue> ands = new ArrayList<KeyValue>();
		ands.add(new KeyValue("dstatus", "Y"));
		
		
		List<KeyValue> likes = new ArrayList<KeyValue>();
		
		selectValue = selectValue==null?null:selectValue.trim();
		if(selectValue != null && !selectValue.equals("")){
			likes.add(new KeyValue("title", "%"+selectValue+"%"));
			likes.add(new KeyValue("user.userName", "%"+selectValue+"%"));
			request.setAttribute("selectValue", selectValue);
		}
		
		
		int count = userApproveServ.getCount(UserApprove.class, ands, likes);
		pu.initPage(UserApprove.class, "userapprove", count);
		
		userApproves = userApproveServ.getList(pu, ands,likes);
		for(UserApprove uua:userApproves){
			List<ApproveLog> temps = approveLogServ.getListByUaidAndMaxStage(uua.getId());
			String status = "Y";
			for(ApproveLog a:temps){
				if(a.getStatus().equals("N")){
					status = "N";
				}else if(a.getStatus().equals("W")){
					status = "W";
				}
			}
			uua.setStatus(status);
		}
		
		request.setAttribute("pu", pu);
		
		return SUCCESS;
	}
	
	
	public String list() throws Exception{
		List<KeyValue> ands = new ArrayList<KeyValue>();
		ands.add(new KeyValue("dstatus", "Y"));
		if(getCurrentUser().getIsAdmin().equals("N")){
			ands.add(new KeyValue("user.id", getCurrentUser().getId()));
		}
		
		List<KeyValue> likes = new ArrayList<KeyValue>();
		
		selectValue = selectValue==null?null:selectValue.trim();
		if(selectValue != null && !selectValue.equals("")){
			likes.add(new KeyValue("title", "%"+selectValue+"%"));
			likes.add(new KeyValue("user.userName", "%"+selectValue+"%"));
			request.setAttribute("selectValue", selectValue);
		}
		
		
		int count = userApproveServ.getCount(UserApprove.class, ands, likes);
		pu.initPage(UserApprove.class, "userapprove", count);
		
		userApproves = userApproveServ.getList(pu, ands,likes);
		for(UserApprove uua:userApproves){
			List<ApproveLog> temps = approveLogServ.getListByUaidAndMaxStage(uua.getId());
			String status = "Y";
			for(ApproveLog a:temps){
				if(a.getStatus().equals("N")){
					status = "N";
				}else if(a.getStatus().equals("W")){
					status = "W";
				}
			}
			uua.setStatus(status);
		}
		
		request.setAttribute("pu", pu);
		
		return SUCCESS;
	}
	
	//stage 1 �������η�����Ϣ
	public String add() throws Exception{
		try {
			String push_title = "�����µ����̴�����"; 
			String push_content = userApprove.getTitle();
			
			if(userApprove.getTitle().equals("")){
				message = "�������������⣡";
				return SUCCESS;
			}
			userApprove.setDstatus("Y");
			userApprove.setCreateTime(StringUtil.getDateToLong(new Date()));
			userApprove.setUser(getCurrentUser());
			userApprove = userApproveServ.insert2(userApprove);
			if(userApprove.getApprove()!=null){
				//��ѯ�ò��ž���,����������־
				Approve a = approveServ.getApproveById(userApprove.getApprove().getId());
				List<SysUser> managers = sysUserServ.getSysUserByPostAndDeptId("1", getCurrentUser().getDept().getId());
				if(managers == null || managers.size() == 0){
					message = "�� ���ȴ����ò��Ų��ž���"; 
					return SUCCESS;
				}
				
				Date now = new Date();
				for(SysUser m:managers){
					//��������������־ - stage 0 status - W wait
					ApproveLog log = new ApproveLog();
					log.setApprove(a);
					log.setUa(userApprove);
					log.setToUser(m);
					log.setFromUser(getCurrentUser());
					log.setContent("");
					log.setCreateTime(StringUtil.getDateToLong(now));
					log.setStage(0);
					log.setStatus("W");
					approveLogServ.insert(log);
				}
				//TODO ��Ϣ����
				new JPushUtil(push_title, push_content,managers).start();
				
				
				message = "��ϲ�������������ύ�ɹ�����ȴ����!";
				returnURL = "userapprove/list.htm?pu.pageNum=1";
				
			}else{
				Date now = new Date();
				List<SysUser> managers = sysUserServ.getSysUserByPostAndDeptId("1", getCurrentUser().getDept().getId());
				
				if(managers == null || managers.size() == 0){
					message = "�� ���ȴ����ò��Ų��ž���"; 
					return SUCCESS;
				}
				
				for(SysUser m:managers){
					//��������������־ - stage 0 status - W wait
					ApproveLog log = new ApproveLog();
					log.setUa(userApprove);
					log.setToUser(m);
					log.setFromUser(getCurrentUser());
					log.setContent("");
					log.setCreateTime(StringUtil.getDateToLong(now));
					log.setStage(0);
					log.setStatus("W");
					approveLogServ.insert(log);
				}
				message = "��ϲ�������������ύ�ɹ�����ȴ����!";
				returnURL = "userapprove/list.htm?pu.pageNum=1";
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = e.toString();
		}
		return SUCCESS;
	}
	
	public String addinit() throws Exception{
		//List<Approve> approves = approveServ.getList();
		//request.setAttribute("approves", approves);\
		
		Approve approve = approveServ.getApproveById(id);
		
		request.setAttribute("approve", approve);
		
		
		return SUCCESS;
	}
	
	public String user_approves() throws Exception{
		
		List<KeyValue> likes = new ArrayList<KeyValue>();
		if(selectValue !=null && !selectValue.equals("")){
			selectValue = selectValue.trim();
			likes.add(new KeyValue("ua.title", "%"+selectValue+"%"));
		}
		
		List<KeyValue> ands = new ArrayList<KeyValue>();
		ands.add(new KeyValue("ua.dstatus", "Y"));
		ands.add(new KeyValue("toUser.id", getCurrentUser().getId()));
		
		int count = approveLogServ.getCount(ApproveLog.class, ands, likes);
		
		pu.initPage(ApproveLog.class, "userapprove", count);
		
		List<ApproveLog> logs = approveLogServ.getList(pu, ands,likes);
		
		request.setAttribute("logs", logs);
		
		return SUCCESS;
	}
	
	public String approve_updateInit() throws Exception{
		ApproveLog log = approveLogServ.getApproveLogById(id);

		if(!log.getStatus().equals("W")){
			//message = "�� ��ǰ�����ѽ�����һ���ڵ㣬�����ظ��ύ!";
			
			if(log.getStage() > 0){
				//��ѯ��һ���ڵ�����
				//List<ApproveLog> oldlogs = approveLogServ.getListToUaidAndStageForYorN(log.getUa().getId(), log.getStage()-1);
				//��ԃ��N�����c�ă���
				List<ApproveLog> oldlogs = approveLogServ.getListToUaidForYorN(log.getUa().getId());
				request.setAttribute("oldlogs", oldlogs);
			}
			
			request.setAttribute("log", log);
			return SUCCESS;
		}else{
			
			if(log.getStage() > 0){
				//��ѯ��һ���ڵ�����
				//List<ApproveLog> oldlogs = approveLogServ.getListToUaidAndStageForYorN(log.getUa().getId(), log.getStage()-1);
				//��ԃ��N�����c�ă���
				List<ApproveLog> oldlogs = approveLogServ.getListToUaidForYorN(log.getUa().getId());
				request.setAttribute("oldlogs", oldlogs);
			}
			
			request.setAttribute("log", log);
			return SUCCESS;
		}
	}
	private List<SysUser> push_users;
	public String approve_update() throws Exception{
		try {
			
			ApproveLog tlog = approveLogServ.getApproveLogById(log.getId());
			
			String push_title = "�����µ����̴�����"; 
			String push_content = tlog.getUa().getTitle();
			
			push_users = new ArrayList<SysUser>();
			
			
			UserDept tud = userDeptServ.getUserDeptByDeptId(tlog.getFromUser().getDept().getId());
			if(tud == null){
				message = "�� �������÷ֲ�߹ܣ�";
			}else{
				approveLogServ.update(log);
			}
			
			log = approveLogServ.getApproveLogById(log.getId());
			
			if(message == "" || message == null){
				Approve a = log.getApprove();
				Date now = new Date();
				if(a != null){
				if(a.getApproveType().equals("0")){
					//�ճ�����
					ApproveLog temp = new ApproveLog();
					temp.setApprove(a);
					temp.setUa(log.getUa());
					temp.setFromUser(log.getFromUser());
					temp.setContent("");
					temp.setStatus("W");
					temp.setCreateTime(StringUtil.getDateToLong(now));
					
					switch(log.getStage()){
						case 0:
							//Ѱ��ҵ��������add stage = 1
							if(log.getStatus().equals("Y")){
								temp.setToUser(a.getApprovePeople());
								temp.setStage(1);
								approveLogServ.insert(temp);
								
								push_users.add(a.getApprovePeople());
							}
							break;
						case 1:
							//Ѱ�ҷֲ�߹�add stage = 2
							UserDept ud = userDeptServ.getUserDeptByDeptId(log.getFromUser().getDept().getId());
							if(ud == null){
								message = "�� �������÷ֲ�߹ܣ�";
							}else{
								if(log.getStatus().equals("Y") && ud != null){
									//temp.setToUser(a.getLayerPeople());
									
									temp.setToUser(ud.getUser());
									temp.setStage(2);
									approveLogServ.insert(temp);
									
									/*
									if(a.getIsLayer().equals("Y")){
										UserDept ud = userDeptServ.getUserDeptByDeptId(log.getFromUser().getDept().getId());
										temp.setToUser(ud.getUser());
										temp.setStage(2);
										approveLogServ.insert(temp);
									}else{
										//TODO Waiting Result
									}*/
									
									push_users.add(ud.getUser());
									
								}
							}
							
							break;
						case 2:
							//��ѯlogs�� ua AND  stage = 1 �Ĳ��� status = W
							if(log.getStatus().equals("Y")){
								//��ѯ�ܾ���
								List<SysUser> toUsers = new ArrayList<SysUser>();
								toUsers.add(a.getManager());
								toUsers.add(a.getChairman());
								toUsers.add(a.getOffice());
								
								recursive_approve(toUsers, log, now, 0);
								
							}
							break;
						case 3:
							//���³�
							if(log.getStatus().equals("Y")){
								
								List<SysUser> toUsers = new ArrayList<SysUser>();
								toUsers.add(a.getChairman());
								toUsers.add(a.getOffice());
								
								recursive_approve(toUsers, log, now, 0);
							}
							break;
						case 4:
							//����칫��
							if(log.getStatus().equals("Y")){
								List<SysUser> toUsers = new ArrayList<SysUser>();
								toUsers.add(a.getOffice());
								recursive_approve(toUsers, log, now, 0);
							}
							
							break;
						case 5:
							//�������� ,��ӹ鵵��־
							ArchiveLog al = new ArchiveLog();
							al.setCreateTime(StringUtil.getDateToLong(now));
							al.setUa(log.getUa());
							al.setUser(a.getArchivePeople());
							al.setStatus("N");
							archiveLogServ.insert(al);
							break;
						default:
							break;
					}
					
				}else{
					//�������
					switch(log.getStage()){
						case 0:
							if(log.getStatus().equals("Y")){
								//Ѱ�ҷ��ίԱ���Ա
								String[] controls = a.getControlPeople().split(",");
								for(int i = 0;i < controls.length;i++){
									SysUser control = sysUserServ.getSysUserById(controls[i]);
									ApproveLog temp1 = new ApproveLog();
									temp1.setApprove(a);
									temp1.setUa(log.getUa());
									temp1.setFromUser(log.getFromUser());
									temp1.setToUser(control);
									temp1.setContent("");
									temp1.setStatus("W");
									temp1.setCreateTime(StringUtil.getDateToLong(now));
									temp1.setStage(1);
									approveLogServ.insert(temp1);
									
									push_users.add(control);
								}
							}
							break;
						case 1:
							//��ѯlogs�� ua AND  stage = 1 �Ĳ��� status = W
							List list2 = approveLogServ.getListToUaidAndStage(log.getUa().getId(),1);
							if(list2 == null || list2.size() == 0){
								//�ֹ�A
								List<SysUser> toUsers = new ArrayList<SysUser>();
								toUsers.add(a.getLayera());
								toUsers.add(a.getLayerb());
								toUsers.add(a.getManager());
								toUsers.add(a.getChairman());
								toUsers.add(a.getOffice());
								
								recursive_approve(toUsers, log, now, 0);
								
							}
							break;
						case 2:
							if(log.getStatus().equals("Y")){
								//�ֹ�B
								List<SysUser> toUsers = new ArrayList<SysUser>();
								toUsers.add(a.getLayerb());
								toUsers.add(a.getManager());
								toUsers.add(a.getChairman());
								toUsers.add(a.getOffice());
								
								recursive_approve(toUsers, log, now, 0);
							}
							break;
						case 3:
							if(log.getStatus().equals("Y")){
								
								//��ѯ�ܾ���
								List<SysUser> toUsers = new ArrayList<SysUser>();
								toUsers.add(a.getManager());
								toUsers.add(a.getChairman());
								toUsers.add(a.getOffice());
								
								recursive_approve(toUsers, log, now, 0);
								
							}
							break;
						case 4:
							//���³�
							if(log.getStatus().equals("Y")){
								
								List<SysUser> toUsers = new ArrayList<SysUser>();
								toUsers.add(a.getChairman());
								toUsers.add(a.getOffice());
								
								recursive_approve(toUsers, log, now, 0);
								
							}
							break;
						case 5:
							//����칫��
							if(log.getStatus().equals("Y")){
								List<SysUser> toUsers = new ArrayList<SysUser>();
								toUsers.add(a.getOffice());
								
								recursive_approve(toUsers, log, now, 0);
							}
							break;
						case 6:
							//�������� ,��ӹ鵵��־
							ArchiveLog al = new ArchiveLog();
							al.setCreateTime(StringUtil.getDateToLong(now));
							al.setUa(log.getUa());
							al.setUser(a.getArchivePeople());
							al.setStatus("N");
							archiveLogServ.insert(al);
							break;
						default:
							break;
					}
				}
				
				}else{
					//��������
					switch(log.getStage()){
						case 0:
							String[] controls = log.getUa().getRemark().split(",");
							for(int i = 0;i < controls.length;i++){
								SysUser control = sysUserServ.getSysUserById(controls[i]);
								ApproveLog temp1 = new ApproveLog();
								temp1.setApprove(a);
								temp1.setUa(log.getUa());
								temp1.setFromUser(log.getFromUser());
								temp1.setToUser(control);
								temp1.setContent("");
								temp1.setStatus("W");
								temp1.setCreateTime(StringUtil.getDateToLong(now));
								temp1.setStage(1);
								approveLogServ.insert(temp1);
								
								push_users.add(control);
							}
							break;
						case 1:
							List list2 = approveLogServ.getListToUaidAndStage(log.getUa().getId(),1);
							if(list2 == null || list2.size() == 0){
								//�ֹܸ���
								String managerid = staticSettingServ.getStaticSettingByColumnName("MANAGER_ID").getColumnValue();
								if(managerid == null || managerid.equals("")){
									message = "x �������÷ֹܸ��ܣ�";
								}else{
									SysUser manager = sysUserServ.getSysUserById(managerid);
									ApproveLog temp1 = new ApproveLog();
									temp1.setApprove(a);
									temp1.setUa(log.getUa());
									temp1.setFromUser(log.getFromUser());
									temp1.setToUser(manager);
									temp1.setContent("");
									temp1.setStatus("W");
									temp1.setCreateTime(StringUtil.getDateToLong(now));
									temp1.setStage(2);
									approveLogServ.insert(temp1);
									
									push_users.add(manager);
								}
							}
							break;
						case 2:
							//�������� ,��ӹ鵵��־
							String archiveid = staticSettingServ.getStaticSettingByColumnName("ARCHIVE_ID").getColumnValue();
							if(archiveid == null || archiveid.equals("")){
								message = "x �������ù鵵�ˣ�";
							}else{
								SysUser archive = sysUserServ.getSysUserById(archiveid);
								ArchiveLog al = new ArchiveLog();
								al.setCreateTime(StringUtil.getDateToLong(now));
								al.setUa(log.getUa());
								//al.setUser(log.getFromUser());
								al.setUser(archive);
								al.setStatus("N");
								archiveLogServ.insert(al);
							}
							break;
						default:
							break;
					}
					
				}
				//TODO push��Ϣ
				if(message == null || message.equals("")){
					new JPushUtil(push_title, push_content,push_users).start();
					message = "��ϲ���������ύ�ɹ�!";	
					returnURL = "userapprove/user_approves.htm?pu.pageNum=1";
				}
			}else{
				//message = "�ܱ�Ǹ�������ύʧ��!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = e.toString();
		}
		return SUCCESS;
	}
	
	public String approvelog() throws Exception{
		List list = approveLogServ.getListByUaid(id);
		
		UserApprove ua = userApproveServ.getUserApproveById(id);
		
		List<ApproveLog> temps = approveLogServ.getListByUaidAndMaxStage(ua.getId());
		String status = "Y";
		for(ApproveLog a:temps){
			if(a.getStatus().equals("N")){
				status = "N";
			}else if(a.getStatus().equals("W")){
				status = "W";
			}
		}
		ua.setStatus(status);
		
		request.setAttribute("list", list);
		request.setAttribute("ua", ua);
		
		return SUCCESS;
	}
	
	/**
	 * ������������
	 * @return
	 * @throws Exception
	 */
	public String addfree_init() throws Exception{
		
		return SUCCESS;
	}
	
	public String addfree() throws Exception{
		try {
			if(userApprove.getTitle().equals("")){
				message = "�������������⣡";
				return SUCCESS;
			}
			if(userApprove.getRemark().equals("")){
				message = "��ѡ�������ˣ�";
				return SUCCESS;
			}
			userApprove.setCreateTime(StringUtil.getDateToLong(new Date()));
			userApprove.setUser(getCurrentUser());
			userApprove = userApproveServ.insert2(userApprove);
			if(1 > 0){
				//��ѯ�ò��ž���,����������־
				List<SysUser> managers = sysUserServ.getSysUserByPostAndDeptId("1", getCurrentUser().getDept().getId());
				if(managers == null || managers.size() == 0){
					message = "���ȴ������ž���!";
					return SUCCESS;
				}
				Date now = new Date();
				for(SysUser m:managers){
					//��������������־ - stage 0 status - W wait
					ApproveLog log = new ApproveLog();
					log.setApprove(null);
					log.setUa(userApprove);
					log.setToUser(m);
					log.setFromUser(getCurrentUser());
					log.setContent("");
					log.setCreateTime(StringUtil.getDateToLong(now));
					log.setStage(0);
					log.setStatus("W");
					approveLogServ.insert(log);
				}
				message = "��ϲ�������������ύ�ɹ�����ȴ����!";
				returnURL = "userapprove/addinit.htm";
				
			}else{
				message = "�ܱ�Ǹ�����������ύʧ��!";
				returnURL = "userapprove/addinit.htm";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = e.toString();
		}
		return SUCCESS;
	}
	
	
	private List<Approve> approves;
	public void setApproves(List<Approve> approves) {
		this.approves = approves;
	}
	public List<Approve> getApproves() {
		return approves;
	}
	
	public String add_list() throws Exception{
		
		List<ApproveType> types = approveTypeServ.getList();
		
		List<KeyValue> likes = new ArrayList<KeyValue>();
		
		selectValue = selectValue==null?null:selectValue.trim();
		if(selectValue != null && !selectValue.equals("")){
			likes.add(new KeyValue("name", "%"+selectValue+"%"));
			request.setAttribute("selectValue", selectValue);
		}
		
		
		List<KeyValue> ands = new ArrayList<KeyValue>();
		if(typeId != null && !typeId.equals("")){
			ands.add(new KeyValue("atype.id", typeId));
		}
		
		ands.add(new KeyValue("status", "Y"));
		
		int count = approveServ.getCount(Approve.class, ands, likes);
		
		pu.initPage(Approve.class, "userapprove", count);
		
		approves = approveServ.getList(pu,ands,likes);
		
		request.setAttribute("pu", pu);
		
		request.setAttribute("types", types);
		
		return SUCCESS;
	}
	
	
	public String printpage() throws Exception{
		ArchiveLog al = archiveLogServ.getArchiveLogById(id);
		
		List list = approveLogServ.getListByUaid(al.getUa().getId());
		request.setAttribute("list", list);
		request.setAttribute("al", al);
		return SUCCESS;
	}
	
	/**
	 * �ݹ�����-����ѭ���ж��������Ƿ�Ϊ��
	 * @param toUsers ������
	 * @param log ���ݴ洢�������
	 * @param index ��ǰ�������б�ʶ
	 * @return ����״̬
	 */
	public int recursive_approve(List<SysUser> toUsers,ApproveLog log,Date date,int index){
		ApproveLog temp = new ApproveLog();
		temp.setApprove(log.getApprove());
		temp.setUa(log.getUa());
		temp.setFromUser(log.getFromUser());
		temp.setStage(log.getStage()+(index+1));
		temp.setToUser(toUsers.get(index));
		temp.setContent("");
		temp.setCreateTime(StringUtil.getDateToLong(date));
		if(toUsers.get(index)!=null){
			//TODO �ȴ�״̬save������
			temp.setStatus("W");
			approveLogServ.insert(temp);
			
			//TODO �˳�������Ϣ
			push_users.add(toUsers.get(index));
			
			return 0;
		}else{
			//TODO �ɹ�״̬save������һ��
			if(index < toUsers.size() - 1){
				//TODO ��ֹ���
				temp.setStatus("Y");
				approveLogServ.insert(temp);
				return recursive_approve(toUsers,log,date,index+1);
			}else if(index == toUsers.size() - 1 ){
				//TODO save+�鵵
				temp.setStatus("Y");
				approveLogServ.insert(temp);
				
				ArchiveLog al = new ArchiveLog();
				al.setStatus("N");
				al.setCreateTime(StringUtil.getDateToLong(date));
				al.setUa(log.getUa());
				al.setUser(log.getApprove().getArchivePeople());
				archiveLogServ.insert(al);
				
				return 0;
			}else{
				//����״̬
				return 1;
			}
		}
	}
	
	public void tel_list() throws Exception{
		//TODO ���ÿ������
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods","POST");
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
	
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(pu == null){
			pu = new PageUtil();
			pu.setPageNum(1);
		}
		pu.setPageSize(1000);
		
		List<KeyValue> ands = new ArrayList<KeyValue>();
		ands.add(new KeyValue("user.id", getCurrentUser().getId()));
		ands.add(new KeyValue("dstatus", "Y"));
		int count = userApproveServ.getCount(UserApprove.class, ands, null);
		pu.initPage(UserApprove.class, "userapprove", count);
		
		userApproves = userApproveServ.getList(pu, ands,null);
		
		for(UserApprove uua:userApproves){
			List<ApproveLog> temps = approveLogServ.getListByUaidAndMaxStage(uua.getId());
			String status = "Y";
			for(ApproveLog a:temps){
				if(a.getStatus().equals("N")){
					status = "N";
				}else if(a.getStatus().equals("W")){
					status = "W";
				}
			}
			uua.setStatus(status);
		}
		
		Map<String,Object> returnMap = new HashMap<String, Object>();
		returnMap.put("userApproves", userApproves);
		out.print(JsonUtil.ObjectToString(returnMap));
		out.flush();
		out.close();
		
	}
	
	public void tel_list_all() throws Exception{
		//TODO ���ÿ������
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods","POST");
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
			
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
				
		List<KeyValue> ands = new ArrayList<KeyValue>();
		ands.add(new KeyValue("dstatus", "Y"));
		
		pu = new PageUtil();
		if(page <= 0){
			pu.setPageNum(1);
		}else{
			pu.setPageNum(page);
		}
		
		int count = userApproveServ.getCount(UserApprove.class, ands, null);
		pu.initPage(UserApprove.class, "userapprove", count);
		
		if(page <pu.getPageCount()){
			userApproves = userApproveServ.getList(pu, ands,null);
		}
		if(userApproves !=null){
			for(UserApprove uua:userApproves){
				List<ApproveLog> temps = approveLogServ.getListByUaidAndMaxStage(uua.getId());
				String status = "Y";
				for(ApproveLog a:temps){
					if(a.getStatus().equals("N")){
						status = "N";
					}else if(a.getStatus().equals("W")){
						status = "W";
					}
				}
				uua.setStatus(status);
			}
		}
		
				
		Map<String,Object> returnMap = new HashMap<String, Object>();
		returnMap.put("userApproves", userApproves);
		out.print(JsonUtil.ObjectToString(returnMap));
		out.flush();
		out.close();
	}
	
	
	public void tel_user_approves() throws Exception{
		
		//TODO ���ÿ������
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods","POST");
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
	
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(pu == null){
			pu = new PageUtil();
			pu.setPageNum(1);
		}
		pu.setPageSize(1000);
		
		List<KeyValue> ands = new ArrayList<KeyValue>();
		ands.add(new KeyValue("toUser.id", getCurrentUser().getId()));
		ands.add(new KeyValue("ua.dstatus","Y"));
		int count = approveLogServ.getCount(ApproveLog.class, ands, null);
		
		pu.initPage(ApproveLog.class, "userapprove", count);
		
		List<ApproveLog> logs = approveLogServ.getList(pu, ands,null);
		
		Map<String,Object> returnMap = new HashMap<String, Object>();
		returnMap.put("logs", logs);
		out.print(JsonUtil.ObjectToString(returnMap));
		out.flush();
		out.close();
		
	
	}
	
	public void tel_approvelog() throws Exception{
		//TODO ���ÿ������
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods","POST");
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
			
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		
		List<ApproveLog> list = approveLogServ.getListByUaid(id);
		if(list !=null && list.size() > 0){
			List<ApproveLog> temps = approveLogServ.getListByUaidAndMaxStage(id);
			String status = "Y";
			for(ApproveLog a:temps){
				if(a.getStatus().equals("N")){
					status = "N";
				}else if(a.getStatus().equals("W")){
					status = "W";
				}
			}
			//list.get(0).setStatus(status);
		}
		
		
		Map<String,Object> returnMap = new HashMap<String, Object>();
		returnMap.put("list", list);
		out.print(JsonUtil.ObjectToString(returnMap));
		out.flush();
		out.close();
	}
	
	public void tel_approve_updateInit() throws Exception{
		//TODO ���ÿ������
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods","POST");
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
					
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		ApproveLog log = approveLogServ.getApproveLogById(id);
		List<ApproveLog> oldlogs = null;
		if(log.getStage() > 0){
			//��ѯ��һ���ڵ�����
			//List<ApproveLog> oldlogs = approveLogServ.getListToUaidAndStageForYorN(log.getUa().getId(), log.getStage()-1);
			//��ԃ��N�����c�ă���
			oldlogs = approveLogServ.getListToUaidForYorN(log.getUa().getId());
		}
		
		Map<String,Object> returnMap = new HashMap<String, Object>();
		returnMap.put("log", log);
		returnMap.put("oldlogs", oldlogs);
		out.print(JsonUtil.ObjectToString(returnMap));
		out.flush();
		out.close();
	}
	
	private String status;
	private String content;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void tel_approve_update() throws Exception{

		
		String returnCode = "000000";
		//TODO ���ÿ������
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods","POST");
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
					
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String push_title = "�����µ����̴�����"; 
		String push_content = "";
		push_users = push_users==null?new ArrayList<SysUser>():push_users;
		try {
			ApproveLog tlog = new ApproveLog();
			tlog.setId(id);
			tlog.setStatus(status);
			tlog.setContent(content);
			
			log = approveLogServ.getApproveLogById(tlog.getId());
			UserDept tud = userDeptServ.getUserDeptByDeptId(log.getFromUser().getDept().getId());
			if(tud == null){
				returnCode = "001001";
			}else{
				approveLogServ.update(tlog);				
			}
			
			push_content = log.getUa().getTitle();
			
			if(returnCode == "000000"){
				Approve a = log.getApprove();
				Date now = new Date();
				if(a != null){
				if(a.getApproveType().equals("0")){
					//�ճ�����
					ApproveLog temp = new ApproveLog();
					temp.setApprove(a);
					temp.setUa(log.getUa());
					temp.setFromUser(log.getFromUser());
					temp.setContent("");
					temp.setStatus("W");
					temp.setCreateTime(StringUtil.getDateToLong(now));
					
					switch(log.getStage()){
						case 0:
							//Ѱ��ҵ��������add stage = 1
							if(log.getStatus().equals("Y")){
								temp.setToUser(a.getApprovePeople());
								temp.setStage(1);
								approveLogServ.insert(temp);
								
								push_users.add(a.getApprovePeople());
							}
							break;
						case 1:
							//Ѱ�ҷֲ�߹�add stage = 2
							UserDept ud = userDeptServ.getUserDeptByDeptId(log.getFromUser().getDept().getId());
							if(ud == null){
								returnCode = "001001";
							}else{
								if(log.getStatus().equals("Y") && ud != null){
									//temp.setToUser(a.getLayerPeople());
									
									temp.setToUser(ud.getUser());
									temp.setStage(2);
									approveLogServ.insert(temp);
									
									/*
									if(a.getIsLayer().equals("Y")){
										UserDept ud = userDeptServ.getUserDeptByDeptId(log.getFromUser().getDept().getId());
										temp.setToUser(ud.getUser());
										temp.setStage(2);
										approveLogServ.insert(temp);
									}else{
										//TODO Waiting Result
										
										
									}*/
									push_users.add(ud.getUser());
								}
							}
							
							break;
						case 2:
							//��ѯlogs�� ua AND  stage = 1 �Ĳ��� status = W
							if(log.getStatus().equals("Y")){
								//��ѯ�ܾ���
								List<SysUser> toUsers = new ArrayList<SysUser>();
								toUsers.add(a.getManager());
								toUsers.add(a.getChairman());
								toUsers.add(a.getOffice());
								
								recursive_approve(toUsers, log, now, 0);
								
							}
							break;
						case 3:
							//���³�
							if(log.getStatus().equals("Y")){
								
								List<SysUser> toUsers = new ArrayList<SysUser>();
								toUsers.add(a.getChairman());
								toUsers.add(a.getOffice());
								
								recursive_approve(toUsers, log, now, 0);
							}
							break;
						case 4:
							//����칫��
							if(log.getStatus().equals("Y")){
								List<SysUser> toUsers = new ArrayList<SysUser>();
								toUsers.add(a.getOffice());
								
								recursive_approve(toUsers, log, now, 0);
							}
							
							break;
						case 5:
							//�������� ,��ӹ鵵��־
							ArchiveLog al = new ArchiveLog();
							al.setCreateTime(StringUtil.getDateToLong(now));
							al.setUa(log.getUa());
							al.setUser(a.getArchivePeople());
							al.setStatus("N");
							archiveLogServ.insert(al);
							break;
						default:
							break;
					}
					
				}else{
					//�������
					switch(log.getStage()){
						case 0:
							if(log.getStatus().equals("Y")){
								//Ѱ�ҷ��ίԱ���Ա
								String[] controls = a.getControlPeople().split(",");
								for(int i = 0;i < controls.length;i++){
									SysUser control = sysUserServ.getSysUserById(controls[i]);
									ApproveLog temp1 = new ApproveLog();
									temp1.setApprove(a);
									temp1.setUa(log.getUa());
									temp1.setFromUser(log.getFromUser());
									temp1.setToUser(control);
									temp1.setContent("");
									temp1.setStatus("W");
									temp1.setCreateTime(StringUtil.getDateToLong(now));
									temp1.setStage(1);
									approveLogServ.insert(temp1);
									
									push_users.add(control);
								}
							}
							break;
						case 1:
							//��ѯlogs�� ua AND  stage = 1 �Ĳ��� status = W
							List list2 = approveLogServ.getListToUaidAndStage(log.getUa().getId(),1);
							if(list2 == null || list2.size() == 0){
								//�ֹ�A
								List<SysUser> toUsers = new ArrayList<SysUser>();
								toUsers.add(a.getLayera());
								toUsers.add(a.getLayerb());
								toUsers.add(a.getManager());
								toUsers.add(a.getChairman());
								toUsers.add(a.getOffice());
								
								recursive_approve(toUsers, log, now, 0);
								
							}
							break;
						case 2:
							if(log.getStatus().equals("Y")){
								//�ֹ�B
								List<SysUser> toUsers = new ArrayList<SysUser>();
								toUsers.add(a.getLayerb());
								toUsers.add(a.getManager());
								toUsers.add(a.getChairman());
								toUsers.add(a.getOffice());
								
								recursive_approve(toUsers, log, now, 0);
							}
							break;
						case 3:
							if(log.getStatus().equals("Y")){
								
								//��ѯ�ܾ���
								List<SysUser> toUsers = new ArrayList<SysUser>();
								toUsers.add(a.getManager());
								toUsers.add(a.getChairman());
								toUsers.add(a.getOffice());
								
								recursive_approve(toUsers, log, now, 0);
								
							}
							break;
						case 4:
							//���³�
							if(log.getStatus().equals("Y")){
								
								List<SysUser> toUsers = new ArrayList<SysUser>();
								toUsers.add(a.getChairman());
								toUsers.add(a.getOffice());
								
								recursive_approve(toUsers, log, now, 0);
								
							}
							break;
						case 5:
							//����칫��
							if(log.getStatus().equals("Y")){
								List<SysUser> toUsers = new ArrayList<SysUser>();
								toUsers.add(a.getOffice());
								
								recursive_approve(toUsers, log, now, 0);
							}
							break;
						case 6:
							//�������� ,��ӹ鵵��־
							ArchiveLog al = new ArchiveLog();
							al.setCreateTime(StringUtil.getDateToLong(now));
							al.setUa(log.getUa());
							al.setUser(a.getArchivePeople());
							al.setStatus("N");
							archiveLogServ.insert(al);
							break;
						default:
							break;
					}
				}
				
				}else{
					//��������
					switch(log.getStage()){
						case 0:
							String[] controls = log.getUa().getRemark().split(",");
							for(int i = 0;i < controls.length;i++){
								SysUser control = sysUserServ.getSysUserById(controls[i]);
								ApproveLog temp1 = new ApproveLog();
								temp1.setApprove(a);
								temp1.setUa(log.getUa());
								temp1.setFromUser(log.getFromUser());
								temp1.setToUser(control);
								temp1.setContent("");
								temp1.setStatus("W");
								temp1.setCreateTime(StringUtil.getDateToLong(now));
								temp1.setStage(1);
								approveLogServ.insert(temp1);
								
								push_users.add(control);
							}
							break;
						case 1:
							//�������� ,��ӹ鵵��־
							List list2 = approveLogServ.getListToUaidAndStage(log.getUa().getId(),1);
							if(list2 == null || list2.size() == 0){
								//�ֹܸ���
								String managerid = staticSettingServ.getStaticSettingByColumnName("MANAGER_ID").getColumnValue();
								if(managerid == null || managerid.equals("")){
									message = "001001";
								}else{
									SysUser manager = sysUserServ.getSysUserById(managerid);
									ApproveLog temp1 = new ApproveLog();
									temp1.setApprove(a);
									temp1.setUa(log.getUa());
									temp1.setFromUser(log.getFromUser());
									temp1.setToUser(manager);
									temp1.setContent("");
									temp1.setStatus("W");
									temp1.setCreateTime(StringUtil.getDateToLong(new Date()));
									temp1.setStage(2);
									approveLogServ.insert(temp1);
									
									push_users.add(manager);
								}
							}
							break;
						case 2:
							//�������� ,��ӹ鵵��־
							String archiveid = staticSettingServ.getStaticSettingByColumnName("ARCHIVE_ID").getColumnValue();
							if(archiveid == null || archiveid.equals("")){
								message = "001004";
							}else{
								SysUser archive = sysUserServ.getSysUserById(archiveid);
								ArchiveLog al = new ArchiveLog();
								al.setCreateTime(StringUtil.getDateToLong(now));
								al.setUa(log.getUa());
								//al.setUser(log.getFromUser());
								al.setUser(archive);
								al.setStatus("N");
								archiveLogServ.insert(al);
							}
							
							/*
							ArchiveLog al = new ArchiveLog();
							al.setCreateTime(StringUtil.getDateToLong(now));
							al.setUa(log.getUa());
							al.setUser(log.getFromUser());
							al.setStatus("N");
							archiveLogServ.insert(al);
							*/
							break;
						default:
							break;
					}
					
				}
				
			}else{
				//returnCode = "001002";
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnCode = "001003";
		}
		
		if(returnCode.equals("000000")){
			new JPushUtil(push_title, push_content,push_users).start();
		}
		
		
		Map<String,Object> returnMap = new HashMap<String, Object>();
		returnMap.put(EAResource.RETURN_CODE, returnCode);
		out.print(JsonUtil.ObjectToString(returnMap));
		out.flush();
		out.close();
		
	}
	
	private String name;
	
	private int page;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void tel_add_list() throws Exception{
		
		//TODO ���ÿ������
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods","POST");
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
							
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		List<KeyValue> ands = new ArrayList<KeyValue>();
		ands.add(new KeyValue("status", "Y"));
		
		List<KeyValue> likes = new ArrayList<KeyValue>();
		if(name !=null && !name.equals("")){
			likes.add(new KeyValue("name","%"+name+"%"));
		}
		pu = new PageUtil();
		pu.setPageSize(10);
		if(page <= 0){
			pu.setPageNum(1);
		}else{
			pu.setPageNum(page);
		}
		
		int count = approveServ.getCount(Approve.class, ands, likes);
		
		pu.initPage(Approve.class, "userapprove", count);
		
		if(page <= pu.getPageCount()){
			approves = approveServ.getList(pu,ands,likes);
		}
		
		request.setAttribute("pu", pu);
		
		List<KeyValue> aands = new ArrayList<KeyValue>();
		aands.add(new KeyValue("user.id", getCurrentUser().getId()));
		aands.add(new KeyValue("dstatus", "Y"));
		//TODO ��������
		int acount = userApproveServ.getCount(UserApprove.class, aands, null);
		//TODO ��ǰ�û��ȴ�������
		int wcount = userApproveServ.getUserApproveCountByUserIdAndStatus(getCurrentUser().getId(), "W");
		
		
		Map<String,Object> returnMap = new HashMap<String, Object>();
		returnMap.put("approves", approves);
		returnMap.put("acount", acount);
		returnMap.put("wcount", wcount);
		
		out.print(JsonUtil.ObjectToString(returnMap));
		out.flush();
		out.close();
	}
	
	public void tel_addinit() throws Exception{
		
		//TODO ���ÿ������
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods","POST");
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
									
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Approve approve = approveServ.getApproveById(id);
		
		Map<String,Object> returnMap = new HashMap<String, Object>();
		returnMap.put("approve", approve);
		out.print(JsonUtil.ObjectToString(returnMap));
		out.flush();
		out.close();
	}
	
	private String title;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void tel_add() throws Exception{
		
		String returnCode = "000000";
		
		//TODO ���ÿ������
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods","POST");
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
											
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(userApprove == null){
			userApprove = new UserApprove();
			Approve approve = approveServ.getApproveById(id);
			userApprove.setApprove(approve);
			userApprove.setTitle(title);
			userApprove.setContent(content);
			userApprove.setDstatus("Y");
		}
		
		try {
			String push_title = "�����µ����̴�����"; 
			String push_content = userApprove.getTitle();
			
			if(userApprove.getTitle().equals("")){
				returnCode = "002001";
			}else{
				userApprove.setCreateTime(StringUtil.getDateToLong(new Date()));
				userApprove.setUser(getCurrentUser());
				userApprove = userApproveServ.insert2(userApprove);
				if(userApprove.getApprove()!=null){
					//��ѯ�ò��ž���,����������־
					Approve a = approveServ.getApproveById(userApprove.getApprove().getId());
					System.out.println(getCurrentUser());
					List<SysUser> managers = sysUserServ.getSysUserByPostAndDeptId("1", getCurrentUser().getDept().getId());
					if(managers == null || managers.size() == 0){
						returnCode = "002002"; 
					}else{
						Date now = new Date();
						for(SysUser m:managers){
							//��������������־ - stage 0 status - W wait
							ApproveLog log = new ApproveLog();
							log.setApprove(a);
							log.setUa(userApprove);
							log.setToUser(m);
							log.setFromUser(getCurrentUser());
							log.setContent("");
							log.setCreateTime(StringUtil.getDateToLong(now));
							log.setStage(0);
							log.setStatus("W");
							approveLogServ.insert(log);
						}
						
						//TODO ��Ϣ����
						new JPushUtil(push_title, push_content,managers).start();
						
						returnCode = "000000";
					}
					
				}else{
					Date now = new Date();
					List<SysUser> managers = sysUserServ.getSysUserByPostAndDeptId("1", getCurrentUser().getDept().getId());
					
					if(managers == null || managers.size() == 0){
						returnCode = "002002"; 
					}else{
						for(SysUser m:managers){
							//��������������־ - stage 0 status - W wait
							ApproveLog log = new ApproveLog();
							log.setUa(userApprove);
							log.setToUser(m);
							log.setFromUser(getCurrentUser());
							log.setContent("");
							log.setCreateTime(StringUtil.getDateToLong(now));
							log.setStage(0);
							log.setStatus("W");
							approveLogServ.insert(log);
						}
						returnCode = "000000";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnCode = "002003";
		}
		
		Map<String,Object> returnMap = new HashMap<String, Object>();
		returnMap.put(EAResource.RETURN_CODE,returnCode);
		out.print(JsonUtil.ObjectToString(returnMap));
		out.flush();
		out.close();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
