package com.approve.services;

import java.util.List;
import java.util.Map;

import com.approve.struts.util.PageUtil;

/**
 * »ù´¡DAO½Ó¿Ú
 * @author Mr.Zhang
 *
 */
public interface BaseInterface {
	public List getList(PageUtil pu, List<KeyValue> ands,List<KeyValue> likes,List<KeyValue> nos);
	public List getList(Class clazz, List<KeyValue> ands,List<KeyValue> likes,List<KeyValue> nos,Map<String,Long> betweens);
	public List getList(PageUtil pu, List<KeyValue> ands,List<KeyValue> likes,List<KeyValue> nos,Map<String,Long> betweens);
	public int getCount(Class clazz,List<KeyValue> ands,List<KeyValue> likes,List<KeyValue> nos,Map<String,Long> betweens);
	public int getCount(Class clazz,List<KeyValue> ands,List<KeyValue> likes,List<KeyValue> nos);
	public List getList(PageUtil pu, List<KeyValue> ands,List<KeyValue> likes);
	public int getCount(Class clazz,List<KeyValue> ands,List<KeyValue> likes);
	
	public List getList(Class clazz,List ins);
}
