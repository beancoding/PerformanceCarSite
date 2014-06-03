package com.dmcliver.perfomancecars.domain;

import static com.dmcliver.performancecars.domain.EngineAspiration.twinTurbo;
import static com.dmcliver.performancecars.domain.EngineType.straightSix;
import static org.hamcrest.Matchers.is;
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
public class ModelYearTest {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	@Transactional
	public void canSave() {
		
		Session session = sessionFactory.getCurrentSession();

		final int expectedYear = 2005;
		final String expectedModelName = "997";
		final String makeName = "Porsche";
		
		buildExpectedModelYear(expectedYear, expectedModelName, session, makeName);
		
		ModelYear actualModelYear = (ModelYear) session.get(ModelYear.class, new ModelYearPK(expectedModelName, expectedYear));
		ModelYearPK actualModelYearPk = getModelYearPk(actualModelYear);
		Model actualModel = getModelName(actualModelYear);
		
		assertNotNull("Model year is null", actualModelYear);
		assertNotNull("Model year pk is null", actualModelYearPk);
		assertNotNull("Actual model is null", actualModel);
		assertNotNull("Make is null", actualModelYear.getMake());
		assertThat("Model year did not match expected result", actualModelYear.getMake().getName(), is(makeName));
		assertThat("Model year did not match expected result", actualModelYearPk.getYear(), is(expectedYear));
		assertThat("Model name did not match expected result", actualModel.getName(), is(expectedModelName));
	}

	private ModelYear buildExpectedModelYear(int year, String modelName, Session session, String makeName) {
		
		Country cnty = (Country) session.get(Country.class, "Germany");
		
		Make make = buildMake(session, cnty, makeName);
		Model model = buildModelWithName(session, make, modelName);
		ModelYear expectedModelYear = buildModelYear(year, session, model, make);
		
		return expectedModelYear;
	}

	private Make buildMake(Session session, Country cnty, String makeName) {
		
		Make mk = new Make(makeName, cnty);
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
	
	private ModelYear buildModelYear(final int year, Session session, Model m, Make make) {
		
		ModelYear my = new ModelYear(m, new ModelYearPK(m.getName(), year), make);
		session.save(my);
		return my;
	}

	private Model getModelName(ModelYear expectedModelYear) {
		return expectedModelYear.getModel();
	}

	private ModelYearPK getModelYearPk(ModelYear modelYear) {
		return modelYear.getModelYearPK();
	}
}