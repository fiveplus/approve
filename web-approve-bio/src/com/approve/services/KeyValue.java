package com.approve.services;

import java.io.Serializable;

/**
 * hql≤Œ ˝∞Ô÷˙¿‡
 * @author Mr.Zhang
 *
 */
public class KeyValue {
	private String key;
	private Serializable value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Serializable getValue() {
		return value;
	}
	public void setValue(Serializable value) {
		this.value = value;
	}
	
	public KeyValue(String key,Serializable value){
		this.key = key;
		this.value = value;
	}
	
}
