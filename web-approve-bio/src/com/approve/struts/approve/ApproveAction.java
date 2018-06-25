package com.approve.struts.approve;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.approve.persistence.entity.Approve;
import com.approve.persistence.entity.ApproveType;
import com.approve.persistence.entity.Department;
import com.approve.persistence.entity.SysUser;
import com.approve.services.KeyValue;
import com.approve.struts.BaseAction;
import com.approve.struts.approve.bo.AdditionalParameters;
import com.approve.struts.approve.bo.Item;
import com.approve.struts.approve.bo.TreeRespVO;
import com.approve.struts.util.JsonUtil;
import com.approve.struts.util.StringUtil;

public class ApproveAction extends BaseAction{
	private List<Approve> approves;
	private Approve approve;
	public List<Approve> getApproves() {
		return approves;
	}
	public void setApproves(List<Approve> approves) {
		this.approves = approves;
	}
	public Approve getApprove() {
		return approve;
	}
	public void setApprove(Approve approve) {
		this.approve = approve;
	}
	
	public String list() throws Exception{
		List<KeyValue> likes = new ArrayList<KeyValue>();
		
		List<KeyValue> ands = new ArrayList<KeyValue>();
		ands.add(new KeyValue("status", "Y"));
		
		selectValue = selectValue==null?null:selectValue.trim();
		if(selectValue!=null && !selectValue.equals("")){
			likes.add(new KeyValue("name","%"+selectValue+"%"));
		}
		
		int count = approveServ.getCount(Approve.class, ands, likes);
		
		pu.initPage(Approve.class, "approve", count);
		
		approves = approveServ.getList(pu,ands,likes);
		
		request.setAttribute("pu", pu);
		
		return SUCCESS;
	}
	
	public String add() throws Exception{
		
		System.out.println(approve.getAtype().getId());
		
		try {
			Approve a = approveServ.getApproveByName(approve.getName());
			if(2<1){
				message = "× 该流程已经存在，请检查后重新输入!";
				return SUCCESS;
			}else{
				if(approve.getName().equals("")){
					message = "× 请输入流程名称！";
					return SUCCESS;
				}
				
				//TODO 流程类型
				if(approve.getAtype()!=null){
					if(approve.getAtype().getId().equals("")){
						approve.setAtype(null);
					}
				}
				
				
				if(approve.getApproveType().equals("0")){
					//TODO 流程审批1型
					if(approve.getApprovePeople()!=null){
						if(approve.getApprovePeople().getId().equals("")){
							message = "× 请选择业务审批人！";
							return SUCCESS;
						}
					}
					if(approve.getControlPeople().equals("")){
						approve.setControlPeople(null);
					}
				}else if(approve.getApproveType().equals("1")){
					//TODO 流程审批2型
					if(approve.getControlPeople().equals("")){
						message = "× 请选择业务相关审核人员！";
						return SUCCESS;
					}
					if(approve.getApprovePeople()!=null){
						if(approve.getApprovePeople().getId().equals("")){
							approve.setApprovePeople(null);
						}
					}
				}
				
				if(approve.getArchivePeople()!=null){
					if(approve.getArchivePeople().getId().equals("")){
						message = "× 请选择业务归档人员！";
						return SUCCESS;
					}
				}
				
	
				
				if(approve.getManager()!=null){
					if(approve.getManager().getId().equals("")){
						approve.setManager(null);
					}
				}
				if(approve.getChairman()!=null){
					if(approve.getChairman().getId().equals("")){
						approve.setChairman(null);
					}
				}
				
				if(approve.getLayera()!=null){
					if(approve.getLayera().getId().equals("")){
						approve.setLayera(null);
					}
				}
				if(approve.getLayerb()!=null){
					if(approve.getLayerb().getId().equals("")){
						approve.setLayerb(null);
					}
				}

				if(approve.getOffice()!=null){
					if(approve.getOffice().getId().equals("")){
						approve.setOffice(null);
					}
				}
				
				/*
				if(approve.getLayerPeople()!=null){
					if(approve.getLayerPeople().getId()!=null){
						approve.setLayerPeople(null);
					}
				}*/
				approve.setStatus("Y");
				approve.setCreateTime(StringUtil.getDateToLong(new Date()));
				int flag = approveServ.insert(approve);
				if(flag > 0){
					message = "恭喜您，流程创建成功!";
					returnURL = "approve/list.htm?pu.pageNum=1";
				}else{
					message = "很抱歉，流程创建失败!";
					returnURL = "approve/addinit.htm";
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			message = e.toString();
		}
		return SUCCESS;
	}
	
	public String addinit() throws Exception{
		List<Department> depts = departmentServ.getList();
		//查询高层管理人员
		List<SysUser> highusers = sysUserServ.getSysUserByPost("0");
		
		//TODO 查询流程类型
		List<ApproveType> types = approveTypeServ.getList();
		
		request.setAttribute("types", types);
		
		request.setAttribute("highusers", highusers);
		request.setAttribute("depts", depts);
		
		request.setAttribute("approve", approve);
		
		return SUCCESS;
	}
	
	public String updateInit() throws Exception{
		
		List<Department> depts = departmentServ.getList();
		//查询高层管理人员
		List<SysUser> highusers = sysUserServ.getSysUserByPost("0");
		
		//TODO 查询流程类型
		List<ApproveType> types = approveTypeServ.getList();
		request.setAttribute("types", types);
		
		request.setAttribute("highusers", highusers);
		request.setAttribute("depts", depts);
		
		request.setAttribute("approve", approve);
		
		approve = approveServ.getApproveById(id);
		
		if(approve.getControlPeople()!=null){
			List<String> ins = Arrays.asList(approve.getControlPeople().split(","));
			List<SysUser> controls = sysUserServ.getList(SysUser.class, ins);
			request.setAttribute("controls", controls);
		}
		
		
		String deptId = approve.getArchivePeople().getDept().getId();
		List<SysUser> archives =  sysUserServ.getSysUserByDeptId(deptId);
		request.setAttribute("archives", archives);
		
		if(approve.getApprovePeople()!=null){
			deptId = approve.getApprovePeople().getDept().getId();
			List<SysUser> approves =  sysUserServ.getSysUserByDeptId(deptId);
			request.setAttribute("approves", approves);
		}
		
		if(approve.getOffice()!=null){
			deptId = approve.getOffice().getDept().getId();
			List<SysUser> offices = sysUserServ.getSysUserByDeptId(deptId);
			request.setAttribute("offices", offices);
		}
		
		
		/*
		if(approve.getLayerPeople()!=null){
			deptId = approve.getLayerPeople().getDept().getId();
			List<SysUser> layers =  sysUserServ.getSysUserByDeptId(deptId);
			request.setAttribute("layers", layers);
		}*/
		
		
		return SUCCESS;
	}
	
	public String update() throws Exception{
		try {
			Approve a = approveServ.getApproveByName(approve.getName());
			if(a!=null && !a .getName().equals(approve.getName())){
				message = "× 该流程已经存在，请检查后重新输入!";
			}else{
				
				if(approve.getName().equals("")){
					message = "× 请输入流程名称！";
					return SUCCESS;
				}
				
				//TODO 流程类型
				if(approve.getAtype()!=null){
					if(approve.getAtype().getId().equals("")){
						approve.setAtype(null);
					}
				}
				
				if(approve.getApproveType().equals("0")){
					//TODO 流程审批1型
					if(approve.getApprovePeople()!=null){
						if(approve.getApprovePeople().getId().equals("")){
							message = "× 请选择业务审批人！";
							return SUCCESS;
						}
					}
					if(approve.getControlPeople().equals("")){
						approve.setControlPeople(null);
					}
				}else if(approve.getApproveType().equals("1")){
					//TODO 流程审批2型
					if(approve.getControlPeople().equals("")){
						message = "× 请选择业务相关审核人员！";
						return SUCCESS;
					}
					if(approve.getApprovePeople()!=null){
						if(approve.getApprovePeople().getId().equals("")){
							approve.setApprovePeople(null);
						}
					}
				}
				
				if(approve.getArchivePeople()!=null){
					if(approve.getArchivePeople().getId().equals("")){
						message = "× 请选择业务归档人员！";
						return SUCCESS;
					}
				}
				
				/*
				if(approve.getManager()!=null){
					if(approve.getManager().getId().equals("")){
						approve.setManager(null);
					}
				}
				if(approve.getChairman()!=null){
					if(approve.getChairman().getId().equals("")){
						approve.setChairman(null);
					}
				}
				
				if(approve.getLayera()!=null){
					if(approve.getLayera().getId().equals("")){
						approve.setLayera(null);
					}
				}
				if(approve.getLayerb()!=null){
					if(approve.getLayerb().getId().equals("")){
						approve.setLayerb(null);
					}
				}

				if(approve.getOffice()!=null){
					if(approve.getOffice().getId().equals("")){
						approve.setOffice(null);
					}
				}*/
				
				int flag = approveServ.update(approve);
				if(flag > 0){
					message = "√ 恭喜您，流程修改成功!";
					returnURL = "approve/list.htm?pu.pageNum=1";
				}else{
					message = "× 很抱歉，流程修改失败!";
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			message =e.toString();
		}
		return SUCCESS;
	}
	
	public void users() throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		TreeRespVO vo = new TreeRespVO();
		
		if(id == null || id.equals("")){
			//查询所有部门
			List<Department> depts = departmentServ.getList();
			List<Item> list = new ArrayList<Item>();
			for(Department d:depts){
				Item t = new Item();
				int count = sysUserServ.getUserCountByDeptId(d.getId());
				t.setName(d.getName());
				t.setType("folder");
				if(count > 0){
					AdditionalParameters adp = new AdditionalParameters();
					adp.setId(d.getId());
					t.setAdditionalParameters(adp);
				}
				list.add(t);
			}
			vo.setData(list);
			out.print(JsonUtil.ObjectToString(vo));
			
		}else{
			//查询某部门用户
			List<Item> list = new ArrayList<Item>();
			List<SysUser> users = sysUserServ.getSysUserByDeptId(id);
			for(SysUser u:users){
				Item t = new Item();
				AdditionalParameters adp = new AdditionalParameters(); 
				adp.setId(u.getId());
				adp.setItemSelected(false);
				t.setName(u.getUserName());
				t.setType("item");
				t.setAdditionalParameters(adp);
				list.add(t);
			}
			vo.setData(list);
			out.print(JsonUtil.ObjectToString(vo));
		}
		
		out.flush();
		out.close();
	}
	
	public void double_select() throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		List<SysUser> users = sysUserServ.getSysUserByDeptId(id);
		
		out.print(JsonUtil.ObjectToString(users));
		
		out.flush();
		out.close();
	}
	
	public String select() throws Exception{
		approve = approveServ.getApproveById(id);
		if(approve.getControlPeople() != null ){
			List<String> ins = Arrays.asList(approve.getControlPeople().split(","));
			List<SysUser> controls = sysUserServ.getList(SysUser.class, ins);
			request.setAttribute("controls", controls);
		}
		return SUCCESS;
	}
	
	public void delete() throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		message = "√ 恭喜您，流程删除成功!";

		approve = approveServ.getApproveById(id);
		
		if(approve.getStatus().equals("Y")){
			approve.setStatus("N");
			approveServ.update(approve);
		}else{
			message = "× 流程已经删除!";
		}
		
		out.print(message);
		
		out.flush();
		out.close();
	}
	
	
}
