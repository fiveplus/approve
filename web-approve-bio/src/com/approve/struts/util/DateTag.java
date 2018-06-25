package com.approve.struts.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 自定义时间标签
 * @author Mr.Zhang
 *
 */
public class DateTag extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -991134678872622289L;
	
	private String value;
	
	private String format;
	
	@Override
	public int doStartTag() throws JspException {
		String vv = ""+value;
		String formatf = "yyyy-MM-dd HH:mm:ss";
		if(format!=null && !format.equals("")){
			formatf = format;
		}
		long time = Long.valueOf(vv);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		SimpleDateFormat sdf = new SimpleDateFormat(formatf);
		String s = sdf.format(c.getTime());
		try {
            pageContext.getOut().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
		return super.doStartTag();
	}
	public void setValue(String value){
		this.value = value;
	}
	public void setFormat(String format){
		this.format = format;
	}
	
}
