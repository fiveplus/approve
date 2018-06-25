package com.approve.struts.system;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import com.approve.persistence.entity.Setting;
import com.approve.persistence.entity.SysFreeApprove;
import com.approve.persistence.entity.SysUser;
import com.approve.persistence.entity.UserApprove;
import com.approve.services.KeyValue;
import com.approve.struts.BaseAction;
import com.approve.struts.util.AuthorizationUtils;
import com.approve.struts.util.Base64Utils;
import com.approve.struts.util.EAResource;
import com.approve.struts.util.JsonUtil;
import com.approve.struts.util.KeyStore;

public class IndexAction extends BaseAction{
	
	public static final String INDEX = "index";


	private String loginName;
	private String password;
	private String error;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public String login() throws Exception{
		
		//存储到Application中
		Setting set = settingServ.getSetting();
		request.getSession().getServletContext().setAttribute("set", set);
		
		if(loginName == null && password == null){
			error = "Please Input Your Information.";
			return LOGIN;
		}
		
		SysUser user = sysUserServ.getSysUserByLoginNameAndPassword(loginName, password);
		
		if(user == null){
			error = "用户名/密码错误!";
			Cookie myCookie[]=request.getCookies();
			for(Cookie co:myCookie){
				if("loginName".equals(co.getName())){
					co = null;
					continue;
				}
				else if("password".equals(co.getName())){
					co = null;
					continue;
				}
			}
			return LOGIN;
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			 
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String loginTime = sdf.format(date);
			request.getSession().setAttribute("loginTime", loginTime);
			 
			return INDEX;
		}
	}
	
	public String logout() throws Exception{
		request.getSession().invalidate();
		return SUCCESS;
	}
	
	public String index() throws Exception{
		//TODO 总审批数
		//int count = userApproveServ.getUserApproveCount();
		//TODO 当前等待审批数
		//int wcount= userApproveServ.getUserApproveCountByStatus("W");
		//request.setAttribute("count", count);
		//request.setAttribute("wcount", wcount);
		
		List<KeyValue> ands = new ArrayList<KeyValue>();
		ands.add(new KeyValue("user.id", getCurrentUser().getId()));
		ands.add(new KeyValue("dstatus", "Y"));
		//TODO 总流程数
		int count = userApproveServ.getCount(UserApprove.class, ands, null);
		//TODO 当前用户等待审批数
		int wcount = userApproveServ.getUserApproveCountByUserIdAndStatus(getCurrentUser().getId(), "W");
		request.setAttribute("count", count);
		request.setAttribute("wcount", wcount);
		
		
		SysFreeApprove fa =  sysFreeApproveServ.getSysFreeApproveByUserId(getCurrentUser().getId());

		request.getSession().setAttribute("fa", fa);
		
		return SUCCESS;
	}
	
	public String setting() throws Exception{
		return SUCCESS;
	}
	
	public String reg() throws Exception{
		//获取key
		String key_path = request.getServletContext().getRealPath("/WEB-INF/webapp.keystore");
		File file = new File(key_path);
		try{
			KeyStore keystore = new KeyStore(file);
			String mac = keystore.getMac();
			String machineCode = keystore.getMachineCode();
			//String key = keystore.getKey();
			String code = keystore.getCode();
			if(mac.equals("")){
				mac = AuthorizationUtils.getMac();
				keystore.setMac(mac);
			}
			if(machineCode.equals("")){
				machineCode = AuthorizationUtils.getMachineCode();
				keystore.setMachineCode(machineCode);
			}
			if(code.equals("")){
				//未注册
				
			}else{
				//验证验证码是否正确
				if(code.equals(AuthorizationUtils.auth(machineCode))){
					request.setAttribute("code", code);
				}else{
					request.setAttribute("code", "");
				}
			}
			
			request.setAttribute("mac", mac);
			request.setAttribute("machineCode", machineCode);
			
		}catch(Exception e){
			System.err.println("The webapp.keystore error:"+e.toString());
			//response.sendRedirect(path+"/system/login.htm");
		}
		
		return SUCCESS;
	}
	
	private String machineCode;
	private String code;
	private String mac;
	
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String regist() throws Exception{
		//接收机器码－验证验证码是否正确，正确保存，不正确返回
		if(AuthorizationUtils.auth(machineCode).equals(code)&&code !=null&&!code.equals("")){
			//保存code
			String key_path = request.getServletContext().getRealPath("/WEB-INF/webapp.keystore");
			File file = new File(key_path);
			try{
				KeyStore keystore = new KeyStore(file);
				keystore.setCode(code);
			}catch(Exception e){
				e.printStackTrace();
			}
			return LOGIN;
			
		}else{
			String msg = "x 很抱歉，序列号输入错误!";
			request.setAttribute("msg", msg);
			request.setAttribute("machineCode", machineCode);
			request.setAttribute("mac", mac);
			request.setAttribute("code", "");
			return SUCCESS;
		}
	}
	
	
	public void tel_login() throws Exception{
		String returnCode = "000000";
		
		//TODO 设置跨域访问
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods","POST");
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		SysUser user = null;
		
		if(loginName == null && password == null){
			returnCode = "000001";
		}else{
			user = sysUserServ.getSysUserByLoginNameAndPassword(loginName, password);
			if(user == null){
				returnCode = "000002";
			}else{
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				returnCode = "000000";
			}
		}
		
		String sessionid = request.getSession().getId();
		
		Map<String,Object> returnMap = new HashMap<String, Object>();
		returnMap.put(EAResource.RETURN_CODE, returnCode);
		returnMap.put("sessionid", sessionid);
		
		out.print(JsonUtil.ObjectToString(returnMap));
		out.flush();
		out.close();
	}
	
	
	public void tel_user() throws Exception{
		//TODO 设置跨域访问
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods","POST");
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");	
	
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Map<String,Object> returnMap = new HashMap<String, Object>();
		returnMap.put("user", getCurrentUser());
		
		out.print(JsonUtil.ObjectToString(returnMap));
		out.flush();
		out.close();
	
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	
}
