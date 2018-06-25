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
           // System.err.println("公钥: \n\r" + publicKey);
           // System.err.println("私钥： \n\r" + privateKey);
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
        System.err.println("公钥加密——私钥解密");
        String source = "这是一行没有任何意义的文字，你看完了等于没看，不是吗？";
        System.out.println("\r加密前文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
        System.out.println("加密后文字：\r\n" + new String(encodedData));
        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
        String target = new String(decodedData);
        System.out.println("解密后文字: \r\n" + target);
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
        System.err.println("私钥加密——公钥解密");
        String source = "这是一行测试RSA数字签名的无意义文字";
        System.out.println("原文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
        System.out.println("加密后：\r\n" + new String(encodedData));
        byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
        String target = new String(decodedData);
        System.out.println("解密后: \r\n" + target);
        System.err.println("私钥签名——公钥验证签名");
        String sign = RSAUtils.sign(encodedData, privateKey);
        System.err.println("签名:\r" + sign);
        boolean status = RSAUtils.verify(encodedData, publicKey, sign);
        System.err.println("验证结果:\r" + status);
    }
}