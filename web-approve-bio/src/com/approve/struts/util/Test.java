package com.approve.struts.util;

public class Test {
	
	
	public static void main(String[] args) {
		TThread tt = new TThread();
		tt.start();
	}
	
	
}

class TThread extends Thread{
	private int i =0;
	public void run(){
		while(true){
			try{
				i++;
				System.out.println(i);
				Thread.sleep(1000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}