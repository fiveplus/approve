package com.approve.services;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.approve.struts.util.PageUtil;


/**
 * 系统基础级服务,下一级服务需要继承此类
 * @author Mr.Zhang
 *
 */
public class BaseService implements BaseInterface {
	protected BaseDAO baseDAO;

	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public List getList(PageUtil pu, List<KeyValue> ands,List<KeyValue> likes,List<KeyValue> nos,Map<String,Long> betweens){
		String hql = pu.getHql();
		if(ands != null){
			for(KeyValue kv : ands){
				hql += " AND " + PageUtil.TEMP + "." + kv.getKey() + " = :" + kv.getKey().replace(".", "");
			}
		}
		if(likes != null && likes.size() > 0){
			int t = 0;
			for(KeyValue kv : likes){
				if(t > 0){
					hql += " OR ";
				}else if(t == 0){
					hql += " AND ( ";
				}
				hql += PageUtil.TEMP + "." + kv.getKey() + " like :" + kv.getKey().replace(".", "");
				t++;
			}
			hql += " ) ";
		}
		if(nos != null){
			for(KeyValue kv : nos){
				hql += " AND " + PageUtil.TEMP + "." + kv.getKey() + " != :" + kv.getKey().replace(".", "");
			}
		}
		if(betweens != null){
			hql += " AND " + PageUtil.TEMP + ".createTime > " + betweens.get("beforeTime")+" ";
			hql += " AND " + PageUtil.TEMP + ".createTime < " + betweens.get("afterTime")+" ";
		}
		hql += " ORDER BY "+PageUtil.TEMP+".createTime DESC ";
		
		
		Session session = this.baseDAO.getBaseSession();
		Query query = session.createQuery(hql);
		if(ands != null){
			for(KeyValue kv : ands){
				query.setParameter(kv.getKey().replace(".", ""), kv.getValue());
			}
		}
		if(likes != null){
			for(KeyValue kv : likes){
				query.setParameter(kv.getKey().replace(".", ""), kv.getValue());
			}
		}
		if(nos != null){
			for(KeyValue kv : nos){
				query.setParameter(kv.getKey().replace(".", ""), kv.getValue());
			}
		}
		query.setFirstResult((pu.getPageNum() - 1) * pu.getPageSize()); 
		query.setMaxResults(pu.getPageSize()); 
		List list = query.list();
		
		return list;
	}
	
	public List getList(Class clazz, List<KeyValue> ands,List<KeyValue> likes,List<KeyValue> nos,Map<String,Long> betweens){
		String hql = "FROM "+clazz.getSimpleName()+" a WHERE 1 = 1 ";
		if(ands != null){
			for(KeyValue kv : ands){
				hql += " AND " + PageUtil.TEMP + "." + kv.getKey() + " = :" + kv.getKey().replace(".", "");
			}
		}
		if(likes != null && likes.size() > 0){
			int t = 0;
			for(KeyValue kv : likes){
				if(t > 0){
					hql += " OR ";
				}else if(t == 0){
					hql += " AND ( ";
				}
				hql += PageUtil.TEMP + "." + kv.getKey() + " like :" + kv.getKey().replace(".", "");
				t++;
			}
			hql += " ) ";
		}
		if(nos != null){
			for(KeyValue kv : nos){
				hql += " AND " + PageUtil.TEMP + "." + kv.getKey() + " != :" + kv.getKey().replace(".", "");
			}
		}
		if(betweens != null){
			hql += " AND " + PageUtil.TEMP + ".createTime > " + betweens.get("beforeTime")+" ";
			hql += " AND " + PageUtil.TEMP + ".createTime < " + betweens.get("afterTime")+" ";
		}
		hql += " order by "+PageUtil.TEMP+".createTime desc ";
		Session session = this.baseDAO.getBaseSession();
		Query query = session.createQuery(hql);
		if(ands != null){
			for(KeyValue kv : ands){
				query.setParameter(kv.getKey().replace(".", ""), kv.getValue());
			}
		}
		if(likes != null && likes.size() > 0){
			for(KeyValue kv : likes){
				query.setParameter(kv.getKey().replace(".", ""), kv.getValue());
			}
		}
		if(nos != null){
			for(KeyValue kv : nos){
				query.setParameter(kv.getKey().replace(".", ""), kv.getValue());
			}
		}
		List list = query.list();
		return list;
	}
	
	
	public List getList(PageUtil pu, List<KeyValue> ands,List<KeyValue> likes,List<KeyValue> nos){
		return getList(pu, ands, likes,nos,null);
	}
	public int getCount(Class clazz,List<KeyValue> ands,List<KeyValue> likes,List<KeyValue> nos){
		return getCount(clazz,ands, likes,nos,null);
	}
	
	public int getCount(Class clazz,List<KeyValue> ands,List<KeyValue> likes,List<KeyValue> nos,Map<String,Long> betweens){
		String hql = "SELECT COUNT(*) FROM " + clazz.getSimpleName() + " a where 1 = 1 ";
		if(ands != null){
			for(KeyValue kv : ands){
				hql += " AND " + PageUtil.TEMP + "." + kv.getKey() + " = :" + kv.getKey().replace(".", "");
			}
		}
		if(likes != null && likes.size() > 0){
			int t = 0;
			for(KeyValue kv : likes){
				if(t > 0){
					hql += " OR ";
				}else if(t == 0){
					hql += " AND ( ";
				}
				hql += PageUtil.TEMP + "." + kv.getKey() + " like :" + kv.getKey().replace(".", "");
				t++;
			}
			hql += " ) ";
		}
		if(nos != null){
			for(KeyValue kv : nos){
				hql += " AND " + PageUtil.TEMP + "." + kv.getKey() + " != :" + kv.getKey().replace(".", "");
			}
		}
		if(betweens != null){
			hql += " AND " + PageUtil.TEMP + ".createTime > " + betweens.get("beforeTime")+" ";
			hql += " AND " + PageUtil.TEMP + ".createTime < " + betweens.get("afterTime")+" ";
		}
		int count = 0;
		Session session = this.baseDAO.getBaseSession();
		Query query = session.createQuery(hql);
		if(ands != null){
			for(KeyValue kv : ands){
				query.setParameter(kv.getKey().replace(".", ""), kv.getValue());
			}
		}
		if(likes != null && likes.size() > 0){
			for(KeyValue kv : likes){
				query.setParameter(kv.getKey().replace(".", ""), kv.getValue());
			}
		}
		if(nos != null){
			for(KeyValue kv : nos){
				query.setParameter(kv.getKey().replace(".", ""), kv.getValue());
			}
		}
		
		count = (new Integer(query.uniqueResult().toString())).intValue(); 
		return count;
	}

	public int getCount(Class clazz, List<KeyValue> ands, List<KeyValue> likes) {
		return getCount(clazz, ands, likes,null);
	}

	public List getList(PageUtil pu, List<KeyValue> ands, List<KeyValue> likes) {
		return getList(pu, ands, likes,null);
	}

	public List getList(Class clazz,List ins) {
		String hql = "FROM  "+clazz.getSimpleName()+" where id in (:ins) ";
		Session session = this.baseDAO.getBaseSession();
		Query query = session.createQuery(hql);
		query.setParameterList("ins", ins);
		List list = query.list();
		return list;
	}
	
	
}
