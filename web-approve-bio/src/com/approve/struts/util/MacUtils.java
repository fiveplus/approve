package com.approve.struts.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;

public class MacUtils {
	//��ȡ��ǰ����ϵͳ���ƣ�return ����ϵͳ���� �磺windows xp,linux��
	public static String getOSName(){
		return System.getProperty("os.name").toLowerCase();
	}
	
	/**
	 * unix mac��ַ
	 * @return
	 */
	public static String getUnixMACAddress(){
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try{
			//linux�µ����ȡ��������
			process = Runtime.getRuntime().exec("ifconfig eth0");
			// ��ʾ��Ϣ�а�����mac��ַ��Ϣ  
            bufferedReader = new BufferedReader(new InputStreamReader(  
                    process.getInputStream()));  
            String line = null;  
            int index = -1;  
            while ((line = bufferedReader.readLine()) != null) {  
                // Ѱ�ұ�ʾ�ַ���[hwaddr]  
                index = line.toLowerCase().indexOf("hwaddr");  
                if (index >= 0) {// �ҵ���  
                    // ȡ��mac��ַ��ȥ��2�߿ո�  
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
			//ʹ�������ѯmac��Ϣ
			 process = Runtime.getRuntime().exec("ipconfig /all");  
	            bufferedReader = new BufferedReader(new InputStreamReader(  
	                    process.getInputStream()));  
	            String line = null;  
	            int index = -1;  
	            while ((line = bufferedReader.readLine()) != null) {  
	                //System.out.println(line);  
	                // Ѱ�ұ�ʾ�ַ���[physical  
	                index = line.toLowerCase().indexOf("physical address");  
	                  
	                if (index >= 0) {// �ҵ���  
	                    index = line.indexOf(":");// Ѱ��":"��λ��  
	                    if (index >= 0) {  
	                        System.out.println(mac);  
	                        // ȡ��mac��ַ��ȥ��2�߿ո�  
	                        mac = line.substring(index + 1).trim();  
	                    }  
	                    break;  
	                }
	                
	                index = line.indexOf("������ַ");
	                if (index >= 0) {// �ҵ���  
	                    index = line.indexOf(":");// Ѱ��":"��λ��  
	                    if (index >= 0) {  
	                        System.out.println(mac);  
	                        // ȡ��mac��ַ��ȥ��2�߿ո�  
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
     * windows 7 ר�� ��ȡMAC��ַ 
     *  
     * @return 
     * @throws Exception 
     */  
    public static String getMACAddress() throws Exception {  
          
        // ��ȡ����IP����  
        InetAddress ia = InetAddress.getLocalHost();  
        // �������ӿڶ��󣨼������������õ�mac��ַ��mac��ַ������һ��byte�����С�  
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();  
  
        // ��������ǰ�mac��ַƴװ��String  
        StringBuffer sb = new StringBuffer();  
  
        for (int i = 0; i < mac.length; i++) {  
            if (i != 0) {  
                sb.append("-");  
            }  
            // mac[i] & 0xFF ��Ϊ�˰�byteת��Ϊ������  
            String s = Integer.toHexString(mac[i] & 0xFF);  
            sb.append(s.length() == 1 ? 0 + s : s);  
        }  
  
        // ���ַ�������Сд��ĸ��Ϊ��д��Ϊ�����mac��ַ������  
        return sb.toString().toUpperCase();  
    }  
    
    public static void main(String[] argc) throws Exception {  
        String os = getOSName();  
        System.out.println(os);  
        if (os.equals("windows 7")) {  
            String mac = getMACAddress();  
            System.out.println(mac);  
        } else if (os.startsWith("windows")) {  
            // ������windows  
            String mac = getWindowsMACAddress();  
            System.out.println(mac);  
        } else {  
            // �����Ƿ�windowsϵͳ һ�����unix  
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
            // ������windows  
            mac = getWindowsMACAddress();  
        } else {  
            // �����Ƿ�windowsϵͳ һ�����unix  
            mac = getUnixMACAddress();  
        }  
        return mac;
    }
    
    
	
}