package com.dmcliver.performancecars.builders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerBuilderImpl implements LoggerBuilder {

	public <T> Logger build(Class<T> classToBeLogged) {
		
		return LoggerFactory.getLogger(classToBeLogged); 
	}
}
