package com.approve.struts.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * �࿽��������
 * @author Mr.Zhang
 *
 */
public class BeanCopyUtil {
	/**
	 * �࿽��
	 * @param oldO ԭʼ��
	 * @param newO ���ƺ������
	 */
	public static void beanCopy(Object oldO,Object newO){
		List<DbEntityInfo> listA = ReflexEntites.getAttributesByEntity(oldO.getClass());
		List<DbEntityInfo> listB = ReflexEntites.getAttributesByEntity(newO.getClass());
		for(DbEntityInfo dB:listB){
			String newName = dB.getName(); 
			for(DbEntityInfo dA:listA){
				String oldName = dA.getName();
				if(newName.equals(oldName)){
					try {
						String methodName = "get"+oldName.substring(0,1).toUpperCase()+oldName.substring(1);
						String methodNameS = "set"+oldName.substring(0,1).toUpperCase()+oldName.substring(1);
						Method[] methods  = oldO.getClass().getMethods();
						Method[] newMethods = newO.getClass().getMethods();
						for(Method m: methods){
							if(m.getName().equals(methodName)){
								Object o = m.invoke(oldO);
								if(o!=null){
									//�õ�ֵ ��ֵ��newO
									for(Method newM:newMethods){
										if(newM.getName().equals(methodNameS)){
											newM.invoke(newO,o);
											break;
										}
									}
								}
								break;
							}
						}
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} 
					break;
				}
			}
		}
	}
}
