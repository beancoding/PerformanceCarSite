package com.dmcliver.performancecars.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dmcliver.performancecars.builders.LoggerBuilder;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	private Logger logger;
	
	@Autowired
	public HomeController(LoggerBuilder logBuilder) {
		logger = logBuilder.build(HomeController.class);
	}
	
	@RequestMapping(value = "/index", method = GET)
	public String index(Model model) {
		
		logger.info("index method called");
		return "homeIndex";
	}
}
