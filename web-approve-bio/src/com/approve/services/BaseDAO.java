package com.approve.services;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.approve.struts.util.PageUtil;

/**
 * »ù´¡DAO
 * @author Mr.Zhang
 *
 * @param <T>
 */
public class BaseDAO<T> extends HibernateDaoSupport  {
	public Serializable save(T t){
		return this.getHibernateTemplate().save(t);
	}
	public void update(T t){
		this.getHibernateTemplate().update(t);
	}
	public void delete(T t){
		this.getHibernateTemplate().delete(t);
	}
	public void deleteAll(Collection entities){
		this.getHibernateTemplate().deleteAll(entities);
	}
	
	public Object findById(Class clazz,Serializable id){
		return this.getHibernateTemplate().get(clazz, id);
	}
	
	public List<T> findAll(Class clazz){
		String hql = "FROM " + clazz.getName();
		return this.getHibernateTemplate().find(hql);
	}
	
	public List findByNamedQueryAndNamedParam(String hql, String pro[], Object val[])
	{				
		return this.getHibernateTemplate().findByNamedQueryAndNamedParam(hql, pro, val);
	}
	
	public List findByNamedQueryAndNamedParam(String hql, String pro, Object val)
	{
		return this.getHibernateTemplate().findByNamedQueryAndNamedParam(hql, pro, val);
	}
	
	public List findByNamedQueryAndNamedParam(String hql){
		return this.getHibernateTemplate().findByNamedQuery(hql);
	}
	
	public Session getBaseSession(){
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession();
	}
	
	
}
