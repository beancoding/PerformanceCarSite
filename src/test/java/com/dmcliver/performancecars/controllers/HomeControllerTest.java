package com.dmcliver.performancecars.controllers;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.ui.Model;

import com.dmcliver.performancecars.LoggerBuilder;
import com.dmcliver.performancecars.controllers.HomeController;

public class HomeControllerTest {

	@Test
	public void index_LogsCall() {
		
		Logger logger = mock(Logger.class);
		Model model = mock(Model.class);

		LoggerBuilder builder = mock(LoggerBuilder.class);
		when(builder.build(HomeController.class)).thenReturn(logger);
		
		HomeController controller = new HomeController(builder);
		controller.index(model);
		
		verify(logger).info(anyString());
		assertThat(true, is(greaterThan(false))); 
	}
}
