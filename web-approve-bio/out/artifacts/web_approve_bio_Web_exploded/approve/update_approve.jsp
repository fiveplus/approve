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
    <div class="nav">您当前的位置：<a href="system/index.htm">管理首页</a> > <a href="approve/list.htm?pu.pageNum=1">流程管理</a> > 流程修改 </div>
    <div class="m_m tab">
    	<div class="submenu">
    		<div class="r">
    			<strong><span class="red">*</span> 请输入流程详细资料</strong>
    		</div>
    		<div><a href="javscript:void(0)" class="h">流程修改</a></div>
    		<div class="clear"></div>
    	</div>
    	<div class="m_main">
    		<form action="approve/update.htm" method="post">
    			<s:token />
	    		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tb_2">
	    			<tr>
	    				<th><span class="red">*</span> 请输入流程名称：</th>
	    				<td>
	    				<input type="hidden" name="approve.id" value="${approve.id}" />
	    				<input type="text" name="approve.name" class="inp1"  maxlength="80" datatype="Require" value="${approve.name}"  /></td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 请选择流程类型：</th>
	    				<td>
	    					<select name="approve.atype.id">
	    						<option value="">请选择</option>
	    						<c:forEach items="${types}" var="t">
	    							<c:if test="${approve.atype.id==t.id}">
	    								<option value="${t.id}" selected="selected">${t.name}</option>
	    							</c:if>
	    							<c:if test="${approve.atype.id!=t.id}">
	    								<option value="${t.id}" >${t.name}</option>
	    							</c:if>
	    						</c:forEach>
	    					</select>
	    				</td>
	    			</tr>
	    			<tr>
	    				<th><span class="red">*</span> 请选择审批类型：</th>
	    				<td>
	    					<select name="approve.approveType" onchange="selectType(this)">
	    						<c:forEach items="${APPROVE_TYPE}" var="t" >
	    							<c:if test="${approve.approveType==t.key}">
	    								<option selected="selected" value="${t.key}">${t.value}</option>
	    							</c:if>
	    							<c:if test="${approve.approveType!=t.key}">
	    								<option value="${t.key}">${t.value}</option>
	    							</c:if>
	    						</c:forEach>
	    					</select>
	    				</td>
	    			</tr>
	    			<tr>
	    				
		    			<td colspan="2" id="type1" <c:if test="${approve.approveType=='1'}">style="display:none;"</c:if> >
		    				<table width="100%"  style="border:1px solid #ccc;">
		    					<tr>
		    						<th><span class="red">*</span> 业务审批人：</th>
					    			<td>
					    				<select onchange="double_select(this.value,'approve_people')">
					    					<option value=""> 请选择</option>
					    					<c:forEach items="${depts}" var="d" >
					    						<c:if test="${approve.approvePeople.dept.id==d.id}">
					    							<option value="${d.id}" selected="selected">${d.name}</option>
					    						</c:if>
					    						<c:if test="${approve.approvePeople.dept.id!=d.id}">
					    							<option value="${d.id}">${d.name}</option>
					    						</c:if>
					    					</c:forEach>
					    				</select>
					    				<select name="approve.approvePeople.id" id="approve_people">
											<option value=""> 请选择</option>
											<c:forEach items="${approves}" var="a">
												<c:if test="${approve.approvePeople.id==a.id}">
													<option value="${a.id}" selected="selected">${a.userName}</option>
												</c:if>
												<c:if test="${approve.approvePeople.id!=a.id}">
													<option value="${a.id}" >${a.userName}</option>
												</c:if>
											</c:forEach>
										</select>
					    			</td>
		    					</tr>
		    					
		    				</table>
		    			</td>
	    			</tr>
	    			<tr>
		    			<td colspan="2" id="type2" <c:if test="${approve.approveType=='0'}">style="display:none;"</c:if> >
		    				<table width="100%" style="border:1px solid #ccc;">
		    					<tr>
		    						<th><span class="red">*</span> 业务相关审核人员：</th>
					    			<td>
					    				<div id="treeview" class="tree tree-selectable"></div>
										<br/>
										<input class="btn_1" type="button" value="人员确认" onclick="select_ok()" />
					    			</td>
		    					</tr>
		    					<tr>
		    						<th><span class="red">*</span> 人员确认：</th>
		    						<td>
		    							<input type="hidden" name="approve.controlPeople" id="controlPeople" value="${approve.controlPeople}" />
										<font style="color:red; height:25px;line-height:25px;overflow:hidden;" id="select_people">
											<c:if test="${approve.controlPeople!=null}">
												<c:forEach items="${controls}" var="c">
													${c.userName}
												</c:forEach>
											</c:if>
											<c:if test="${approve.controlPeople==null}">
												无
											</c:if>
										</font>
		    						</td>
		    					</tr>
		    					<tr>
		    						<th><span class="red">*</span> 分管领导A：</th>
		    						<td>
		    							<select name="approve.layera.id" >
								    		<option value="">不审批</option>
											<c:forEach items="${highusers}" var="h">
												<c:if test="${approve.layera.id==h.id}">
													<option value="${h.id}" selected="selected">${h.userName}</option>
												</c:if>
												<c:if test="${approve.layera.id!=h.id}">
													<option value="${h.id}" >${h.userName}</option>
												</c:if>
											</c:forEach>
										</select>
		    						</td>
		    					</tr>
		    					<tr>
		    						<th><span class="red">*</span> 分管领导B：</th>
		    						<td>
		    							<select name="approve.layerb.id" >
								    		<option value="">不审批</option>
											<c:forEach items="${highusers}" var="h">
												<c:if test="${approve.layerb.id==h.id}">
													<option value="${h.id}" selected="selected">${h.userName}</option>
												</c:if>
												<c:if test="${approve.layerb.id!=h.id}">
													<option value="${h.id}" >${h.userName}</option>
												</c:if>
											</c:forEach>
										</select>
		    						</td>
		    					</tr>
		    				</table>
		    			</td>
	    			</tr>
	    			
	    			<tr>
		    			<th><span class="red">*</span> 总经理：</th>
		    			<td>
					    	<select name="approve.manager.id" >
					    		<option value="">不审批</option>
								<c:forEach items="${highusers}" var="h">
									<c:if test="${approve.manager.id==h.id}">
										<option value="${h.id}" selected="selected">${h.userName}</option>
									</c:if>
									<c:if test="${approve.manager.id!=h.id}">
										<option value="${h.id}" >${h.userName}</option>
									</c:if>
								</c:forEach>
							</select>
		    			</td>
		    		</tr>
		    			<tr>
		    				<th><span class="red">*</span> 董事长：</th>
		    				<td>
					    		<select name="approve.chairman.id" >
					    			<option value="">不审批</option>
									<c:forEach items="${highusers}" var="h">
										<c:if test="${approve.chairman.id==h.id}">
											<option value="${h.id}" selected="selected">${h.userName}</option>
										</c:if>
										<c:if test="${approve.chairman.id!=h.id}">
											<option value="${h.id}" >${h.userName}</option>
										</c:if>
												
									</c:forEach>
								</select>
		    				</td>
		    			</tr>
		    			<tr>
		    				<th><span class="red">*</span> 经理办公会：</th>
		    				<td>
		    					<select onchange="double_select(this.value,'office')">
		    						<option value=""> 请选择</option>
		    						<c:forEach items="${depts}" var="d" >
						    			<c:if test="${approve.office.dept.id==d.id}">
						    				<option value="${d.id}" selected="selected">${d.name}</option>
						    			</c:if>
						    			<c:if test="${approve.office.dept.id!=d.id}">
						    				<option value="${d.id}" >${d.name}</option>
						    			</c:if>
					    			</c:forEach>
		    					</select>
					    		<select name="approve.office.id" id="office">
					    			<option value=""> 请选择</option>
									<c:forEach items="${offices}" var="o">
										<c:if test="${approve.office.id==o.id}">
											<option value="${o.id}" selected="selected">${o.userName}</option>
										</c:if>
										<c:if test="${approve.office.id!=o.id}">
											<option value="${o.id}" >${o.userName}</option>
										</c:if>
									</c:forEach>
								</select>
		    				</td>
		    			</tr>
		    			
		    			
		    			
	    			<tr>
		    			<th><span class="red">*</span> 业务归档人：</th>
					    <td>
					    	<select onchange="double_select(this.value,'archive_people')">
					    		<option value=""> 请选择</option>
					    		<c:forEach items="${depts}" var="d" >
					    			<c:if test="${approve.archivePeople.dept.id==d.id}">
					    				<option value="${d.id}" selected="selected">${d.name}</option>
					    			</c:if>
					    			<c:if test="${approve.archivePeople.dept.id!=d.id}">
					    				<option value="${d.id}" >${d.name}</option>
					    			</c:if>
					    		</c:forEach>
					    	</select>
					    	<select name="approve.archivePeople.id" id="archive_people">
					    		<option value="">请选择</option>
								<c:forEach items="${archives}" var="a">
									<c:if test="${approve.archivePeople.id==a.id}">
										<option value="${a.id}" selected="selected">${a.userName}</option>
									</c:if>
									<c:if test="${approve.archivePeople.id!=a.id}">
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
