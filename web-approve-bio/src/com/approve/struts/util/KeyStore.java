package com.approve.struts.util;

import java.io.File;
/**
 * √‹‘ø∞Ô÷˙¿‡
 * @author zhangshenwu
 *
 */
public class KeyStore {
	
	private String mac;
	private String machineCode;
	private String code;
	private String key;
	
	private File file;
	
	public KeyStore(File file) throws Exception{
		
		String content = FileUtil.readFile(file);
		this.file = file;
		String[] contents = content.split("\r\n");
		mac = contents[0].substring(contents[0].indexOf('=')+1,contents[0].length());
		machineCode = contents[1].substring(contents[1].indexOf('=')+1,contents[1].length());
		code = contents[2].substring(contents[2].indexOf('=')+1,contents[2].length());
		key = contents[3].substring(contents[3].indexOf('=')+1,contents[3].length());
	
	}
	
	public String getMac(){
		return this.mac;
	}
	public String getMachineCode(){
		return this.machineCode;
	}
	public String getCode(){
		return this.code;
	}
	public String getKey(){
		return this.key;
	}
	public void setMac(String mac) throws Exception{
		String content = "mac="+mac+"\r\n"
				+"machine-code="+getMachineCode()+"\r\n"
				+"code="+getCode()+"\r\n"
				+"key="+getKey();
		FileUtil.writeFile(content, file);
	}
	public void setMachineCode(String machineCode) throws Exception{
		String content = "mac="+getMac()+"\r\n"
				+"machine-code="+machineCode+"\r\n"
				+"code="+getCode()+"\r\n"
				+"key="+getKey();
		FileUtil.writeFile(content, file);
	}
	public void setCode(String code)throws Exception{
		String content = "mac="+getMac()+"\r\n"
				+"machine-code="+getMachineCode()+"\r\n"
				+"code="+code+"\r\n"
				+"key="+getKey();
		FileUtil.writeFile(content, file);
	}
	public void setKey(String key)throws Exception{
		String content = "mac="+getMac()+"\r\n"
				+"machine-code="+getMachineCode()+"\r\n"
				+"code="+getCode()+"\r\n"
				+"key="+key;
		FileUtil.writeFile(content, file);
	}
	
}
