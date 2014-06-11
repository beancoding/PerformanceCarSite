package com.dmcliver.performancecars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dmcliver.performancecars.domain.Continent;
import com.dmcliver.performancecars.domain.Country;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration("classpath:/servlet-context.xml")
public class PopulateData {

	@Autowired
	private SessionFactory factory;
	
	@Test
	@Transactional
	public void run() {
		
		Continent europa = new Continent("Europe", true);
		Continent usa = new Continent("America", true);
		Continent ozzie = new Continent("Australasia", false);
		Continent nese = new Continent("Asia", true);

		Session session = factory.getCurrentSession();

		populateContinents(europa, usa, ozzie, nese, session);
		
		populateEuropa(europa, session);
		populateAsia(nese, session);

		session.save(new Country("USA", usa));
		session.save(new Country("Australia", ozzie));
	}

	private void populateAsia(Continent nese, Session session) {
		
		session.save(new Country("Japan", nese));
		session.save(new Country("South Korea", nese));
	}

	private void populateContinents(Continent europa, Continent usa, Continent ozzie, Continent nese, Session session) {
		
		session.save(europa);
		session.save(usa);
		session.save(ozzie);
		session.save(nese);
	}

	private void populateEuropa(Continent europa, Session session) {
		
		session.save(new Country("Germany", europa));
		session.save(new Country("France", europa));
		session.save(new Country("Italy", europa));
		session.save(new Country("Britain", europa));
		session.save(new Country("Sweden", europa));
		session.save(new Country("Russia", europa));
	}
}
