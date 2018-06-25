package com.approve.struts.util.jpush;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.approve.persistence.entity.SysUser;

/**
 * 消息推送帮助类
 * @author zhangshenwu
 *
 */
public class JPushUtil extends Thread{
	private MessagePush jpush;
	private List<SysUser> users;
	public JPushUtil(String title,String content,List<SysUser> users){
		jpush = new MessagePush(title, content);
		this.users = users;
	}
	
	public JPushUtil(String content){
		jpush = new MessagePush(content);
		this.users = users;
	}
	
	/**
	 * 向指定用户发送消息推送
	 * @param users  需要推送的用户列表
	 * @return 消息ID
	 */
	public long sendPush(List<SysUser> users){
		long msgId = 0;
		Set<String> alias = new HashSet<String>();
		for(SysUser user:users){
			alias.add(user.getLoginName().toLowerCase());
		}
		if(users !=null && users.size() > 0){
			msgId = jpush.sendPushAlias(alias);
		}
		return msgId;
	}
	
	public long sendPushTag(String tag){
		return jpush.sendPushTag(tag);
	}
	
	public long sendPushAll(){
		return jpush.sendPushAll();
	}
	
	/**
	 * 推送测试
	 * @param args
	 */
	public static void main(String[] args) {
		Set<String> alias = new HashSet<String>();
		alias.add("hsg1046");
		new MessagePush("Web approve Test Message 4.").sendPushAlias(alias);
	}
	

	public void run() {
		sendPush(users);
	}


}
