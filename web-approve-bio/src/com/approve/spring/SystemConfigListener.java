package com.approve.spring;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.approve.struts.util.EAResource;

public class SystemConfigListener implements ServletContextListener{
	private static String contextPath = "web-approve";
	private static Properties sysConfig = new Properties();
	
	private static ServletContext context;
	
	public void contextDestroyed(ServletContextEvent sce){
		sysConfig = null;
	}
	
	public void contextInitialized(ServletContextEvent sce){
		context = sce.getServletContext();
		// 动态取出当前WEB应用的根路径
		contextPath = context.getContextPath();
		InputStream in = context.getResourceAsStream("/WEB-INF/sql-config.properties");
		try {
			sysConfig.load(in);
			EAResource.contextPath = contextPath;
			//全站注入
			context.setAttribute("contextPath", contextPath);	
			context.setAttribute("URL_IMAGES", contextPath
					+ EAResource.URL_IMAGES);
			context.setAttribute("URL_JS", contextPath
					+ EAResource.URL_JS);
			context.setAttribute("URL_CSS", contextPath + EAResource.URL_CSS);
			
			context.setAttribute("POST", EAResource.POST);
			
			context.setAttribute("APPROVE_TYPE", EAResource.APPROVE_TYPE);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Properties getSysConfig(){
		return sysConfig;
	}
	
	public static String getSysAttribute(String key){
		String value = sysConfig.getProperty(key);
		return value;
	}
	
	public static String getContextPath(){
		return contextPath;
	}
	
	public static String getRealPath(String path){
		return context.getRealPath(path);
	}
	
	public static InputStream getResourceAsStream(String path){
		return context.getResourceAsStream(path);
	}
	
}
