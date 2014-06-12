package com.dmcliver.performancecars.datalayer;

import static com.dmcliver.performancecars.StringExtras.emptyStr;
import static java.lang.String.valueOf;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.dmcliver.performancecars.StringExtras;

public abstract class HibernateTemplateDAO<T> {

	private SessionFactory sessionFactory;
	private Class<T> entityType;
	protected String methName;

	public HibernateTemplateDAO(SessionFactory sessionFactory, Class<T> daoEntityType) {
		this.sessionFactory = sessionFactory;
		this.entityType = daoEntityType;
	}

	@Transactional
	public List<?> findAll() {
		
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(entityType).list();
	}

	@Transactional
	public void save(T entity) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public T findById(Serializable id) {
		
		Session session = sessionFactory.getCurrentSession();
		return (T)session.get(entityType, id);
	}

	@SuppressWarnings("unchecked")
	protected <U> U on(Class<U> type) {
		
		Enhancer h = new Enhancer();
		h.setSuperclass(type);
		
		h.setCallback(new InvocationHandler(){
	
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				methName = method.getName();
				return null;
			}
		});
		return (U)h.create();
	}
	
	public String name(Object getter){
		
		methName = methName.replace("get", emptyStr);
		String lower = valueOf(methName.charAt(0)).toLowerCase();
		return lower + methName.substring(1);
	}
}