package com.dmcliver.performancecars.controllers;

import static com.dmcliver.performancecars.EnumMetadata.getTag;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dmcliver.performancecars.EnumMetadata;
import com.dmcliver.performancecars.datalayer.MakeDAO;
import com.dmcliver.performancecars.domain.EngineAspiration;
import com.dmcliver.performancecars.domain.EngineType;
import com.dmcliver.performancecars.domain.Make;
import com.dmcliver.performancecars.models.VehicleModel;

@Controller
@RequestMapping("/models")
public class ModelController {

	private MakeDAO makeDAO;

	@Autowired
	public ModelController(MakeDAO makeDAO) {
		this.makeDAO = makeDAO;
	}
	
	@RequestMapping(value = "/add", method = GET)
	public String viewAdd(Model model) {
		
		HashMap<String,String> engines = new HashMap<String,String>();
		for (EngineType engineType : EngineType.values()) engines.put(engineType.name(), getTag(engineType));

		HashMap<String,String> aspiration = new HashMap<String,String>();
		for(EngineAspiration aspirationType : EngineAspiration.values()) aspiration.put(aspirationType.name(), getTag(aspirationType));
		
		List<?> makes = makeDAO.findAll();
		
		model.addAttribute("engines", engines);
		model.addAttribute("aspiration", aspiration);
		model.addAttribute("makes", makes);
		model.addAttribute("vehicleModel", new VehicleModel());
		
		return "addModel";
	}
	
	@RequestMapping(value = "/add", method = POST)
	public String add(@Valid @ModelAttribute("vehicleModel") VehicleModel vehicle, BindingResult result, Model model) {
	
		
		return "redirect:/home/index";
	}
}