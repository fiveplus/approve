<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<jsp:include page="common/top.jsp" />
<div class="main">
  <jsp:include page="common/menu.jsp" />
  <div class="main-right r">
    <div class="nav">您当前的位置：<a href="system/index.htm">管理首页</a> > 网站设置 </div>
    <div class="m_m tab">
    	<div class="submenu">
    		<div class="r">
    			<strong><span class="red">*</span> 请输入网站详细资料</strong>
    		</div>
    		<div><a href="javscript:void(0)" class="h">网站设置</a></div>
    		<div class="clear"></div>
    	</div>
    	<div class="m_main">
	    		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tb_2">
	    			<tr>
	    				<th><span class="red">*</span> 登录标题：</th>
	    				<td><input name="s" type="button" value="设置" class="btn_1" onclick="post_url('setting/titleInit.htm')" /></td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 登录背景：</th>
	    				<td><input name="s" type="button" value="设置" class="btn_1" onclick="post_url('setting/backInit.htm')" /></td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 自由审批设置：</th>
	    				<td>
	    					<input name="s" type="button" value="设置" class="btn_1" onclick="post_url('setting/freeApproveInit.htm')" />
	    				</td>
	    			</tr>
	    		</table>
    	</div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="common/bottom.jsp" />
</body>
</html>
