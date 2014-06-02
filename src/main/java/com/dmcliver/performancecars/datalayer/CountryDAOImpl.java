package com.dmcliver.performancecars.datalayer;

import static org.hibernate.criterion.Restrictions.eq;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dmcliver.performancecars.domain.Country;

@Repository
public class CountryDAOImpl implements CountryDAO {

	@Autowired
	private SessionFactory factory;
	
	@Transactional
	public Country findById(String id) {
		
		Session session = factory.getCurrentSession();
		return (Country) session.get(Country.class, id);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Country> findByContinentId(String id) {

		Session session = factory.getCurrentSession();
		
		List<Country> countries = session.createCriteria(Country.class)
										 .createAlias("continent", "r")
										 .add(eq("r.name", id))
										 .list();
		return countries;
	}
}
