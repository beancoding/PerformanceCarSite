package com.dmcliver.performancecars.datalayer;

import static org.hibernate.criterion.Restrictions.eq;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dmcliver.performancecars.domain.Model;
import com.dmcliver.performancecars.domain.ModelYear;

@Repository
public class ModelDAOImpl extends HibernateTemplateDAO<Model> implements ModelDAO {

	private SessionFactory factory;
	
	@Autowired
	public ModelDAOImpl(SessionFactory factory) {
		super(factory, Model.class);
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

	@Override
	@Transactional
	public void save(Model model) {
		
		Session session = factory.getCurrentSession();
		session.save(model);
	}

	@Override
	@Transactional
	public void save(ModelYear modelYear) {
		
		Session session = factory.getCurrentSession();
		session.save(modelYear);
	}

	@Override
	@Transactional
	public Model findByName(String modelName) {
		return super.findById(modelName);
	}
}