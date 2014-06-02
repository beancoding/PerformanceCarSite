package com.dmcliver.performancecars.datalayer;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dmcliver.performancecars.domain.Continent;

@Repository
public class ContinentDAOImpl extends HibernateTemplateDAO<Continent> implements ContinentDAO {

	@Autowired
	public ContinentDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory, Continent.class);
	}
}
