package com.approve.struts.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.RandomAccessFile;

public class FileUtil {
	/**
	 * 创建文件
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static boolean createFile(File fileName) throws Exception{
		boolean flag = false;
		try{
			if(!fileName.exists()){
				fileName.createNewFile();
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 读取文件内容
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static String readFile(File fileName) throws Exception{
		String result = "";
		FileReader fileReader = null;
		BufferedReader br = null;
		try{
			fileReader = new FileReader(fileName);
			br = new BufferedReader(fileReader);
			String read = null;
			while((read=br.readLine())!=null){
				result+=read+"\r\n";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(br != null){
				br.close();
			}
			if(fileReader != null){
				fileReader.close();
			}
		}
		return result;
	}
	
	public static boolean writeFile(String content,File fileName) throws Exception{
		RandomAccessFile mm = null;
		boolean flag = false;
		FileOutputStream o = null;
		try{
			o = new FileOutputStream(fileName);
			o.write(content.getBytes("GBK"));
			o.close();
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(mm != null){
				mm.close();
			}
		}
		return flag;
	}
	
	
}
