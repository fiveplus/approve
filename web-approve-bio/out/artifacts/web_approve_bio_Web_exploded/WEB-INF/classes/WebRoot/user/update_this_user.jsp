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
    <div class="nav">您当前的位置：<a href="system/index.htm">管理首页</a> > 密码修改 </div>
    <div class="m_m tab">
    	<div class="submenu">
    		<div class="r">
    			<strong><span class="red">*</span> </strong>
    		</div>
    		<div><a href="javscript:void(0)" class="h">密码修改</a></div>
    		<div class="clear"></div>
    	</div>
    	<div class="m_main">
    		<form action="user/update_user.htm" method="post">
    			<s:token />
	    		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tb_2">
	    			<tr>
	    				<th><span class="red"></span> 用户账号：</th>
	    				<td>
	    				<input type="hidden" name="us.id" value="${us.id}" />
	    				${us.loginName}</td>
	    			</tr>
	    			<tr>
	    				<th><span class="red"></span> 用户姓名：</th>
	    				<td>${us.userName}</td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 请输入原密码：</th>
	    				<td>
	    					<input type="password" name="oldpass" class="inp1"  maxlength="80" datatype="Require"  />
	    				</td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 请输入新密码：</th>
	    				<td>
	    					<input type="password" name="newpass" class="inp1"  maxlength="80" datatype="Require"  />
	    				</td>
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
