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
    <div class="nav">您当前的位置：<a href="system/index.htm">管理首页</a> > 流程管理</div>
    <div class="m_m tab">
    	<div class="tj"><a href="approve/addinit.htm">添加流程</a></div>
    	<div class="submenu">
    		<div><a href="#" class="h">流程列表</a></div>
    		<div class="clear"></div>
    	</div>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb_1">
    		<tr>
    			<td colspan="7" >
    				<form action="approve/list.htm?pu.pageNum=1" method="post" id="search">
    					请输入流程名称：<input type="text" name="selectValue" value="${selectValue}"  /> 
    					<input type="submit" value="查询" class="btn_1" />
    				</form>
    			</td>
    		</tr>
    		<tr>
    			<th><input type="checkbox" name="checkbox" onclick="checkAll(this)" /></th>
    			<th>流程名称</th>
    			<th>流程类型</th>
    			<th>审批类型</th>
    			<th>创建时间</th>
    			<th>状态</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach items="${approves}" var="a">
    			<tr align="center">
	    			<td><input type="checkbox" name="checkbox" /></td>
    				<td align="left"><i><a href="approve/select.htm?id=${a.id}">${a.name}</a></i></td>
    				<td>
    					${a.atype.name}
    				</td>
    				<td>
	    				<c:if test="${a.approveType == '0'}">
							流程审批1型
						</c:if>
						<c:if test="${a.approveType == '1'}">
							流程审批2型
						</c:if>
    				</td>
    				<td><date:date value="${a.createTime}" /></td>
    				<td><span><img src="img/toolbar_ok.gif" alt="" width="13" height="13"/></span></td>
    				<td>  <a href="approve/select.htm?id=${a.id}">查看</a>   |   <a href="approve/updateInit.htm?id=${a.id}">修改</a>  | <a href="javascript:del('approve/delete.htm','${a.id}')">删除</a> </td>
    			</tr>
    		</c:forEach>
    	</table>
    	<div class="qx">
    		<div class="r">共 ${pu.count} 条信息</div>
    		<a class="red" href="javascript:void(0)">&nbsp;</a>
    	</div>
    	<div class="page" align="left">
    		<a href="javascript:to_page('approve/list.htm?pu.pageNum=1','search')">首页</a>
    		<a href="javascript:to_page('approve/list.htm?pu.pageNum=${pu.pageNum-1}','search')">上一页</a>
    		<c:forEach items="${pu.pageList}" var="p">
    			<c:if test="${p == pu.pageNum}">
    				<span class="pagenum">
		    			<span>${pu.pageNum}</span>
		    		</span>
    			</c:if>
    			<c:if test="${p != pu.pageNum}">
    				<a href="javascript:to_page('approve/list.htm?pu.pageNum=${p}','search')">&nbsp; ${p} &nbsp;</a>
    			</c:if>
    		</c:forEach>
    		<a href="javascript:to_page('approve/list.htm?pu.pageNum=${pu.pageNum+1}','search')">下一页</a>
    		<a href="javascript:to_page('approve/list.htm?pu.pageNum=${pu.pageCount}','search')">尾页</a>
    	</div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="../common/bottom.jsp" />
</body>
</html>
