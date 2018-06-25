package com.approve.struts.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;

public class MacUtils {
	//获取当前操作系统名称，return 操作系统名称 如：windows xp,linux等
	public static String getOSName(){
		return System.getProperty("os.name").toLowerCase();
	}
	
	/**
	 * unix mac地址
	 * @return
	 */
	public static String getUnixMACAddress(){
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try{
			//linux下的命令，取本地网卡
			process = Runtime.getRuntime().exec("ifconfig eth0");
			// 显示信息中包含有mac地址信息  
            bufferedReader = new BufferedReader(new InputStreamReader(  
                    process.getInputStream()));  
            String line = null;  
            int index = -1;  
            while ((line = bufferedReader.readLine()) != null) {  
                // 寻找标示字符串[hwaddr]  
                index = line.toLowerCase().indexOf("hwaddr");  
                if (index >= 0) {// 找到了  
                    // 取出mac地址并去除2边空格  
                    mac = line.substring(index + "hwaddr".length() + 1).trim();  
                    break;  
                }  
            }  
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {  
                if (bufferedReader != null) {  
                    bufferedReader.close();  
                }  
            } catch (IOException e1) {  
                e1.printStackTrace();  
            }  
            bufferedReader = null;  
            process = null; 
		}
		return mac;
	}
	
	public static String getWindowsMACAddress(){
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try{
			//使用命令查询mac信息
			 process = Runtime.getRuntime().exec("ipconfig /all");  
	            bufferedReader = new BufferedReader(new InputStreamReader(  
	                    process.getInputStream()));  
	            String line = null;  
	            int index = -1;  
	            while ((line = bufferedReader.readLine()) != null) {  
	                //System.out.println(line);  
	                // 寻找标示字符串[physical  
	                index = line.toLowerCase().indexOf("physical address");  
	                  
	                if (index >= 0) {// 找到了  
	                    index = line.indexOf(":");// 寻找":"的位置  
	                    if (index >= 0) {  
	                        System.out.println(mac);  
	                        // 取出mac地址并去除2边空格  
	                        mac = line.substring(index + 1).trim();  
	                    }  
	                    break;  
	                }
	                
	                index = line.indexOf("物理地址");
	                if (index >= 0) {// 找到了  
	                    index = line.indexOf(":");// 寻找":"的位置  
	                    if (index >= 0) {  
	                        System.out.println(mac);  
	                        // 取出mac地址并去除2边空格  
	                        mac = line.substring(index + 1).trim();  
	                    }  
	                    break;  
	                }
	            }  
		}catch(Exception e){
			e.printStackTrace();
		}finally {  
            try {  
                if (bufferedReader != null) {  
                    bufferedReader.close();  
                }  
            } catch (IOException e1) {  
                e1.printStackTrace();  
            }  
            bufferedReader = null;  
            process = null;  
        }  
  
        return mac;  
	}
	
	/** 
     * windows 7 专用 获取MAC地址 
     *  
     * @return 
     * @throws Exception 
     */  
    public static String getMACAddress() throws Exception {  
          
        // 获取本地IP对象  
        InetAddress ia = InetAddress.getLocalHost();  
        // 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。  
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();  
  
        // 下面代码是把mac地址拼装成String  
        StringBuffer sb = new StringBuffer();  
  
        for (int i = 0; i < mac.length; i++) {  
            if (i != 0) {  
                sb.append("-");  
            }  
            // mac[i] & 0xFF 是为了把byte转化为正整数  
            String s = Integer.toHexString(mac[i] & 0xFF);  
            sb.append(s.length() == 1 ? 0 + s : s);  
        }  
  
        // 把字符串所有小写字母改为大写成为正规的mac地址并返回  
        return sb.toString().toUpperCase();  
    }  
    
    public static void main(String[] argc) throws Exception {  
        String os = getOSName();  
        System.out.println(os);  
        if (os.equals("windows 7")) {  
            String mac = getMACAddress();  
            System.out.println(mac);  
        } else if (os.startsWith("windows")) {  
            // 本地是windows  
            String mac = getWindowsMACAddress();  
            System.out.println(mac);  
        } else {  
            // 本地是非windows系统 一般就是unix  
            String mac = getUnixMACAddress();  
            System.out.println(mac);  
        }  
    }  
    
    public static String getMAC() throws Exception{
    	String mac = "";
    	String os = getOSName();   
        if (os.equals("windows 7")) {  
            mac = getMACAddress();  
        } else if (os.startsWith("windows")) {  
            // 本地是windows  
            mac = getWindowsMACAddress();  
        } else {  
            // 本地是非windows系统 一般就是unix  
            mac = getUnixMACAddress();  
        }  
        return mac;
    }
    
    
	
}
