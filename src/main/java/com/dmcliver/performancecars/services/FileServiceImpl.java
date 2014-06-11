package com.dmcliver.performancecars.services;

import static com.dmcliver.performancecars.StringExtras.isNullOrEmpty;

import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dmcliver.performancecars.FileUtilities;
import com.dmcliver.performancecars.builders.LoggerBuilder;

@Service
public class FileServiceImpl implements FileService {
	
	private FileUtilities fileUtils;
	private Logger logger;

	@Autowired
	public FileServiceImpl(FileUtilities fileUtils, LoggerBuilder logBuilder) {
		this.fileUtils = fileUtils;
		logger = logBuilder.build(FileServiceImpl.class);
	}
	
	public void saveFile(MultipartFile pic) {
		
		OutputStream fileStream = null;
		try {
			
			byte[] bytes = pic.getBytes();
			final String name = pic.getOriginalFilename();
			
			if(isNullOrEmpty(name)) return;

			String path = fileUtils.getImageFilePathFromProperties("resources.properties", "resource_files.dir");
			String fullpath = path + name;
			
			if(fileUtils.fileExists(fullpath));
				fileUtils.deleteFile(fullpath);
			fileUtils.createNewFile(fullpath);
			
			fileStream = fileUtils.getFileOutputSteam(fullpath);
			fileStream.write(bytes);
		} 
		catch (IOException ex) {
			logger.error("Could not write image file to disk", ex);
		}
		catch(Exception ex){
			logger.error("File resource error", ex);
		}
		finally{
			
			if(fileStream != null) {
				
				try {
					fileStream.close();
				} 
				catch (IOException ex) {
					logger.warn("There was trouble trying to close file stream when writing image file to disk", ex);
				}
			}
		}
	}
}