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
	
	<!-- <link rel="stylesheet" href="assets/css/bootstrap.min.css" /> -->
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
    <div class="nav">您当前的位置：<a href="system/index.htm">管理首页</a> > <a href="userapprove/user_approves.htm?pu.apgeNum=1&">我的审批</a> > 查看</div>
    <div class="m_m tab">
    	<div class="submenu">
    		<div class="r">
    			<strong><span class="red">*</span> 请查看详细资料</strong>
    		</div>
    		<div><a href="javscript:void(0)" class="h">我的审批</a></div>
    		<div class="clear"></div>
    	</div>
    	<div class="m_main">
    		<table ></table>
    		<form action="userapprove/approve_update.htm" method="post" onsubmit="return check_submit()">
    			<s:token />
	    		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tb_2">
	    			<c:if test="${log.stage > 0}">
	    			<tr>
	    				<th style="background: #ddd;text-align: center;" colspan="2">
	    					历史节点
	    				</th>
	    			</tr>
	    			<c:forEach items="${oldlogs}" var="l">
	    			<c:if test="${l.toUser!=null}">
	    				<tr>
	    					<th colspan="2" style="text-align: center;">节点 ${l.stage}</th>
	    				</tr>
	    				<tr>
	    					<th>审批人：</th>
	    					<td>
	    						[
		    					<c:forEach items="${POST}" var="p">
		    						<c:if test="${l.toUser.post==p.key}">${p.value}</c:if>
		    					</c:forEach>
		    					] ${l.toUser.userName}
	    					</td>
	    				</tr>
	    				<tr>
	    				<th>是否同意：</th>
	    					<td>
	    					<c:if test="${l.status=='Y'}">同意</c:if>
	    					<c:if test="${l.status=='N'}">不同意</c:if>
	    					</td>
	    				</tr>
	    				<tr>
	    					<th>审批意见：</th>
	    					<td></td>
	    				</tr>
	    				<tr>
	    					<td colspan="2">
	    					${l.content}
	    					</td>
	    				</tr>
	    			</c:if>
	    			</c:forEach>
	    			</c:if>
	    			<tr>
	    				<th style="background: #ddd;text-align: center;" colspan="2">
	    					本节点
	    				</th>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 审批标题：</th>
	    				<td>
	    				<input type="hidden" name="log.id" value="${log.id}" />
	    				${log.ua.title}</td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 流程名称：</th>
	    				<td>
	    					${log.approve.name}
	    				</td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 发起人：</th>
	    				<td>${log.fromUser.userName}</td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 详细内容：</th>
	    				<th></th>
	    			</tr>
	    			<tr>
	    				<td colspan="2">
	    					${log.ua.content}
	    				</td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 是否同意：</th>
	    				<td>
	    					<c:if test="${log.status=='W'}">
	    						<select name="log.status">
		    						<option value="Y">同意</option>
		    						<option value="N">不同意</option>
	    						</select>
	    					</c:if>
	    					<c:if test="${log.status=='Y'}">
	    						同意
	    					</c:if>
	    					<c:if test="${log.status=='N'}">
	    						不同意
	    					</c:if>
	    				</td>
	    			</tr>
	    			<tr>
	    				<th>
	    					<span class="red">*</span> 审批意见：
	    				</th>
	    				<th></th>
	    			</tr>
	    			<tr>
	    				<td colspan="2">
	    					<c:if test="${log.status=='W'}">
	    						<textarea name="log.content" class="textarea">${log.content}</textarea>
	    					</c:if>
	    					<c:if test="${log.status!='W'}">
	    						${log.content}
	    					</c:if>
	    				</td>
	    			</tr>
	    		</table>
	    		<div class="btnbox">
	    			<c:if test="${log.status=='W'}">
	    			<input name="s" type="submit" value="提交" class="btn_1" id="submit" />
					&nbsp;
	    			</c:if>
					<input name="s" type="button" value="返回" class="btn_2" onclick="go_back()" />
	    		</div>
    		</form>
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
