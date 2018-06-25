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
    <div class="nav">您当前的位置：<a href="system/setting.htm">网站设置</a> > 自由审批设置 </div>
    <div class="m_m tab">
    	<div class="submenu">
    		<div class="r">
    			<strong><span class="red">*</span> 请选择能自由审批的员工</strong>
    		</div>
    		<div><a href="javscript:void(0)" class="h">自由审批设置</a></div>
    		<div class="clear"></div>
    	</div>
    	<div class="m_main">
    		<form action="setting/freeApprove.htm" method="post">
    			<s:token />
	    		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tb_2">
	    			<tr>
	    				<th><span class="red">*</span> 自由审批设置：</th>
	    				<td>
	    					<div id="treeview" class="tree tree-selectable"></div>
							<br/>
							<input class="btn_1" type="button" value="人员确认" onclick="select_ok()" />
	    				</td>
	    			</tr>
	    			<tr>
		    			<th><span class="red">*</span> 人员确认：</th>
		    			<td>
		    				<input type="hidden" name="ids" id="controlPeople" value="${ids}" />
							<font style="color:red; height:25px;line-height:25px;overflow:hidden;" id="select_people">
								<c:if test="${ids==null || ids==''}">
									无
								</c:if>
								<c:if test="${ids!=null && ids != ''}">
									<c:forEach items="${list}" var="l">
										${l.user.userName}
									</c:forEach>
								</c:if>
							</font>
		    			</td>
		    		</tr>
		    		<tr>
		    			<th><span class="red">*</span>审批副总：</th>
		    			<td>
		    				<select name="managerid">
		    					<option value="">请选择</option>
		    					<c:forEach items="${highusers}" var="h">
		    						<c:if test="${managerid==h.id}">
										<option value="${h.id}" selected="selected">${h.userName}</option>
									</c:if>
									<c:if test="${managerid!=h.id}">
										<option value="${h.id}" >${h.userName}</option>
									</c:if>
		    					</c:forEach>
		    				</select>
		    			</td>
		    		</tr>
		    		<tr>
		    			<th><span class="red">*</span>归档人：</th>
		    			<td>
		    				<select onchange="double_select(this.value,'archiveid')">
		    					<option value="">请选择</option>
		    					<c:forEach items="${depts}" var="d">
		    						<c:if test="${deptid==d.id}">
						    			<option value="${d.id}" selected="selected">${d.name}</option>
						    		</c:if>
						    		<c:if test="${deptid!=d.id}">
						    			<option value="${d.id}" >${d.name}</option>
						    		</c:if>
		    					</c:forEach>
		    				</select>
		    				<select name="archiveid" id="archiveid">
		    					<option value="">请选择</option>
		    					<c:forEach items="${archives}" var="a">
									<c:if test="${archiveid==a.id}">
										<option value="${a.id}" selected="selected">${a.userName}</option>
									</c:if>
									<c:if test="${archiveid!=a.id}">
										<option value="${a.id}" >${a.userName}</option>
									</c:if>
								</c:forEach>
		    				</select>
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
