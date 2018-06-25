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
<jsp:include page="../common/top.jsp" />
<div class="main">
  <jsp:include page="../common/menu.jsp" />
  <div class="main-right r">
    <div class="nav">您当前的位置：<a href="system/index.htm">管理首页</a> > 我的归档</div>
    <div class="m_m tab">
    	<div class="submenu">
    		<div><a href="#" class="h">我的归档列表</a></div>
    		<div class="clear"></div>
    	</div>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb_1">
    		<tr>
    			<td colspan="8" >
    				<form action="archive/list.htm?pu.pageNum=1" method="post" id="search">
    					请输入审批标题/编号/发起人：<input type="text" name="selectValue" value="${selectValue}"  /> 
    					<input type="submit" value="查询" class="btn_1" />
    				</form>
    			</td>
    		</tr>
    		<tr>
    			<th><input type="checkbox" name="checkbox" onclick="checkAll(this)" /></th>
    			<th>编号</th>
    			<th>审批标题</th>
    			<th>流程名称</th>
    			<th>发起人</th>
    			<th>完成时间</th>
    			<th>状态</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach items="${list}" var="l">
    			<tr align="center">
	    			<td><input type="checkbox" name="checkbox" /></td>
	    			<td align="left"><i><a href="archive/archive.htm?id=${l.id}">${l.ua.approveNum}</a></i></td>
    				<td>${l.ua.title}</td>
    				<td>
    					<c:if test="${l.ua.approve == null}">
    						自由审批
    					</c:if>
    					<c:if test="${l.ua.approve != null}">
    						${l.ua.approve.name}
    					</c:if>
	    				
    				</td>
    				<td>
    					${l.ua.user.userName}
    				</td>
    				<td><date:date value="${l.createTime}" /></td>
    				<td><span>
	    				<c:if test="${l.status == 'Y'}">
	    					<img src="img/toolbar_ok.gif" alt="" width="13" height="13"/>
    					</c:if>
    					<c:if test="${l.status != 'Y'}">
    						<img src="img/toolbar_nn.gif" alt="" width="13" height="13"/>
    					</c:if>
    				</span></td>
    				<td>  <a href="archive/archive.htm?id=${l.id}">查看</a> <!--  |   <a href="#">修改</a>--> <!--  | <a href="#">删除</a>  --> </td>
    			</tr>
    		</c:forEach>
    	</table>
    	<div class="qx">
    		<div class="r">共 ${pu.count} 条信息</div>
    		<a class="red" href="javascript:void(0)">&nbsp;</a>
    	</div>
    	<div class="page" align="left">
    		<a href="javascript:to_page('archive/list.htm?pu.pageNum=1','search')">首页</a>
    		<a href="javascript:to_page('archive/list.htm?pu.pageNum=${pu.pageNum-1}','search')">上一页</a>
    		<c:forEach items="${pu.pageList}" var="p">
    			<c:if test="${p == pu.pageNum}">
    				<span class="pagenum">
		    			<span>${pu.pageNum}</span>
		    		</span>
    			</c:if>
    			<c:if test="${p != pu.pageNum}">
    				<a href="javascript:to_page('archive/list.htm?pu.pageNum=${p}','search')">&nbsp; ${p} &nbsp;</a>
    			</c:if>
    		</c:forEach>
    		<a href="javascript:to_page('archive/list.htm?pu.pageNum=${pu.pageNum+1}','search')">下一页</a>
    		<a href="javascript:to_page('archive/list.htm?pu.pageNum=${pu.pageCount}','search')">尾页</a>
    	</div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="../common/bottom.jsp" />
</body>
</html>
