package com.approve.struts.archive;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.approve.persistence.entity.ArchiveLog;
import com.approve.persistence.entity.UserApprove;
import com.approve.services.KeyValue;
import com.approve.struts.BaseAction;
import com.approve.struts.util.JsonUtil;
import com.approve.struts.util.PageUtil;

public class ArchiveAction extends BaseAction{
	
	private List<ArchiveLog> list;
	private ArchiveLog al;
	
	public List<ArchiveLog> getList() {
		return list;
	}

	public void setList(List<ArchiveLog> list) {
		this.list = list;
	}

	public void setAl(ArchiveLog al) {
		this.al = al;
	}

	public ArchiveLog getAl() {
		return al;
	}

	public String list() throws Exception{
		
		//TODO status=D±íÊ¾ÒÑ×÷·Ï
		List<KeyValue> nos = new ArrayList<KeyValue>();
		nos.add(new KeyValue("status", "D"));
		
		List<KeyValue> ands = new ArrayList<KeyValue>();
		ands.add(new KeyValue("user.id", getCurrentUser().getId()));
		
		List<KeyValue> likes = new ArrayList<KeyValue>();
		
		selectValue = selectValue==null?null:selectValue.trim();
		if(selectValue != null && !selectValue.equals("")){
			likes.add(new KeyValue("ua.title", "%"+selectValue+"%"));
			likes.add(new KeyValue("ua.approveNum", "%"+selectValue+"%"));
			likes.add(new KeyValue("ua.user.userName", "%"+selectValue+"%"));
			request.setAttribute("selectValue", selectValue);
		}
		
		int count = archiveLogServ.getCount(ArchiveLog.class, ands, likes);
		pu.initPage(ArchiveLog.class, "archive", count);
		
		list = archiveLogServ.getList(pu, ands, likes);
		request.setAttribute("pu", pu);
		
		return SUCCESS;
	}
	
	public String archive() throws Exception{
		al = archiveLogServ.getArchiveLogById(id);
		return SUCCESS;
	}
	
	public String update() throws Exception{
		try {
			int flag = archiveLogServ.update(al);
			if(flag > 0){
				message = "¡Ì ¹§Ï²Äú£¬¹éµµ¸üÐÂ³É¹¦!";
				if(getCurrentUser().getIsAdmin().equals("Y")){
					returnURL = "archive/list_admin.htm?pu.pageNum=1";
				}else{
					returnURL = "archive/list.htm?pu.pageNum=1";
				}
				
			}else{
				message = "¡Á ºÜ±§Ç¸£¬¹éµµ¸üÐÂÊ§°Ü!";
			}
		} catch (Exception e) {
			message = e.toString();
		}
		return SUCCESS;
	}
	
	public String list_admin() throws Exception{
		List<KeyValue> nos = new ArrayList<KeyValue>();
		nos.add(new KeyValue("status", "D"));
		
		List<KeyValue> ands = new ArrayList<KeyValue>();
		//ands.add(new KeyValue("user.id", getCurrentUser().getId()));
		
		List<KeyValue> likes = new ArrayList<KeyValue>();
		
		selectValue = selectValue==null?null:selectValue.trim();
		if(selectValue != null && !selectValue.equals("")){
			likes.add(new KeyValue("ua.title", "%"+selectValue+"%"));
			likes.add(new KeyValue("ua.approveNum", "%"+selectValue+"%"));
			likes.add(new KeyValue("ua.user.userName", "%"+selectValue+"%"));
			request.setAttribute("selectValue", selectValue);
		}
		
		int count = archiveLogServ.getCount(ArchiveLog.class, ands, likes,nos);
		pu.initPage(ArchiveLog.class, "archive", count);
		
		list = archiveLogServ.getList(pu, ands, likes,nos);
		request.setAttribute("pu", pu);
		
		return SUCCESS;
	}
	
	
	public String del_list() throws Exception{
		List<KeyValue> ands = new ArrayList<KeyValue>();
		ands.add(new KeyValue("status", "D"));
		
		if(getCurrentUser().getIsAdmin().equals("N")){
			ands.add(new KeyValue("user.id", getCurrentUser().getId()));
		}
		
		
		List<KeyValue> likes = new ArrayList<KeyValue>();
		
		if(selectValue != null && !selectValue.equals("")){
			likes.add(new KeyValue("ua.title", "%"+selectValue+"%"));
			likes.add(new KeyValue("ua.approveNum", "%"+selectValue+"%"));
			likes.add(new KeyValue("ua.user.userName", "%"+selectValue+"%"));
			request.setAttribute("selectValue", selectValue);
		}
		
		int count = archiveLogServ.getCount(ArchiveLog.class, ands, likes);
		pu.initPage(ArchiveLog.class, "archive", count);
		
		list = archiveLogServ.getList(pu, ands, likes);
		request.setAttribute("pu", pu);
		
		return SUCCESS;
	}
	
	
	public void delete() throws Exception{
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		message = "¡Ì ¹§Ï²Äú£¬¹éµµ¼ÇÂ¼×÷·Ï³É¹¦!";
		
		//int flag = archiveLogServ.delete(id);
		al = archiveLogServ.getArchiveLogById(id);
		if(al != null){
			al.setStatus("D");
		}
		
		int flag = archiveLogServ.update(al);
		if(flag > 0){
			message = "¡Ì ¹§Ï²Äú£¬¹éµµ¼ÇÂ¼×÷·Ï³É¹¦!";
		}else{
			message = "¡Á ºÜ±§Ç¸£¬¹éµµ¼ÇÂ¼×÷·ÏÊ§°Ü!";
		}
		
		out.print(message);
		out.flush();
		out.close();
	}
	
	public String recover() throws Exception{
		al = archiveLogServ.getArchiveLogById(id);
		if(al != null){
			al.setStatus("N");
		}
		int flag = archiveLogServ.update(al);
		if(flag > 0){
			message = "¡Ì ¹§Ï²Äú£¬¹éµµ¼ÇÂ¼»Ö¸´³É¹¦!";
			returnURL = "archive/del_list.htm?pu.pageNum=1";
		}else{
			message = "¡Á ºÜ±§Ç¸£¬¹éµµ¼ÇÂ¼»Ö¸´Ê§°Ü!";
		}
		
		return SUCCESS;
	}
	
	private int page;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public void tel_all_archives() throws Exception{
		
		//TODO ÉèÖÃ¿çÓò·ÃÎÊ
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods","POST");
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
									
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		List<KeyValue> nos = new ArrayList<KeyValue>();
		nos.add(new KeyValue("status", "D"));
		
		List<KeyValue> ands = new ArrayList<KeyValue>();
		//ands.add(new KeyValue("user.id", getCurrentUser().getId()));
		
		List<KeyValue> likes = new ArrayList<KeyValue>();
		
		pu = new PageUtil();
		pu.setPageSize(10);
		if(page <= 0){
			pu.setPageNum(1);
		}else{
			pu.setPageNum(page);
		}
		
		int count = archiveLogServ.getCount(ArchiveLog.class, ands, likes,nos);
		pu.initPage(ArchiveLog.class, "archive", count);
		
		list = archiveLogServ.getList(pu, ands, likes,nos);
		request.setAttribute("pu", pu);
		
		Map<String,Object> returnMap = new HashMap<String, Object>();
		returnMap.put("archives", list);
		
		out.print(JsonUtil.ObjectToString(returnMap));
		out.flush();
		out.close();
		
	}
	
	public void tel_archive() throws Exception{
		//TODO ÉèÖÃ¿çÓò·ÃÎÊ
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods","POST");
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
			
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		al = archiveLogServ.getArchiveLogById(id);
		
		
		Map<String,Object> returnMap = new HashMap<String, Object>();
		returnMap.put("al", al);
		
		out.print(JsonUtil.ObjectToString(returnMap));
		out.flush();
		out.close();
	}
	
	
	
	
}
