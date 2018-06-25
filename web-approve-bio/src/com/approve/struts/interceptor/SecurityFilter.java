package com.approve.struts.interceptor;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.approve.persistence.entity.SysUser;
import com.approve.struts.util.AuthorizationUtils;
import com.approve.struts.util.Base64Utils;
import com.approve.struts.util.DESCoder;
import com.approve.struts.util.FileUtil;
import com.approve.struts.util.KeyStore;
import com.approve.struts.util.RSATimer;
import com.approve.struts.util.RSAUtils;
import com.approve.struts.util.StringUtil;

public class SecurityFilter implements Filter{
	private String[] files;
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
	
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		HttpSession session = request.getSession();
		
		//获取请求后缀
		String uri = request.getRequestURI();
		String request_uri = uri.substring(uri.lastIndexOf("/")+1);
		
		String path = request.getContextPath();
		
		//获取key
		String key_path = req.getServletContext().getRealPath("/WEB-INF/webapp.keystore");
		File file = new File(key_path);
		try{
			KeyStore keystore = new KeyStore(file);
			String mac = keystore.getMac();
			String machineCode = keystore.getMachineCode();
			String key = keystore.getKey();
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
				String value = new String(Base64Utils.decode(key));
				long time = Long.parseLong(value);
				long now = System.currentTimeMillis();
				//试用60天
				long t = 24*3600*1000*60l;
				if(now - time > t && now-time > 0){
					//时间超出
					if(request_uri.equals("login.htm")||request_uri.equals("login.html")||request_uri.equals("")
							||request_uri.equals("tel.login.htm")||request_uri.equals("reg.htm")
							||request_uri.equals("regist.htm")){
						chain.doFilter(request, response);
						return;
					}else{
						for(int i = 0;i<files.length;i++){
							if(request_uri.endsWith(files[i])){
								chain.doFilter(request, response);
								return;
							}
						}
						response.sendRedirect(path+"/system/login.htm");
					}
					
				}else{
					long temp = t - (now - time);
					long day = temp / (24*3600*1000);
					request.setAttribute("msg", "尊敬的用户您好，剩余时间："+day+"天");
				}
			}else{
				//验证验证码是否正确
				if(!code.equals(AuthorizationUtils.auth(machineCode))){
					//验证码错误
					response.sendRedirect(path+"/system/reg.htm");
				}else{
					request.setAttribute("msg","");
				}
			}
		}catch(Exception e){
			System.err.println("The webapp.keystore error:"+e.toString());
			e.printStackTrace();
			//response.sendRedirect(path+"/system/login.htm");
			response.sendRedirect(path+"/system/reg.htm");
			return;
		}
		
		if(request_uri.equals("login.htm")||request_uri.equals("login.html")||request_uri.equals("")
				||request_uri.equals("tel.login.htm")||request_uri.equals("reg.htm")
				||request_uri.equals("regist.htm")){
			chain.doFilter(request, response);
			return;
		}
		for(int i = 0;i<files.length;i++){
			if(request_uri.endsWith(files[i])){
				chain.doFilter(request, response);
				return;
			}
		}
		SysUser user = (SysUser)session.getAttribute("user");
		if(user != null){
			chain.doFilter(request, response);
		}else{
			response.sendRedirect(path+"/system/login.htm");
		}
	}
	
	public void destroy() {
		
	}

	public void init(FilterConfig config) throws ServletException {
		files = config.getInitParameter("files").split(",");
	}
	
	
}
