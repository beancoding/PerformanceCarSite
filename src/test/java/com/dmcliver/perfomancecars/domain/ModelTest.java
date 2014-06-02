package com.dmcliver.perfomancecars.domain;

import static com.dmcliver.performancecars.domain.EngineAspiration.twinTurbo;
import static com.dmcliver.performancecars.domain.EngineType.straightSix;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dmcliver.performancecars.domain.Country;
import com.dmcliver.performancecars.domain.Make;
import com.dmcliver.performancecars.domain.Model;
import com.dmcliver.performancecars.domain.ModelYear;
import com.dmcliver.performancecars.domain.ModelYearPK;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration("classpath:/servlet-context.xml")
public class ModelTest {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	@Transactional
	public void canSave() {
		
		final int year = 2005;
		final String modelName = "997";
		
		Session session = sessionFactory.getCurrentSession();
		
		Country cnty = (Country) session.get(Country.class, "Germany");
		
		Make mk = buildMake(session, cnty);
		
		Model m = buildModelWithName(session, mk, modelName);
		
		ModelYear my = new ModelYear(m, new ModelYearPK(m.getName(), year));
		session.save(my);
		
		ModelYear modelYear = (ModelYear) session.get(ModelYear.class, new ModelYearPK(m.getName(), year));
		
		assertNotNull(modelYear);
		assertThat(modelYear.getModel(), notNullValue());
		assertThat(modelYear.getModelYearPK(), notNullValue());
		assertThat(modelYear.getModelYearPK().getYear(), equalTo(year));
		assertThat(modelYear.getModel().getName(), equalTo(modelName));
	}

	private Make buildMake(Session session, Country cnty) {
		
		Make mk = new Make("Porsche", cnty);
		session.save(mk);
		return mk;
	}

	private Model buildModelWithName(Session session, Make mk, String name) {
		
		Model m = new Model();
		m.setName(name);
		m.setMake(mk);
		m.setEngineSize(new BigDecimal(3.6));
		m.setEngineAspiration(twinTurbo);
		m.setEngineType(straightSix);
		session.save(m);
		return m;
	}
}
