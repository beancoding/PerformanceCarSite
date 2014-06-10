package com.dmcliver.performancecars.builders;

import org.slf4j.Logger;

public interface LoggerBuilder {

	<T> Logger build(Class<T> classToBeLogged);

}