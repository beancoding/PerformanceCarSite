package com.dmcliver.performancecars.services;

import static org.mockito.Mockito.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import com.dmcliver.performancecars.FileUtilities;
import com.dmcliver.performancecars.builders.LoggerBuilder;
import com.dmcliver.performancecars.services.FileServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class FileServiceTest {

	@Mock private FileUtilities utils;
	@Mock private MultipartFile pic;
	@Mock private OutputStream out;
	@Mock private LoggerBuilder logBuilder;

	@Test
	public void saveFile_FindsResourceContainingFilePath() throws IOException{
		
		final String fileName = "testFileName.ext";
		final String dir = "/Users/danielmcliver";
		final String path = dir + "/" + fileName;
		final byte[] stream = new byte[]{0,1,2,3};
		
		buildUtils(fileName, path, dir);
		
		buildMultipartType(fileName, stream);
		
		FileServiceImpl service = new FileServiceImpl(utils, logBuilder);
		service.saveFile(pic);
		
		verify(utils).deleteFile(path);
		verify(utils).createNewFile(path);
		verify(out).write(stream);
		verify(out).close();
	}

	private void buildUtils(final String fileName, final String path, String dir) throws FileNotFoundException, IOException {
		
		when(utils.getImageFilePathFromProperties(anyString(), anyString())).thenReturn(dir + "/");
		when(utils.fileExists(fileName)).thenReturn(true);
		when(utils.getFileOutputSteam(path)).thenReturn(out);
	}

	private void buildMultipartType(final String fileName, final byte[] stream) throws IOException {
		
		when(pic.getOriginalFilename()).thenReturn(fileName);
		when(pic.getBytes()).thenReturn(stream);
	}
}
