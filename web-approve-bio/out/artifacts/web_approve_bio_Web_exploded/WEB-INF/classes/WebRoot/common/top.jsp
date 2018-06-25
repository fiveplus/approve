<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="top">
  <div class="m">
    <div class="logo l"><img src="img/huangshi.jpg" /></div>
    <div class="r"><a href="user/update_this_init.htm?id=${user.id}" class="a1">${user.userName }</a> <a href="system/logout.htm" class="a3">退出</a></div>
    <div class="clear"></div>
  </div>
</div>
