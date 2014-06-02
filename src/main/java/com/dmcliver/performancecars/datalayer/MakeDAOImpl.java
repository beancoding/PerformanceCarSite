package com.dmcliver.performancecars.datalayer;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dmcliver.performancecars.domain.Make;

@Repository
public class MakeDAOImpl extends HibernateTemplateDAO<Make> implements MakeDAO {

	@Autowired
	public MakeDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory, Make.class);
	}
}
