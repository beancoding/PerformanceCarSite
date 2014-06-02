package com.dmcliver.performancecars.controllers;

import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.project;
import static com.dmcliver.performancecars.Constants.addMake;
import static com.dmcliver.performancecars.StringExtras.equalIgnoreCase;
import static com.dmcliver.performancecars.StringExtras.isNullOrEmpty;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dmcliver.performancecars.datalayer.ContinentDAO;
import com.dmcliver.performancecars.datalayer.CountryDAO;
import com.dmcliver.performancecars.datalayer.MakeDAO;
import com.dmcliver.performancecars.domain.Country;
import com.dmcliver.performancecars.domain.Make;
import com.dmcliver.performancecars.models.MakeViewModel;
import com.dmcliver.performancecars.models.TreeModel;

@Controller
@RequestMapping("/makes")
public class MakeController {

	private MakeDAO makeDAO;
	private ContinentDAO continentDAO;
	private CountryDAO countryDAO;
	
	@Autowired
	public MakeController(MakeDAO makeDAO, ContinentDAO continentDAO, CountryDAO countryDAO) {
		
		this.makeDAO = makeDAO;
		this.continentDAO = continentDAO;
		this.countryDAO = countryDAO;
	}

	/**
	 * JSON response to return all the car makes
	 */
	@RequestMapping(value = "/getall/{level}", method = GET)
	public @ResponseBody List<?> getAll(@PathVariable("level") String level, @RequestParam("id") String id) {
		
		if(!equalIgnoreCase("none", level)) 
			return new ArrayList<Object>(1);

		List<?> makes = makeDAO.findAll();
		List<TreeModel> models = project(makes, TreeModel.class, on(Make.class).getName(), on(Make.class).toString());
		return models;
	}
	
	@RequestMapping(value = "/add", method = GET)
	public String prepareAdd(Model model) {
		
		getContinents(model);
		model.addAttribute("make", new MakeViewModel());
		
		return addMake;
	}
	
	@RequestMapping(value = "/add", method = POST)
	public String add(@Valid @ModelAttribute("make") MakeViewModel formData, BindingResult result, Model model) {

		if(result.hasErrors()) {
			
			getContinents(model);
			return addMake;
		}
		
		if(isNullOrEmpty(formData.getContinentId())) {
			
			result.reject("ContinentIdGenErr", "*Must select a continent");
			getContinents(model);
			return addMake;
		}
		
		Country country = null;
		try {
			country = countryDAO.findById(formData.getCountriesId());
		} 
		catch (Exception ex) {

			result.reject("ContinentIdGenErr", "*Please select a valid continent");
			getContinents(model);
			return addMake;
		}
		
		Make make = new Make(formData.getMakeName(), country);
		makeDAO.save(make);
		
		return "redirect:/home/index";
	}
	
	private void getContinents(Model model) {
		
		List<?> continents = continentDAO.findAll();
		model.addAttribute("continents", continents);
	}
}
