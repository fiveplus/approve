package com.approve.struts.approve;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.approve.persistence.entity.ApproveType;
import com.approve.services.KeyValue;
import com.approve.struts.BaseAction;
import com.approve.struts.util.StringUtil;

public class ApproveTypeAction extends BaseAction{
	
	private List<ApproveType> types;
	private ApproveType type;
	
	public List<ApproveType> getTypes() {
		return types;
	}

	public void setTypes(List<ApproveType> types) {
		this.types = types;
	}

	public ApproveType getType() {
		return type;
	}

	public void setType(ApproveType type) {
		this.type = type;
	}
	

	public String list() throws Exception{
		List<KeyValue> likes = new ArrayList<KeyValue>();
		List<KeyValue> ands = new ArrayList<KeyValue>();
		ands.add(new KeyValue("status", "Y"));
		
		selectValue = selectValue==null?null:selectValue.trim();
		if(selectValue != null && !selectValue.equals("")){
			likes.add(new KeyValue("name", "%"+selectValue+"%"));
		}
		
		int count = approveTypeServ.getCount(ApproveType.class, ands, likes);
		pu.initPage(ApproveType.class, "approvetype", count);
		
		types = approveTypeServ.getList(pu, ands, likes);
		
		request.setAttribute("pu", pu);
		
		return SUCCESS;
	}
	
	public String addinit() throws Exception{
		request.setAttribute("typr", type);
		return SUCCESS;
	}
	
	public String add() throws Exception{
		try {
			ApproveType t = approveTypeServ.getApproveTypeByName(type.getName());
			if(2<1){
				message = "× 该流程类型已经存在，请检查后重新输入!";
				return SUCCESS;
			}else{
				if(type.getName().equals("")){
					message = "× 请输入流程类型名称!";
				}else{
					type.setCreateTime(StringUtil.getDateToLong(new Date()));
					type.setStatus("Y");
					int flag = approveTypeServ.insert(type);
					if(flag > 0){
						message = "√ 恭喜您，流程类型创建成功!";
						returnURL = "approvetype/list.htm?pu.pageNum=1";
					}else{
						message = "× 很抱歉，流程类型创建失败!";
						request.setAttribute("type", type);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = e.toString();
			request.setAttribute("type", type);
		}
		
		return SUCCESS;
	}
	
	public String updateInit() throws Exception{
		type = approveTypeServ.getApproveTypeById(id);
		return SUCCESS;
	}
	
	public String update() throws Exception{
		try{
			ApproveType t = approveTypeServ.getApproveTypeByName(type.getName());
			if(t!=null&&!t.getName().equals(type.getName())){
				message = "× 流程类型重复，请检查后重新输入!";
				request.setAttribute("type", type);
			}else{
				if(type.getName().equals("")){
					message = "× 请输入流程类型名称!";
				}else{
					int flag = approveTypeServ.update(type);
					if(flag > 0){
						message = "√ 流程类型修改成功!";
						returnURL = "approvetype/list.htm?pu.pageNum=1";
					}else{
						message = "× 很抱歉，流程类型修改失败!";
						request.setAttribute("type", type);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			message = e.toString();
			request.setAttribute("type", type);
		}
		return SUCCESS;
	}
	
	public void delete() throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		message = "√ 恭喜您，流程类型删除成功!";
		
		type = approveTypeServ.getApproveTypeById(id);
		if(type.getStatus().equals("Y")){
			type.setStatus("N");
			approveTypeServ.update(type);
		}else{
			message = "× 流程类型已经删除!";
		}
		
		out.print(message);
		out.flush();
		out.close();
	}
	
	
	
	
	
}
