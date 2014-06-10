package com.dmcliver.performancecars;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class FileUtilitiesImpl implements FileUtilities, ResourceFileUtils {

	private final String forwardSlash = "\\";
	private final String backSlash = "/";
	
	@Override
	public String getImageFilePathFromProperties(String propertiesFileName, Object propKey) throws FileNotFoundException, IOException {

		Properties props = new Properties();
		props.load(new FileReader(propertiesFileName));
		String path = props.get(propKey).toString();
		
		return path.endsWith(backSlash) || path.endsWith(forwardSlash) ? path :
			   path.contains(forwardSlash) ? path + forwardSlash : path + backSlash;
	}

	@Override
	public boolean fileExists(String pathName) {

		return new File(pathName).exists();
	}

	@Override
	public boolean deleteFile(String pathName) {
		
		return new File(pathName).delete();
	}

	@Override
	public boolean createNewFile(String pathName) throws IOException {

		return new File(pathName).createNewFile();
	}

	@Override
	public OutputStream getFileOutputSteam(String pathName) throws FileNotFoundException {

		return new FileOutputStream(pathName);
	}

}
