package com.approve.struts.util;
import java.lang.reflect.Field;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ¿‡øΩ±¥∑¥…‰∞Ô÷˙¿‡
 * @author Mr.zhang
 *
 */
public class ReflexEntites {
	
	public static List<DbEntityInfo> getAttributesByEntity(Class c) {
		List<DbEntityInfo> list = new ArrayList<DbEntityInfo>();
		Field[] fs = c.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			String name = fs[i].getName();
			String type = fs[i].getType().getName();
			DbEntityInfo db = new DbEntityInfo(name, type);
			list.add(db);
		}
		return list;
	}
	
	public static List<Object> getMapToObject2(List<Map<String, Object>> listMap, Class c) {
		List<Object> list = new ArrayList<Object>();
		try {
			
			for (Map<String, Object> map : listMap) {
				Object obj = c.newInstance();
				List<DbEntityInfo> enties = getAttributesByEntity(obj.getClass());
				Method[] methods = obj.getClass().getMethods();
				for (int i = 0; i < methods.length; i++) {
					Method method = methods[i];
					if (method.getName().startsWith("set")) {
						String temp = method.getName().substring(3);
						temp = temp.substring(0, 1).toLowerCase()
								+ temp.substring(1);
						for (DbEntityInfo db : enties) {
							String key = db.getName();
							String type = db.getType();
							
							if (key.equals(temp)) {
								Object value = map.get(key);
								if(value==null){
									method.invoke(obj,(Object)null);
								}else{
									if (type.equals("java.lang.Integer")) {
										// int ÔøΩÔøΩÔøΩÔøΩ
										method.invoke(obj,value);
									} else if (type.equals("java.lang.String")) {
										// String ÔøΩÔøΩÔøΩÔøΩ
										method.invoke(obj,value);
									} else if (type.equals("java.lang.Double")) {
										// Double ÔøΩÔøΩÔøΩÔøΩ
										method.invoke(obj,value);
									} else if (type.equals("java.util.Date")) {
										// DateÔøΩÔøΩÔøΩÔøΩ
										Date tempValue = StringUtil.getStringToDate((String)value);
										method.invoke(obj,(Object)tempValue);
									}else{
										//method.invoke(obj,value);
									}
								}
								break;
							}
						}
					}
				}
				list.add(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	
		return list;
	}

	
	public static Object getMapToObject(Map<String, Object> map, Class c) {
		Object obj = null;
		try {
			obj = c.newInstance();
				List<DbEntityInfo> enties = getAttributesByEntity(obj.getClass());
				Method[] methods = obj.getClass().getMethods();
				for (int i = 0; i < methods.length; i++) {
					Method method = methods[i];
					if (method.getName().startsWith("set")) {
						String temp = method.getName().substring(3);
						temp = temp.substring(0, 1).toLowerCase()
								+ temp.substring(1);
						for (DbEntityInfo db : enties) {
							String key = db.getName();
							String type = db.getType();
							
							if (key.equals(temp)) {
								Object value = map.get(key);
								if(value==null){
									method.invoke(obj,(Object)null);
								}else{
									if (type.equals("java.lang.Integer")) {
										// int ÔøΩÔøΩÔøΩÔøΩ
										method.invoke(obj,value);
									} else if (type.equals("java.lang.String")) {
										// String ÔøΩÔøΩÔøΩÔøΩ
										method.invoke(obj,value);
									} else if (type.equals("java.lang.Double")) {
										// Double ÔøΩÔøΩÔøΩÔøΩ
										method.invoke(obj,value);
									} else if (type.equals("java.util.Date")) {
										// DateÔøΩÔøΩÔøΩÔøΩ
										Date tempValue = StringUtil.getStringToDate((String)value);
										method.invoke(obj,(Object)tempValue);
									} else {
										// ÔøΩÔøΩÔøΩÔøΩ
										method.invoke(obj,value);
									}
								}
								break;
							}
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return obj;
	}
	
	
	
	
	
	public static List<Object> getMaptoObject(List<Map<String, String>> listMap, Class c) {
		List<Object> list = new ArrayList<Object>();
		try {
			
			for (Map<String, String> map : listMap) {
				Object obj = c.newInstance();
				List<DbEntityInfo> enties = getAttributesByEntity(obj.getClass());
				Method[] methods = obj.getClass().getMethods();
				for (int i = 0; i < methods.length; i++) {
					Method method = methods[i];
					if (method.getName().startsWith("set")) {
						String temp = method.getName().substring(3);
						temp = temp.substring(0, 1).toLowerCase()
								+ temp.substring(1);
						for (DbEntityInfo db : enties) {
							String key = db.getName();
							String type = db.getType();
							
							if (key.equals(temp)) {
								String value = map.get(key);
								if(value==null){
									method.invoke(obj,(Object)null);
								}else{
									if (type.equals("java.lang.Integer")) {
										// int ÔøΩÔøΩÔøΩÔøΩ
										Integer tempValue = Integer.parseInt(value);
										method.invoke(obj,(Object)tempValue);
									} else if (type.equals("java.lang.String")) {
										// String ÔøΩÔøΩÔøΩÔøΩ
										method.invoke(obj,(Object)value);
									} else if (type.equals("java.lang.Double")) {
										// Double ÔøΩÔøΩÔøΩÔøΩ
										Double tempValue = Double.parseDouble(value);
										method.invoke(obj,(Object)tempValue);
									} else if (type.equals("java.util.Date")) {
										// DateÔøΩÔøΩÔøΩÔøΩ
										Date tempValue = StringUtil.getStringToDate(value);
										method.invoke(obj,(Object)tempValue);
										System.out.println(tempValue);
									} else {
										// ÔøΩÔøΩÔøΩÔøΩ
										method.invoke(obj,(Object)value);
									}
								}
								break;
							}
						}
					}
				}
				list.add(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	
		return list;
	}

	public static Object[] getValues(Object obj) {
		List<DbEntityInfo> list = getAttributesByEntity(obj.getClass());
		int length = list.size();
		Object[] objs = new Object[length];
		int tp = 0;
		try {
			Method[] methods = obj.getClass().getMethods();

			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				if (method.getName().startsWith("get")) {

					String temp = method.getName().substring(3);
					temp = temp.substring(0, 1).toLowerCase()
							+ temp.substring(1);

					for (DbEntityInfo db : list) {
						String key = db.getName();
						String type = db.getType();
						if (key.equals(temp)) {
							Object o = method.invoke(obj);
							if (o != null) {
								if (type.equals("java.util.Date")) {
									Date d = (Date) o;
									objs[tp] = StringUtil.getDatetToString(d);
								} else {
									objs[tp] = o;
								}

							} else {
								objs[tp] = null;
							}
							tp++;
							break;
						}

					}
				}
			}

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return objs;
	}

}
