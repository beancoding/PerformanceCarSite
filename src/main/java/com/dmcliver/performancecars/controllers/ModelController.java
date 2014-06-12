package com.dmcliver.performancecars.controllers;

import static com.dmcliver.performancecars.EnumMetadata.getTag;
import static com.dmcliver.performancecars.StringExtras.emptyStr;
import static com.dmcliver.performancecars.domain.EngineAspiration.valueOf;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dmcliver.performancecars.ResourceFileUtils;
import com.dmcliver.performancecars.StringExtras;
import com.dmcliver.performancecars.builders.LoggerBuilder;
import com.dmcliver.performancecars.datalayer.MakeDAO;
import com.dmcliver.performancecars.datalayer.ModelDAO;
import com.dmcliver.performancecars.domain.EngineAspiration;
import com.dmcliver.performancecars.domain.EngineType;
import com.dmcliver.performancecars.domain.Make;
import com.dmcliver.performancecars.domain.ModelYear;
import com.dmcliver.performancecars.domain.ModelYearPK;
import com.dmcliver.performancecars.models.VehicleModel;
import com.dmcliver.performancecars.services.FileService;

@Controller
@RequestMapping("/models")
public class ModelController {

	private MakeDAO makeDAO;
	private ModelDAO modelDAO;
	private FileService fileService;
	private ResourceFileUtils fileUtils;
	private Logger logger;

	@Autowired
	public ModelController(MakeDAO makeDAO, ModelDAO modelDAO, FileService fileService, ResourceFileUtils fileUtils, LoggerBuilder logBuilder) {
		
		this.makeDAO = makeDAO;
		this.modelDAO = modelDAO;
		this.fileService = fileService;
		this.fileUtils = fileUtils;
		logger = logBuilder.build(ModelController.class);
	}
	
	@RequestMapping(value = "/add", method = GET)
	public String viewAdd(Model model) {
		
		buildModel(model);
		model.addAttribute("vehicleModel", new VehicleModel());
		return "addModel";
	}

	private void buildModel(Model model) {
		
		HashMap<String,String> engines = new HashMap<String,String>();
		for (EngineType engineType : EngineType.values()) engines.put(engineType.name(), getTag(engineType));

		HashMap<String,String> aspiration = new HashMap<String,String>();
		for(EngineAspiration aspirationType : EngineAspiration.values()) aspiration.put(aspirationType.name(), getTag(aspirationType));
		
		List<?> makes = makeDAO.findAll();
		
		model.addAttribute("engines", engines);
		model.addAttribute("aspiration", aspiration);
		model.addAttribute("makes", makes);
	}
	
	@RequestMapping(value = "/getAll/{makeName}", method = GET) 
	public @ResponseBody List<com.dmcliver.performancecars.domain.Model> getAll(@PathVariable("makeName") String makeName) {
		
		return modelDAO.findAll(makeName); 
	}
	
	@RequestMapping(value = "/get/{modelName}", method = GET)
	public @ResponseBody ModelYear get(@PathVariable("modelName") String modelName, @RequestParam("year") String year){
		
		int modelYear = 0;
		try {
			modelYear = Integer.parseInt(year);
		} catch (NumberFormatException ex) {}
		
		ModelYear model = modelDAO.findByNameAndYear(modelName, modelYear);
		return model == null ? new ModelYear() : model;
	}
	
	@RequestMapping(value = "/add", method = POST)
	public String add(@Valid @ModelAttribute("vehicleModel") VehicleModel vehicle, BindingResult result, @RequestParam(value = "carPic", required = false) MultipartFile pic, Model model) throws IOException {
	
		if(result.hasErrors()) {
		
			buildModel(model);
			return "addModel";
		}
		
		fileService.saveFile(pic);
		
		com.dmcliver.performancecars.domain.Model modelToBeSaved = null;
		if(!vehicle.isExistingModel()) 
			modelToBeSaved = createNewModel(vehicle);
		else 
			modelToBeSaved = modelDAO.findByName(vehicle.getModelName());
		
		int year = vehicle.getCentury() * 100 + vehicle.getDecade() * 10 + vehicle.getYear();

		ModelYear modelYear = buildModelDetails(vehicle, modelToBeSaved, year, pic.getOriginalFilename());
		modelDAO.save(modelYear);
		
		return "redirect:/home/index";
	}

	private com.dmcliver.performancecars.domain.Model createNewModel(VehicleModel vehicle) {
		
		com.dmcliver.performancecars.domain.Model modelToBeSaved;
		Make make = makeDAO.findByName(vehicle.getSelectedMake());
		modelToBeSaved = new com.dmcliver.performancecars.domain.Model(vehicle.getModelName(), make);
		modelDAO.save(modelToBeSaved);
		return modelToBeSaved;
	}

	private ModelYear buildModelDetails(VehicleModel vehicle, com.dmcliver.performancecars.domain.Model modelToBeSaved, int year, String fileName) {
		
		String filePath = emptyStr;
		try {
			filePath = fileUtils.getImageFilePathFromProperties("resources.properties", "resource_files.url");
		}
		catch (IOException ex) {
			logger.error("Couldnt retrieve file url", ex);
		}
		
		ModelYear modelYear = new ModelYear(modelToBeSaved, vehicle.getEngineSize(), new ModelYearPK(vehicle.getModelName(), year)) ;
		modelYear.setEngineAspiration(valueOf(vehicle.getEngineAspiration()));
		modelYear.setEngineType(EngineType.valueOf(vehicle.getEngineType()));
		modelYear.setQuarterMileTime(vehicle.getQuarterMileTime());
		modelYear.setTimeToOneHundred(vehicle.getTimeToOneHundred());
		modelYear.setFilePath(filePath + fileName);
		return modelYear;
	}
}