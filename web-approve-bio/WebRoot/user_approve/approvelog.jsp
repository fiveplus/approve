<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="date" uri="/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>${set.title}</title>
	
	<!-- <link rel="stylesheet" href="assets/css/bootstrap.min.css" />  -->
	<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
	<!-- fonts -->
	<link rel="stylesheet" href="assets/css/ace-fonts.css" />
	<link rel="stylesheet" href="assets/css/ace.min.css" />


		

	<link href="img/s.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
  </head>
  
<body>
<jsp:include page="../common/top.jsp" />
<div class="main">
  <jsp:include page="../common/menu.jsp" />
  <div class="main-right r">
    <div class="nav">您当前的位置：<a href="system/index.htm">管理首页</a> > <a href="userapprove/list.htm?pu.apgeNum=1&">我的流程</a> > 查看</div>
    <div class="m_m tab">
    	<div class="submenu">
    		<div class="r">
    			<strong><span class="red"></span> </strong>
    		</div>
    		<div><a href="javscript:void(0)" class="h">查看</a></div>
    		<div class="clear"></div>
    	</div>
    	<div class="m_main">
    		<c:if test="${list ==null || fn:length(list) == 0}">
    			<h3>请不要着急，请耐心等待审核!</h3>
    			<a href="javascript:go_back()">返回</a>
    		</c:if>
    		<c:if test="${list !=null && fn:length(list) > 0}">
    		<form action="userapprove/add.htm" method="post">
	    		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tb_2">
	    			<tr>
	    				<th><span class="red">*</span> 审批标题：</th>
	    				<td>${list[0].ua.title}</td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 详细内容：</th>
	    				<th></th>
	    			</tr>
	    			<tr>
	    				<td colspan="2" >
	    					${list[0].ua.content}
	    				</td>
	    			</tr>
	    			<tr>
	    				<th colspan="2" style="text-align: center;background:#ddd;">
	    					当前进度
	    				</th>
	    			</tr>
	    			<c:forEach items="${list}" var="l">
	    				<c:if test="${l.toUser != null}">
	    					<tr>
		    					<th>节点  ${l.stage+1}：</th>
		    					<td>
		    					[
		    					<c:forEach items="${POST}" var="p">
		    						<c:if test="${l.toUser.post==p.key}">${p.value}</c:if>
		    					</c:forEach>
		    					] ${l.toUser.userName}</td>
		    				</tr>
		    				<tr>
		    					<th>是否同意：</th>
		    					<td>
		    						<c:if test="${l.status == 'W'}">
		    							等待审批
		    						</c:if>
		    						<c:if test="${l.status == 'Y'}">
		    							通过
		    						</c:if>
		    						<c:if test="${l.status == 'N'}">
		    							未通过
		    						</c:if>
		    					</td>
		    				</tr>
		    				<tr>
		    					<th>审批意见：</th>
		    					<th></th>
		    				</tr>
		    				<tr>
			    				<td colspan="2">
			    					${l.content}
			    				</td>
		    				</tr>
	    				</c:if>
	    			</c:forEach>
	    			<c:if test="${ua.status=='Y'}">
	    				<tr>
	    					<th>归档人：</th>
			    			<td>
			    				${ua.approve.archivePeople.userName}
			    			</td>
		    			</tr>
	    			</c:if>
	    		</table>
	    		<div class="btnbox">
					<input name="s" type="button" value="返回" class="btn_2" onclick="go_back()" />
	    		</div>
    		</form>
    		</c:if>
    	</div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="../common/bottom.jsp" />

<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/fuelux/fuelux.tree.min.js"></script>
<!-- ace scripts -->
<script src="assets/js/ace-elements.min.js"></script>

		
<script type="text/javascript" src="js/index.js"></script>

			

</body>
</html>
