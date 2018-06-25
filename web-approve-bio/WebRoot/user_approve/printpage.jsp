<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="date" uri="/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>${al.ua.approve.name}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=8" > 
    <link href="img/s.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
	body {
		font-family:华文仿宋, 仿宋, simsong;
		color:#333;
		font-size:18px;
	}
	.h1 h1 {
		color:#e00;
		margin:0;
		padding:40px 0;
		font:bolder 36px/36px 华文仿宋, 仿宋, simsong;
		transform:scale(1, 1.5);
	}
	.h1 {
		text-align:center;
		border-bottom:solid 2px #e00;
	}
	
	.pname {
		font-size:24px;
		font-weight:bold;
		text-align:center;
		padding:10px;
	}
	td {
		font-size:18px; padding:5px;
	}
	.h2 {
		padding:10px 0 10px;
		margin:20px 0 10px;
		background:#f4f4f4;
		text-align:center;
		border-top:solid 1px #ccc;
		border-bottom:solid 1px #fff;
		font-weight:bold;
		color:#e00;
	}
	.h3 {
		padding:10px 0 10px;
		margin:20px 0 10px;
		background:#fff5f5;
		font-weight:bold;
	}
	.print {
		border:solid 1px #999;
		background:#f4f4f4;
		border-width:2px 0 2px 0;
		text-align:center;
	}
	</style>
	<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
  </head>
  
  <body>
  	<table width="90%"  align="center" cellpadding="10" cellspacing="0" >
  		<tr>
  			<td class="h1"><h1>黄石市担保集团-流程审批单</h1>
  				<table width="100%" border="0" cellspacing="0" cellpadding="0">
  					<tr align="left" valign="top">
  						<td width="50%">审批编号：${al.ua.approveNum}</td>
  						<td width="50%" align="right">发起人：${al.ua.user.userName}</td>
  					</tr>
  				</table>
  			</td>
  		</tr>
  		<tr>
  			<td><div class="pname">审批项目：${al.ua.approve.name}</div>
  				<table width="100%" border="0" cellpadding="0" cellspacing="0">
  					<tr align="left" valign="top">
  						<td width="50%">审批日期：<date:date value="${al.ua.createTime}" /></td>
  						<td width="50%">事项备注：${al.ua.title}</td>
  					</tr>
  					<tr align="left" valign="top">
  						<td width="100%" colspan="2">
  							详细内容：${al.ua.content}
  						</td>
  					</tr>
  				</table>
  				<c:forEach items="${list}" var="l">
  					<c:if test="${l.toUser != null}">
  						<div class="h2">
  							[<c:forEach items="${POST}" var="p">
		    					<c:if test="${l.toUser.post==p.key}">${p.value}</c:if>
		    				</c:forEach>
		    				] ${l.toUser.userName} 意见：
		    				<c:if test="${l.status == 'W'}">
		    				等待审批
			    			</c:if>
			    			<c:if test="${l.status == 'Y'}">
			    				通过
			    			</c:if>
			    			<c:if test="${l.status == 'N'}">
			    				未通过
			    			</c:if>
  						</div>
  						<table width="100%" border="0" cellpadding="0" cellspacing="0">
  							<tr align="left" valign="top">
  								<td>审批日期：<date:date value="${l.createTime}" /></td>
  							</tr>
  							<tr align="left" valign="top">
  								<td colspan="2" class="h3">信息备注：${l.content}</td>
  							</tr>
  						</table>
  					</c:if>
  				</c:forEach>
  				<p>&nbsp;</p>
  			</td>
  		</tr>
  		<tr id="deltr">
  			<td class="print">
  				<label>
  					<input type="button" style="border: 1px solid #ccc; padding: 20px 100px;margin: 15px;background: red;color: white;font-size: 20px;" onclick="printPage()"  value="确认打印" />
  				</label>
  			</td>
  		</tr>
  		<tr>
  			<td>
  				<p>&nbsp;</p>
  			</td>
  		</tr>
    </table>
  	
  </body>
</html>
