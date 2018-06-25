package com.approve.struts.util;

import java.util.ArrayList;
import java.util.List;

import com.approve.services.KeyValue;
/**
 * 系统静态常量
 * @author Max
 *
 */
public class EAResource {
	public static String contextPath = "/web-approve2";
	public static String URL_IMAGES = "/img/";
	public static String URL_CSS = "/css/";
	public static String URL_JS = "/js/";
	public static String CURRENT_SYSTEM = "/admin";
	
	public static String image(String file){
		StringBuffer imgBf = new StringBuffer(contextPath + URL_IMAGES);
		imgBf.append(file);
		return imgBf.toString();
	}
	
	public static String js(String file){
		StringBuffer jsBf = new StringBuffer(contextPath + URL_JS);
		jsBf.append(file);
		return jsBf.toString();
	}
	
	public static String css(String file) {
		StringBuffer jsBf = new StringBuffer(contextPath + URL_CSS);
		jsBf.append(file);
		return jsBf.toString();
	}
	
	public static String curSystem(){
		return CURRENT_SYSTEM;
	}
	
	public static String boot(){
		return contextPath;
	}
	
	/**
	 * 系统所有职位
	 */
	public static List<KeyValue> POST = new ArrayList<KeyValue>(){{
		add(new KeyValue("0", "高层管理"));
		add(new KeyValue("1", "部门主任"));
		add(new KeyValue("2", "普通员工"));
	}};
	
	
	public static List<KeyValue> APPROVE_TYPE = new ArrayList<KeyValue>(){{
		add(new KeyValue("0", "流程审批1型"));
		add(new KeyValue("1", "流程审批2型"));
	}};
	
	public static final String RETURN_CODE = "return_code";
	public static final String OK_CODE = "000000";
	
	
	
}
