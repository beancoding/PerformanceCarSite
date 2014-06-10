package com.dmcliver.performancecars;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ResourceFileUtils {

	String getImageFilePathFromProperties(String propertiesFileName, Object propKey) throws FileNotFoundException, IOException;
}
