<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="date" uri="/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>${set.title}</title>
	<link href="img/s.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
  </head>
  
<body>
<jsp:include page="../common/top.jsp" />
<div class="main">
  <jsp:include page="../common/menu.jsp" />
  <div class="main-right r">
    <div class="nav">您当前的位置：<a href="system/index.htm">管理首页</a> > <a href="user/list.htm?pu.apgeNum=1&">用户管理</a> > 用户新增 </div>
    <div class="m_m tab">
    	<div class="submenu">
    		<div class="r">
    			<strong><span class="red">*</span> *请输入用户详细资料(默认密码为123456)</strong>
    		</div>
    		<div><a href="javscript:void(0)" class="h">用户新增</a></div>
    		<div class="clear"></div>
    	</div>
    	<div class="m_main">
    		<form action="user/update.htm" method="post">
    			<s:token />
	    		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tb_2">
	    			<tr>
	    				<th><span class="red">*</span> 请选择部门：</th>
	    				<td>
	    					<input type="hidden" name="us.id" value="${us.id}" />
	    					<select name="us.dept.id" style="width:200px;">
	    						<c:forEach items="${depts}" var="d">
									<option value="${d.id}" <c:if test="${us.dept.id == d.id}">selected="selected"</c:if>>${d.name}</option>
	    						</c:forEach>	
	    					</select>
	    				</td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 请输入用户账号：</th>
	    				<td><input type="text" name="us.loginName" class="inp1"  maxlength="80" datatype="Require" value="${us.loginName}"  /></td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 请输入用户姓名：</th>
	    				<td><input type="text" name="us.userName" class="inp1"  maxlength="80" datatype="Require" value="${us.userName}"  /></td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 请选择用户职位：</th>
	    				<td>
	    					<select name="us.post" style="width:200px;">
	    						<c:forEach items="${POST}" var="p">
									<option value="${p.key}" <c:if test="${us.post == p.key}">selected="selected"</c:if>>${p.value}</option>
	    						</c:forEach>	
	    					</select>
	    				</td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 请输入新密码：</th>
	    				<td><input type="password" name="us.password" class="inp1"  maxlength="80" datatype="Require" /></td>
	    			</tr>
	    		</table>
	    		<div class="btnbox">
	    			<input name="s" type="submit" value="提交" class="btn_1" />
					&nbsp;
					<input name="s" type="button" value="返回" class="btn_2" onclick="go_back()" />
	    		</div>
    		</form>
    	</div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="../common/bottom.jsp" />
</body>
</html>
