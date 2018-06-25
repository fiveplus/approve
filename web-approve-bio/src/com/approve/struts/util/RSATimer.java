package com.approve.struts.util;

import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.approve.persistence.entity.Department;
import com.approve.persistence.entity.SysUser;


public class RSATimer {
	private static String publicKey;
    private static String privateKey;
 
    static {
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            publicKey = RSAUtils.getPublicKey(keyMap);
            privateKey = RSAUtils.getPrivateKey(keyMap);
           // System.err.println("��Կ: \n\r" + publicKey);
           // System.err.println("˽Կ�� \n\r" + privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public static void main(String[] args) throws Exception {
        //test(); 
        //testSign();
    	byte[] key = getKey(System.currentTimeMillis()+"");
    	String value = Base64Utils.encode(key);
    	System.out.println(value);
    	String time = getTime(Base64Utils.decode(value));
    	System.out.println(time);
    }
 
    static void test() throws Exception {
        System.err.println("��Կ���ܡ���˽Կ����");
        String source = "����һ��û���κ���������֣��㿴���˵���û����������";
        System.out.println("\r����ǰ���֣�\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
        System.out.println("���ܺ����֣�\r\n" + new String(encodedData));
        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
        String target = new String(decodedData);
        System.out.println("���ܺ�����: \r\n" + target);
    }
    
    public static byte[] getKey(String value) throws Exception{
    	byte[] data = value.getBytes();
    	byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
    	return encodedData;
    }
    
    public static String getTime(byte[] data) throws Exception{
    	byte[] encodeData = RSAUtils.decryptByPrivateKey(data, privateKey);
    	return new String(encodeData);
    }
    
    

    static void testSign() throws Exception {
        System.err.println("˽Կ���ܡ�����Կ����");
        String source = "����һ�в���RSA����ǩ��������������";
        System.out.println("ԭ���֣�\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
        System.out.println("���ܺ�\r\n" + new String(encodedData));
        byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
        String target = new String(decodedData);
        System.out.println("���ܺ�: \r\n" + target);
        System.err.println("˽Կǩ��������Կ��֤ǩ��");
        String sign = RSAUtils.sign(encodedData, privateKey);
        System.err.println("ǩ��:\r" + sign);
        boolean status = RSAUtils.verify(encodedData, publicKey, sign);
        System.err.println("��֤���:\r" + status);
    }
}