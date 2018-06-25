<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:if test="${user.isAdmin == 'Y'}">
<div class="main-left l">
	  <a href="system/index.htm" >管理首页 <i class="a_1"></i></a>
	  <a href="system/setting.htm">网站设置<i class="a_2"></i></a>
	  <a href="user/list.htm?pu.pageNum=1">用户管理 <i class="a_2"></i></a> 
	  <a href="dept/list.htm?pu.pageNum=1">部门管理 <i class="a_3"></i></a> 
	  <a href="approvetype/list.htm?pu.pageNum=1">流程类型管理 <i class="a_3"></i></a> 
	  <a href="approve/list.htm?pu.pageNum=1">流程管理 <i class="a_4"></i></a> 
	  <a href="userapprove/add_list.htm?pu.pageNum=1">发起审批 <i class="a_5"></i></a> 
	  <a href="userapprove/list.htm?pu.pageNum=1">待办审批 <i class="a_6"></i></a>
	  <a href="userapprove/list.htm?pu.pageNum=1">在途审批 <i class="a_7"></i></a>
	  <a href="archive/list_admin.htm?pu.pageNum=1">审批归档 <i class="a_8"></i></a>
	  <a href="userapprove/del_list.htm?pu.pageNum=1">作废流程 <i class="a_8"></i></a>
	  <a href="archive/del_list.htm?pu.pageNum=1">作废归档 <i class="a_8"></i></a>
	  <!-- <a href="userapprove/list.htm?pu.pageNum=1">审批查询 <i class="a_9"></i></a>
	  <a href="userapprove/list.htm?pu.pageNum=1">审批列表 <i class="a_10"></i></a> -->
</div>
</c:if>
<c:if test="${user.isAdmin == 'N'}">
<div class="main-left l">
	<a href="system/index.htm" >管理首页 <i class="a_1"></i></a>
	<c:if test="${fa != null}">
		<a href="userapprove/addfree_init.htm">自由审批 <i class="a_5"></i></a> 
	</c:if>
	<a href="userapprove/add_list.htm?pu.pageNum=1">发起审批 <i class="a_5"></i></a> 
	<a href="userapprove/list.htm?pu.pageNum=1" >我的流程 <i class="a_1"></i></a>
	<a href="userapprove/user_approves.htm?pu.pageNum=1" >我的审批<i class="a_1"></i></a>
	<a href="archive/list.htm?pu.pageNum=1" >我的归档 <i class="a_1"></i></a>
	
	<c:if test="${user.post == '0' }">
		<a href="userapprove/list_all.htm?pu.pageNum=1">待办审批 <i class="a_6"></i></a>
		<a href="archive/list_admin.htm?pu.pageNum=1">其他归档 <i class="a_8"></i></a>
	</c:if>
	
	 <a href="userapprove/del_list.htm?pu.pageNum=1">作废流程 <i class="a_8"></i></a>
	  <a href="archive/del_list.htm?pu.pageNum=1">作废归档 <i class="a_8"></i></a>
</div>
</c:if>
