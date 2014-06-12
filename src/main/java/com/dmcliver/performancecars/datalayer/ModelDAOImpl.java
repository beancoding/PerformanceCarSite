package com.dmcliver.performancecars.datalayer;

import static org.hibernate.criterion.Projections.distinct;
import static org.hibernate.criterion.Projections.property;
import static org.hibernate.criterion.Restrictions.eq;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dmcliver.performancecars.domain.Model;
import com.dmcliver.performancecars.domain.ModelYear;
import com.dmcliver.performancecars.domain.ModelYearPK;

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

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Integer> findYearsByName(String id) {
		
		String model = name(on(ModelYear.class).getModel());

		Session session = factory.getCurrentSession();
		List<Integer> years = session.createCriteria(ModelYear.class)
					  				 .createAlias(model, "m")
					  				 .createAlias("m.make", "mk")
					  				 .add(eq("mk.name", id))
					  				 .setProjection(distinct(property("modelYearPK.year")))
					  				 .list();
		return years;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<String> findByMakeAndYear(String make, int year) {
		
		Session session = factory.getCurrentSession();
		return session.createCriteria(ModelYear.class)
					  .createAlias("model", "m")
					  .createAlias("m.make", "mk")
					  .add(eq("mk.name", make))
					  .add(eq("modelYearPK.year", year))
					  .setProjection(property("m.name"))
					  .list();
	}

	@Override
	@Transactional
	public ModelYear findByNameAndYear(String modelName, int modelYear) {
		
		Session session = factory.getCurrentSession();
		return (ModelYear) session.get(ModelYear.class, new ModelYearPK(modelName, modelYear));
	}
}