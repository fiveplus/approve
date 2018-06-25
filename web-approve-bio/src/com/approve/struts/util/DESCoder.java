package com.approve.struts.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESCoder {
	private static final String KEY_ALGORITHM = "DES";
	private static final String DEFAULT_CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
	
	static byte[] key;
	static{
		try {
			key = initSecretKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] initSecretKey() throws Exception{
		//返回生成指定算法的密钥的对象
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		//初始化密钥生成器，让他具有确定的密钥大小
		kg.init(56);
		//生成一个密钥
		SecretKey secreKey = kg.generateKey();
		return secreKey.getEncoded();
	}
	
	private static Key toKey(byte[] key) throws Exception{
		//实例化DES密钥
		DESKeySpec dks = new DESKeySpec(key);
		//实例化密钥工厂
		SecretKeyFactory skf = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		//生成密钥
		SecretKey secretKey = skf.generateSecret(dks);
		return secretKey;
	}
	
	public static byte[] encrypt(byte[] data,Key key) throws Exception{
		return encrypt(data,key,DEFAULT_CIPHER_ALGORITHM);
	}
	public static byte[] encrypt(byte[] data,byte[] key) throws Exception{
		return encrypt(data,key,DEFAULT_CIPHER_ALGORITHM);
	}
	
	public static byte[] encrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{
		//还原密钥
		Key k = toKey(key);
		return encrypt(data,k,cipherAlgorithm);
	}
	
	public static byte[] encrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{  
		//实例化
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		//使用密钥初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, key);
		//执行操作
		return cipher.doFinal(data);
	}
	
	public static byte[] decrypt(byte[] data,byte[] key) throws Exception{
		return decrypt(data,key,DEFAULT_CIPHER_ALGORITHM);
	}
	
	public static byte[] decrypt(byte[] data,Key key) throws Exception{
		return decrypt(data,key,DEFAULT_CIPHER_ALGORITHM);
	}
	
	public static byte[] decrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{
		//还原密钥
		Key k = toKey(key);
		return decrypt(data, k,cipherAlgorithm);
	}
	
	public static byte[] decrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{
		//实例化
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		//使用密钥初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, key);
		//执行操作
		return cipher.doFinal(data);
	}
	
	private static String showByArray(byte[] data){
		if(data == null){
			return null;
		}
		StringBuilder sb = new StringBuilder("{");
		for(byte b:data){
			sb.append(b).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("}");
		return sb.toString();
	}
	
	
	public static String encrypt(String data) throws Exception{
		return Base64Utils.encode(encrypt(data.getBytes(), key));
	}
	public static String decrypt(String data) throws Exception{
		return new String(decrypt(Base64Utils.decode(data),key));
	}

	
}
