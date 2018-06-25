<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <div class="nav">您当前的位置：管理首页</div>
    <div class="m_m">
    	<div>
			<h2><b>${user.userName }</b> 你好！</h2>
			你本次登录时间：${loginTime}<br/><br/>
			<input type="button" onclick="post_url('userapprove/user_approves.htm?pu.pageNum=1')" value="当前等待审批数量：${wcount}" class="btn_1" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" onclick="post_url('userapprove/list.htm?pu.pageNum=1')"  value="当前流程数量：${count}" class="btn_1" />
			<br/>
		</div>
		<div class="home">
			<!-- 此处插入正文 -->
		</div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="common/bottom.jsp" />
</body>
</html>
