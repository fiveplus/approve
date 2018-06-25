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
	
	
	<script type="text/javascript" src="js/ajaxfileupload.js" ></script> 
	
	
  </head>
  
<body>
<jsp:include page="../common/top.jsp" />
<div class="main">
  <jsp:include page="../common/menu.jsp" />
  <div class="main-right r">
    <div class="nav">您当前的位置：<a href="system/setting.htm">网站设置</a> > 登录背景 </div>
    <div class="m_m tab">
    	<div class="submenu">
    		<div class="r">
    			<strong><span class="red">*</span> 请输入网站详细资料</strong>
    		</div>
    		<div><a href="javscript:void(0)" class="h">网站设置</a></div>
    		<div class="clear"></div>
    	</div>
    	<div class="m_main">
    		<form action="" method="post">
	    		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tb_2">
	    			<tr>
	    				<th><span class="red">*</span> 登录背景：</th>
	    				<td>
	    					<a href="javascript:fileSelect()"><img width="400" id="back_img" src="images/1.jpg" /></a>
	    					<form action="" method="post" enctype="multipart/form-data" style="width:auto;" >
								<input type="file" name="file" id="file" onchange="fileSelected(this)" style="display:none;" />
							</form>
	    				</td>
	    			</tr>
	    			<tr>
	    				<th></th>
	    				<td><b>注：(1920*1200) 最佳</b></td>
	    			</tr>
	    		</table>
	    	</form>
    	</div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="../common/bottom.jsp" />
</body>
<script type="text/javascript">
	function fileSelect(){
		document.getElementById("file").click();
	}
	function fileSelected(obj){
		if(obj.value == ''){
			alert("请选择文件!");
			return;
		}
		var ext = obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
		if(ext == '.jpg'){
			$.ajaxFileUpload({
				url:'setting/upload.htm',
				type:'post',
				secureuri:false,
				fileElementId:'file',
				success:function(data,status){
					alert(data);
					window.location.reload()
				}
			});
		}else{
			alert("请选择一个jpg文件上传!");
		}
	}
</script>
</html>
