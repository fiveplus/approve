package com.approve.struts.util;

import java.util.List;

import com.approve.struts.util.PageCode;

public class PageUtil {
	/**
	 * HqlÓï¾ä±ðÃû
	 */
	public static final String TEMP = "a";
	
	private String list = "list";
	
	
	public PageUtil(){
		
	}
	
	private List<Integer> pageList;
	
	public void initPage(Class clazz,String nameSpace,int count){
		setClazz(clazz);
		setCount(count);
		setPageCount(initPageCount(count));
		if(pageNum < 1){
			pageNum = 1;
		}
		if(pageNum > pageCount){
			pageNum = pageCount;
		}
		PageCode pc = new PageCode(pageNum, pageCount);
		pageList = pc.getPageList();
		
		setPageHTML(addPageHTML(nameSpace,pageNum, pageCount));
	}
	
	public void initPage(String list,Class clazz,String nameSpace,int count){
		this.list = list;
		setClazz(clazz);
		setCount(count);
		setPageCount(initPageCount(count));
		if(pageNum < 1){
			pageNum = 1;
		}
		if(pageNum > pageCount){
			pageNum = pageCount;
		}
		setPageHTML(addPageHTML(nameSpace,pageNum, pageCount));
	}
	
	public void initPage(Class clazz,String nameSpace,int count,String id){
		setClazz(clazz);
		setCount(count);
		setPageCount(initPageCount(count));
		if(pageNum < 1){
			pageNum = 1;
		}
		if(pageNum > pageCount){
			pageNum = pageCount;
		}
		setPageHTML(addPageHTML2(nameSpace,pageNum, pageCount,id));
	}
	
	
	private String param = "";
	
	public void setOtherParam(String param){
		this.param = param;
	}
	
	public List<Integer> getPageList() {
		return pageList;
	}

	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}

	private String pageHTML;
	
	private int count;
	
	private Class clazz;
	
	private int pageSize = 10;
	
	private int pageNum;
	
	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public PageUtil(Class clazz,int count,int pageSize){
		this.clazz = clazz;
		this.pageSize = pageSize;
		this.setCount(count);
	}
	
	public PageUtil(Class clazz,int count){
		this.clazz = clazz;
		this.setCount(count);
		this.pageSize = 10;
	}
	
	public String getHql(){
		String clazzName = clazz.getSimpleName();
		String hql = "FROM " + clazzName + " " + TEMP + " where 1 = 1 ";
		return hql;
	}
	
	private int pageCount;

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	public int initPageCount(int count){
		int pageCount = 1;
		if(count%pageSize==0){
			pageCount = count/pageSize; 
		}else{
			pageCount = count/pageSize + 1; 
		}
		if(count==0){
			pageCount = 1;
		}
		return pageCount;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}

	public String getPageHTML() {
		return pageHTML;
	}
	
	public String addPageHTML(String nameSpace,int pageNum,int pageCount){
		String html = "";
		PageCode pc = new PageCode(pageNum, pageCount);
		List<Integer> pageList = pc.getPageList();
		
		if(pageList != null && pageList.size() > 0){
			//Integer left = pageList.get(0);
			//Integer right = pageList.get(pageList.size()-1);
			
			String l = pageNum == 1 ? addLiLeft(nameSpace,false) : addLiLeft(nameSpace,true);
			String r = pageNum == pageCount ? addLiRight(nameSpace,pageCount,false) : addLiRight(nameSpace,pageCount,true);
			String c = "";
			for(Integer page:pageList){
				 c = page == pageNum ? c + addChild(nameSpace,page,true) : c + addChild(nameSpace,page,false);
			}
			html = l + c + r;
		}
		return html;
	}
	
	private String addLiLeft(String nameSpace,boolean flag){
		String clazz = "prev";
		String func = "";
		if(!flag){
			clazz += " disabled";
			func = "javascript:void(0)";
		}else{
			func = "javascript:loadHTML('"+nameSpace+"/"+list+".htm?pu.pageNum=1&"+param+"')";
		}
		return "<li class='"+clazz+"'><a href=\""+func+"\"><i class='icon-double-angle-left'></i></a></li>" ;
	}
	private String addLiRight(String nameSpace,int pageCount,boolean flag){
		String func = "";
		String clazz = "next";
		if(!flag){
			clazz += " disabled";
			func = "javascript:void(0)";
		}else{
			func = "javascript:loadHTML('"+nameSpace+"/"+list+".htm?pu.pageNum="+pageCount+"&"+param+"')";
		}
		return "<li class='"+clazz+"'><a href=\""+func+"\"><i class='icon-double-angle-right'></i></a></li>" ;
	}
	private String addChild(String nameSpace,int page,boolean flag){
		String html = "<li ";
		if(flag){
			html+= "class='active'";
		}
		html+= "><a href=\"javascript:loadHTML('"+nameSpace+"/"+list+".htm?pu.pageNum="+page+"&"+param+"')\">" + page + "</a></li>";
		return html;
	}
	
	
	
	
	
	
	
	
	
	
	public String addPageHTML2(String nameSpace,int pageNum,int pageCount,String id){
		String html = "";
		PageCode pc = new PageCode(pageNum, pageCount);
		List<Integer> pageList = pc.getPageList();
		
		if(pageList != null && pageList.size() > 0){
			//Integer left = pageList.get(0);
			//Integer right = pageList.get(pageList.size()-1);
			String l = pageNum == 1 ? addLiLeft2(nameSpace,false,id) : addLiLeft2(nameSpace,true,id);
			String r = pageNum == pageCount ? addLiRight2(nameSpace,pageCount,false,id) : addLiRight2(nameSpace,pageCount,true,id);
			String c = "";
			for(Integer page:pageList){
				 c = page == pageNum ? c + addChild2(nameSpace,page,true,id) : c + addChild2(nameSpace,page,false,id);
			}
			html = l + c + r;
		}
		return html;
	}
	
	
	
	private String addLiLeft2(String nameSpace,boolean flag,String id){
		String clazz = "prev";
		String func = "";
		if(!flag){
			clazz += " disabled";
			func = "javascript:void(0)";
		}else{
			func = "javascript:loadHTML2('"+nameSpace+"/"+list+".htm?pu.pageNum=1&"+param+"','"+id+"')";
		}
		return "<li class='"+clazz+"'><a href=\""+func+"\"><i class='icon-double-angle-left'></i></a></li>" ;
	}
	private String addLiRight2(String nameSpace,int pageCount,boolean flag,String id){
		String func = "";
		String clazz = "next";
		if(!flag){
			clazz += " disabled";
			func = "javascript:void(0)";
		}else{
			func = "javascript:loadHTML2('"+nameSpace+"/"+list+".htm?pu.pageNum="+pageCount+"&"+param+"','"+id+"')";
		}
		return "<li class='"+clazz+"'><a href=\""+func+"\"><i class='icon-double-angle-right'></i></a></li>" ;
	}
	private String addChild2(String nameSpace,int page,boolean flag,String id){
		String html = "<li ";
		if(flag){
			html+= "class='active'";
		}
		html+= "><a href=\"javascript:loadHTML2('"+nameSpace+"/"+list+".htm?pu.pageNum="+page+"&"+param+"','"+id+"')\">" + page + "</a></li>";
		return html;
	}
}
