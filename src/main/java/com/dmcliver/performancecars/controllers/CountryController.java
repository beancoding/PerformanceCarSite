package com.dmcliver.performancecars.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dmcliver.performancecars.datalayer.CountryDAO;
import com.dmcliver.performancecars.domain.Country;
import com.dmcliver.performancecars.models.CountryModel;

import static ch.lambdaj.Lambda.*;

@Controller
@RequestMapping("countries")
public class CountryController {

	private CountryDAO countryDAO;
	
	@Autowired
	public CountryController(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	@RequestMapping("/bycontinent/{continentId}")
	public @ResponseBody List<CountryModel> findByContinent(@PathVariable("continentId") String id){
		
		List<Country> countries = countryDAO.findByContinentId(id);
		List<CountryModel> model = project(countries, CountryModel.class, on(Country.class).getName());
		return model;
	}
}
