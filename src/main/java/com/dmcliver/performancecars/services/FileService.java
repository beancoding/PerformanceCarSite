package com.dmcliver.performancecars.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	void saveFile(MultipartFile pic);
}