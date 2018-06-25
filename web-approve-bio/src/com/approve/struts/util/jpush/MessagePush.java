package com.approve.struts.util.jpush;

import java.util.Set;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * Jpush����˼���
 * @author zhangshenwu
 *
 */
public class MessagePush {
	
	//TODO ���������������ӵ�����Դ���
	private static final int MAX_RETRY_NUM = 3;
	//TODO ע��Ӧ�õ������룬API������
	private static final String MASTER_SECRET = "28b73f3c970b101af85dd2b1";
	//TODO ע��Ӧ�õ�APP_KEY
	private static final String APP_KEY = "e1a0c4068babde1c9b1b69e4";
	
	private JPushClient jpushClient;
	private String title;
	private String content;
	
	public MessagePush(String content){
		this.content = content;
		jpushClient = new JPushClient(MASTER_SECRET, APP_KEY,MAX_RETRY_NUM);
	}
	
	public MessagePush(String title,String content){
		this(content);
		this.title = title;
	}
	
	/**
	 * ��������������Ϣ
	 * @return ��ϢID
	 */
	public long sendPushAll(){
		PushPayload payload = buildPushObject_all_all_alert();
		long msgId = 0;
		try{
			PushResult result = jpushClient.sendPush(payload);
			msgId = result.msg_id;
		}catch(APIConnectionException e){
			System.err.println("[Connection Error]:"+ e.toString());
		}catch(APIRequestException e){
			System.out.println("HTTP Status:"+e.getStatus());
			msgId = e.getMsgId();
		}
		return msgId;
	}
	
	/**
	 * ��ָ�������Ŀͻ���������Ϣ
	 * @param alias ���б����ļ���[����ʹ�ñ��]
	 * @return ��ϢID
	 */
	public long sendPushAlias(Set<String> alias){
		//PushPayload payloadAlias = buildPushObject_android_alias_alertWithTitle(alias);
		PushPayload payloadAlias = buildPushObject_all_alias_alertWithTitle(alias);
		long msgId = 0;
		try{
			PushResult result = jpushClient.sendPush(payloadAlias);
			msgId = result.msg_id;
		}catch(APIConnectionException e){
			System.err.println("[Connection Error]:"+ e.toString());
		}catch(APIRequestException e){
			System.out.println("HTTP Status:"+e.getStatus());
			System.out.println("Error Code:"+e.getErrorCode());
			System.out.println("Error Message:"+e.getErrorMessage());
			System.out.println("Msg ID:"+e.getMsgId());
			msgId = e.getMsgId();
		}
		return msgId;
	}
	
	/**
	 * ��ָ����������Ϣ
	 * @param tag ������
	 * @return ��ϢID
	 */
	public long sendPushTag(String tag){
		PushPayload payloadtag = buildPushObject_android_tag_alertWithTitle(tag);
		long msgId = 0;
		try{
			PushResult result = jpushClient.sendPush(payloadtag);
			msgId = result.msg_id;
		}catch(APIConnectionException e){
			System.err.println("[Connection Error]:"+ e.toString());
		}catch(APIRequestException e){
			System.out.println("HTTP Status:"+e.getStatus());
			System.out.println("Error Code:"+e.getErrorCode());
			System.out.println("Error Message:"+e.getErrorMessage());
			System.out.println("Msg ID:"+e.getMsgId());
			msgId = e.getMsgId();
		}
		return msgId;
	}
	
	
	
	//TODO ���з�װ�����ֻ����Ϣ���Ͷ���(android)
	public PushPayload buildPushObject_android_alias_alertWithTitle(Set<String> alias){
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(alias)).setNotification(Notification.android(content, title, null)).build();
	}
	public PushPayload buildPushObject_android_tag_alertWithTitle(String tag){
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.tag(tag)).setNotification(Notification.android(content, title, null)).build();
	}
	public PushPayload buildPushObject_all_all_alert(){
		return PushPayload.alertAll(content);
	}
	
	//TODO ȫƽ̨��Ϣ����
	public PushPayload buildPushObject_all_alias_alertWithTitle(Set<String> alias){
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(alias))
				.setNotification(Notification.newBuilder()
						.setAlert(content)
						.addPlatformNotification(AndroidNotification.newBuilder()
								.setTitle(title).build())
						.addPlatformNotification(IosNotification.newBuilder()
								.incrBadge(1)
								//extra_key extra_value
								.addExtra(title,content).build())
								.build())
				.build();
	}
	
}
