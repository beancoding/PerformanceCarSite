package com.dmcliver.performancecars;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public interface FileUtilities {

	String getImageFilePathFromProperties(String propertiesFileName, Object propKey) throws FileNotFoundException, IOException;
	boolean fileExists(String pathName);
	boolean deleteFile(String pathName);
	boolean createNewFile(String pathName) throws IOException;
	OutputStream getFileOutputSteam(String pathName) throws FileNotFoundException;
}
