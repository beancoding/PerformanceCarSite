package com.dmcliver.performancecars.datalayer;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public abstract class HibernateTemplateDAO<T> {

	private SessionFactory sessionFactory;
	private Class<T> entityType;

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
}