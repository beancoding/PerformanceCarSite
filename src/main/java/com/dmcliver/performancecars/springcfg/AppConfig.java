package com.dmcliver.performancecars.springcfg;

import static com.dmcliver.performancecars.StringExtras.isNullOrEmpty;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource("classpath:/db.properties")
@EnableTransactionManagement
public class AppConfig {

	@Autowired
	private Environment env;
	
	@Bean(name = "transactionManager")
	public HibernateTransactionManager buildTransactionManager() throws PropertyVetoException{
		
		HibernateTransactionManager tx = new HibernateTransactionManager();
		tx.setSessionFactory(buildSessionFactoryBean().getObject());
		return tx;
	}

	@Bean
	public LocalSessionFactoryBean buildSessionFactoryBean() throws PropertyVetoException {
		
		LocalSessionFactoryBean sessFac = new LocalSessionFactoryBean();
		sessFac.setDataSource(buildDataSource());
		sessFac.setPackagesToScan("com.dmcliver.performancecars.domain");
		sessFac.setHibernateProperties(hibernateProperties());
		return sessFac;
	}

	@Bean
	public DataSource buildDataSource() throws PropertyVetoException {
		
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setJdbcUrl("jdbc:postgresql://localhost:5432/PerformanceCarsDb");
		ds.setUser("postgres");
		ds.setPassword(env.getProperty("user.pwd"));
		ds.setDriverClass("org.postgresql.Driver");
		return ds;
	}

	private Properties hibernateProperties() {

		String auto = env.getProperty("hbm2ddl.auto");
		Properties props = new Properties();
		props.put("hibernate.show_sql", true);
		props.put("hibernate.hbm2ddl.auto", isNullOrEmpty(auto) ? "update" : auto);
		return props;
	}
}
