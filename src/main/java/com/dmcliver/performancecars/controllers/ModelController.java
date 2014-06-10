package com.dmcliver.performancecars.controllers;

import static com.dmcliver.performancecars.EnumMetadata.getTag;
import static com.dmcliver.performancecars.StringExtras.emptyStr;
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

import com.dmcliver.performancecars.LoggerBuilder;
import com.dmcliver.performancecars.ResourceFileUtils;
import com.dmcliver.performancecars.datalayer.MakeDAO;
import com.dmcliver.performancecars.datalayer.ModelDAO;
import com.dmcliver.performancecars.domain.EngineAspiration;
import com.dmcliver.performancecars.domain.EngineType;
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
	
	@RequestMapping(value = "/getAll/{makeName}", method = GET) 
	public @ResponseBody List<com.dmcliver.performancecars.domain.Model> getAll(@PathVariable("makeName") String makeName) {
		
		return modelDAO.findAll(makeName); 
	}
	
	@RequestMapping(value = "/add", method = POST)
	public String add(@Valid @ModelAttribute("vehicleModel") VehicleModel vehicle, BindingResult result, @RequestParam(value = "carPic", required = false) MultipartFile pic, Model model) {
	
		if(result.hasErrors())
			return "addModel";
		
		fileService.saveFile(pic);
		String filePath = emptyStr;
		try {
			filePath = fileUtils.getImageFilePathFromProperties("resources.properties", "resource_files.dir");
		} 
		catch (FileNotFoundException ex) {
			logger.warn("could not get resource props with key resource_files.dir", ex);
		} 
		catch (IOException ex) {
			logger.warn("could not get resource props with key resource_files.dir", ex);
		}
		
		return "redirect:/home/index";
	}
}