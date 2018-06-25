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
    <div class="nav">您当前的位置：<a href="system/index.htm">管理首页</a> > <a href="approve/list.htm?pu.pageNum=1">流程管理</a> > 查看 </div>
    <div class="m_m tab">
    	<div class="submenu">
    		<div class="r">
    			<strong><span class="red">*</span> 请输入流程详细资料</strong>
    		</div>
    		<div><a href="javscript:void(0)" class="h">流程查看</a></div>
    		<div class="clear"></div>
    	</div>
    	<div class="m_main">
	    		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tb_2">
	    			<tr>
	    				<th><span class="red">*</span> 流程名称：</th>
	    				<td>${approve.name}</td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 流程类型：</th>
	    				<td>${approve.atype.name}</td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 审批类型：</th>
	    				<td>
	    					<c:forEach items="${APPROVE_TYPE}" var="at">
	    						<c:if test="${approve.approveType == at.key}">${at.value}</c:if>
	    					</c:forEach>
	    				</td>
	    			</tr>
	    			<c:if test="${approve.approveType=='0'}">
		    			<tr>
		    				<th><span class="red">*</span> 业务审批人：</th>
					    	<td>
					    		${approve.approvePeople.userName}
					    	</td>
		    			</tr>
		    			
	    			</c:if>
	    			<c:if test="${approve.approveType=='1'}">
		    			<tr>
		    				<th><span class="red">*</span> 业务相关审核人员：</th>
					    	<td>
					    		<c:forEach items="${controls}" var="c">
					    			${c.userName}
					    		</c:forEach>
					    	</td>
		    			</tr>
		    			<tr>
		    				<th><span class="red">*</span> 分管领导A：</th>
					    	<td>
					    		${approve.layera.userName}
					    	</td>
		    			</tr>
		    			<tr>
		    				<th><span class="red">*</span> 分管领导B：</th>
					    	<td>
					    		${approve.layerb.userName}
					    	</td>
		    			</tr>
	    			</c:if>
	    			
	    			<tr>
		    			<th><span class="red">*</span> 总经理：</th>
		    			<td>
					    	${approve.manager.userName}
		    			</td>
		    		</tr>
		    		<tr>
		    			<th><span class="red">*</span> 董事长：</th>
		    			<td>
					    	${approve.chairman.userName}
		    			</td>
		    		</tr>
		    		<tr>
		    			<th><span class="red">*</span> 经理办公会：</th>
		    			<td>
					    	${approve.office.userName}
		    			</td>
		    		</tr>
	    			<tr>
		    			<th><span class="red">*</span> 业务归档人：</th>
					    <td>
					    	${approve.archivePeople.userName}
					    </td>
		    		</tr>
	    			
	    		</table>
	    		<div class="btnbox">
					<input name="s" type="button" value="返回" class="btn_2" onclick="go_back()" />
	    		</div>
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
<script type="text/javascript" src="js/approve.js"></script>

			

</body>
</html>
