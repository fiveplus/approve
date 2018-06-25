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
    <div class="nav">您当前的位置：<a href="system/index.htm">管理首页</a> > <a href="approve/list.htm?pu.apgeNum=1&">流程管理</a> > 自由审批</div>
    <div class="m_m tab">
    	<div class="submenu">
    		<div class="r">
    			<strong><span class="red">*</span> 请输入审批详细资料</strong>
    		</div>
    		<div><a href="javscript:void(0)" class="h">审批新增</a></div>
    		<div class="clear"></div>
    	</div>
    	<div class="m_main">
    		<form action="userapprove/add.htm" method="post" onsubmit="return check_user_approve()">
    			<s:token />
	    		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tb_2">
	    			<tr>
	    				<th><span class="red">*</span> 请输入审批标题：</th>
	    				<td><input type="text" name="userApprove.title" class="inp1"  maxlength="80" datatype="Require" value=""  /></td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 请选择审批人：</th>
	    				<td>
	    					<div id="treeview" class="tree tree-selectable"></div>
							<br/>
							<input class="btn_1" type="button" value="人员确认" onclick="select_ok()" />
	    				</td>
	    			</tr>
	    			<tr>
		    			<th><span class="red">*</span> 人员确认：</th>
		    			<td>
		    				<input type="hidden" name="userApprove.remark" id="controlPeople" value="" />
							<font style="color:red; height:25px;line-height:25px;overflow:hidden;" id="select_people">无</font>
		    			</td>
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
	    			<input name="s" type="submit" value="提交" class="btn_1" />
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
<script type="text/javascript" src="js/approve.js"></script>
			

</body>
</html>
