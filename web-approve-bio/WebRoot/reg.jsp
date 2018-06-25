<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>${set.title}</title>
 	<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="images/login.js"></script>
	<link href="css/login2.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
<h1>${set.title}<sup>2016</sup></h1>

<div class="login" style="margin-top:50px;">
    
    <div class="header">
        <div class="switch" id="switch"><a class="switch_btn_focus"  id="switch_qlogin" href="javascript:void(0);" tabindex="7">软件注册</a>
			<div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>    
  
    
    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 305px;">    

            <!--登录-->
            <div class="web_login" id="web_login">
              	
              
               <div class="login-box">
    
            
			<div class="login_form">
				<form action="system/regist.htm" name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" method="post">
				<div style="color:red;padding-bottom:10px;">${msg}</div>
                <div class="uinArea" id="uinArea">
                <label class="input-tips" for="u">MAC：</label>
                <div class="inputOuter" id="uArea">
                    <input type="text" id="u" name="mac" style="background: #eee;" class="inputstyle" readonly="readonly" value="${mac}"/>
                </div>
                </div>
                <div class="pwdArea" id="pwdArea">
               <label class="input-tips" for="p">系统码：</label> 
               <div class="inputOuter" id="pArea">
                    <input type="text" id="p" name="machineCode" style="background: #eee;"  class="inputstyle" readonly="readonly" value="${machineCode}" />
                </div>
                </div>
                 <div class="pwdArea" >
                <label class="input-tips" for="p">序列号：</label> 
               <div class="inputOuter" id="pArea">
                    <input type="text" id="p" name="code" class="inputstyle" <c:if test="${code != null && code != ''}">readonly="readonly"</c:if> value="${code}"  />
                </div>
                </div>
               
                <div style="padding-left: 55px; margin-top: 10px;">
                <input type="submit" value="确认注册" style="width:150px;" class="button_blue"/>
                </div>
              	<br />
                 <div >
  	 				<a href="system/login.htm" style="margin-left:10px;">返回登录</a>
  				 </div>

              </form>
           </div>
            </div>
               
            </div>
            <!--登录end-->
  </div>

 
</div>
<div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div>
</body></html>