package com.dmcliver.performancecars.controllers;

import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.project;
import static com.dmcliver.performancecars.Constants.addMake;
import static com.dmcliver.performancecars.StringExtras.equalIgnoreCase;
import static com.dmcliver.performancecars.StringExtras.isNullOrEmpty;
import static java.util.ResourceBundle.getBundle;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dmcliver.performancecars.builders.LoggerBuilder;
import com.dmcliver.performancecars.datalayer.ContinentDAO;
import com.dmcliver.performancecars.datalayer.CountryDAO;
import com.dmcliver.performancecars.datalayer.MakeDAO;
import com.dmcliver.performancecars.datalayer.ModelDAO;
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
	private Logger logger;
	private ModelDAO modelDAO;
	
	@Autowired
	public MakeController(MakeDAO makeDAO, ContinentDAO continentDAO, CountryDAO countryDAO, ModelDAO modelDAO, LoggerBuilder logBuilder) {
		
		this.makeDAO = makeDAO;
		this.continentDAO = continentDAO;
		this.countryDAO = countryDAO;
		this.modelDAO = modelDAO;
		logger = logBuilder.build(MakeController.class);
	}

	/**
	 * JSON response to return all the car makes
	 */
	@RequestMapping(value = "/getall/{level}", method = GET)
	public @ResponseBody List<?> getAll(@PathVariable("level") String level, @RequestParam("id") String id, @RequestParam("parent") String parent) {
		
		logger.info("getAll method called");
		if(equalIgnoreCase("none", level)) {

			List<?> makes = makeDAO.findAll();
			List<TreeModel> models = project(makes, TreeModel.class, on(Make.class).getName(), on(Make.class).toString());
			return models;
		}
		else if(equalIgnoreCase("make", level)) {
		
			List<TreeModel> yearModels = new ArrayList<TreeModel>();
			List<Integer> years = modelDAO.findYearsByName(id);
			for(Integer year: years) yearModels.add(new TreeModel(year.toString(), "Year"));
			return yearModels ;
		}
		else {
			
			int year = 0;
			try {
				year = Integer.parseInt(id);
			} catch (NumberFormatException ex) {}
			
			List<TreeModel> viewModels = new ArrayList<TreeModel>();
			List<String> modelNames = modelDAO.findByMakeAndYear(parent, year);
			for(String name: modelNames) viewModels.add(new TreeModel(name, "Model"));
			return viewModels;
		}
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
		catch (IllegalArgumentException ex) {

			logger.error("Continent exception", ex);
			result.reject("ContinentIdGenErr", "*Please select a valid continent");
			getContinents(model);
			return addMake;
		}
		catch (Exception ex) {

			logger.error("Continent exception", ex);
			result.reject("ContinentIdGenErr", getBundle("messages").getString("error.unexpected"));
			getContinents(model);
			return addMake;
		}
		
		Make make = new Make(formData.getMakeName(), country);
		try {
			makeDAO.save(make);
		}
		catch (DataIntegrityViolationException ex) {
			
			logger.error("Saving make error", ex);
			result.reject("SaveMakeErr","*A make with that name already exists");
			getContinents(model);
			return addMake;
		}
		catch(Exception ex) {
			
			logger.error("Saving make error", ex);
			result.reject("SaveMakeErr", getBundle("messages").getString("error.unexpected"));
			getContinents(model);
			return addMake;
		}
		return "redirect:/home/index";
	}
	
	private void getContinents(Model model) {
		
		List<?> continents = continentDAO.findAll();
		model.addAttribute("continents", continents);
	}
}
