<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div class="nav">您当前的位置：<a href="system/index.htm">管理首页</a> > 提示信息 </div>
    <div class="m_m">
    	<h3>${message}</h3>
    	<c:if test="${returnURL == null}">
    		<a href="javascript:go_back()">返回</a>
    	</c:if>
    	<c:if test="${returnURL != null}">
    		<a href="${returnURL}">返回</a>
    	</c:if>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="common/bottom.jsp" />
</body>
</html>
