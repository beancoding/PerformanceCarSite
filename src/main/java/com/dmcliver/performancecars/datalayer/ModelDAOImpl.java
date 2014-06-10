package com.dmcliver.performancecars.datalayer;

import static org.hibernate.criterion.Restrictions.eq;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dmcliver.performancecars.domain.Model;

@Repository
public class ModelDAOImpl implements ModelDAO {

	private SessionFactory factory;
	
	@Autowired
	public ModelDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Model> findAll(String makeName) {

		Session session = factory.getCurrentSession();
		
		return session.createCriteria(Model.class)
					  .createAlias("make", "mk")
					  .add(eq("mk.name", makeName))
					  .list();
	}
}