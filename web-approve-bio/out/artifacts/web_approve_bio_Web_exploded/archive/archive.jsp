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
    <div class="nav">您当前的位置：<a href="system/index.htm">管理首页</a> > <a href="archive/list.htm?pu.pageNum=1">我的归档</a> > 查看 </div>
    <div class="m_m tab">
    	<div class="submenu">
    		<div class="r">
    			<strong><span class="red"></span> </strong>
    		</div>
    		<div><a href="javscript:void(0)" class="h">查看</a></div>
    		<div class="clear"></div>
    	</div>
    	<div class="m_main">
    		<form action="archive/update.htm" method="post">
    			<s:token />
    			<div id="approve">
	    		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tb_2" >
	    			<tr>
	    				<th><span class="red">*</span> 审批编号：</th>
	    				<td>
	    				<input type="hidden" name="al.id" value="${al.id}" />
	    				${al.ua.approveNum}</td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 审批标题：</th>
	    				<td>
	    					${al.ua.title}
	    				</td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 审批人：</th>
	    				<td>
	    					${al.ua.user.userName}
	    				</td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span>状态：</th>
	    				<td>
	    					<select name="al.status">
	    						<option value="N" <c:if test="${al.status=='N'}">selected="selected"</c:if>>未处理</option>
	    						<option value="Y" <c:if test="${al.status=='Y'}">selected="selected"</c:if>>已处理</option>
	    					</select>
	    				</td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 备注：</th>
	    				<th></th>
	    			</tr>
	    			<tr>
	    				<td colspan="2">
	    					<textarea name="al.remark" class="textarea">${al.remark}</textarea>
	    				</td>
	    			</tr>
	    		</table>
	    		</div>
	    		<div class="btnbox">
	    			<input type="button" onclick="showPrintDialog('${al.id}')" value="打印预览" class="btn_1" />
					&nbsp;
					<input name="s" type="submit" value="保存" class="btn_2" />
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
<script type="text/javascript" src="js/approve.js"></script>

			

</body>
</html>
