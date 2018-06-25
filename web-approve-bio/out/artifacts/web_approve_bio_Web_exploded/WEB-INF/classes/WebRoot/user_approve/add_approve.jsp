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
    <div class="nav">您当前的位置：<a href="system/index.htm">管理首页</a> > <a href="userapprove/add_list.htm?pu.pageNum=1">流程列表</a> > 发起审批 </div>
    <div class="m_m tab">
    	<div class="submenu">
    		<div class="r">
    			<strong><span class="red">*</span> 请输入审批详细资料</strong>
    		</div>
    		<div><a href="javscript:void(0)" class="h">发起审批</a></div>
    		<div class="clear"></div>
    	</div>
    	<div class="m_main">
    		<form action="userapprove/add.htm" method="post" onsubmit="return check_submit()">
    			<s:token />
	    		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tb_2">
	    			<tr>
	    				<th><span class="red"></span> 流程：</th>
	    				<td>
	    					<input type="hidden" name="userApprove.approve.id" value="${approve.id}"  />
	    					${approve.name}
	    				</td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 请输入审批标题：</th>
	    				<td><input type="text" name="userApprove.title" class="inp1"  maxlength="80" datatype="Require" value=""  /></td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 请输入详细内容：</th>
	    				<th></th>
	    			</tr>
	    			<tr>
	    				<td colspan="2">
	    					<textarea name="userApprove.content" class="textarea"></textarea>
	    				</td>
	    			</tr>
	    		</table>
	    		<div class="btnbox">
	    			<input name="s" type="submit" value="提交" class="btn_1" id="submit" />
					&nbsp;
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
