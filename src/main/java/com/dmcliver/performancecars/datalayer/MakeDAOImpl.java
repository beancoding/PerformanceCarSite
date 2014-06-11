package com.dmcliver.performancecars.datalayer;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dmcliver.performancecars.domain.Make;

@Repository
public class MakeDAOImpl extends HibernateTemplateDAO<Make> implements MakeDAO {

	SessionFactory factory;

	@Autowired
	public MakeDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory, Make.class);
		factory = sessionFactory;
	}

	@Override
	@Transactional
	public Make findByName(String selectedMake) {
		return super.findById(selectedMake);
	}
}
