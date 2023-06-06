package com.masai.school.service.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.masai.school.service.FileService;

import lombok.Setter;
@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		//get File Name
		
		String name=file.getOriginalFilename();
		
		//pic.jpg
		String randomId=UUID.randomUUID().toString();
		String fileName1=randomId.concat(name.substring(name.lastIndexOf(".")));
		
		//fullPath
		
		String filePath=path+File.separator+fileName1;
		
		//create folder if not created
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		
		//copy File;
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String filenName) throws FileNotFoundException {
		
		String fullPath=path+File.separator+filenName;
		InputStream is=new FileInputStream(fullPath);
		return is;
	}

}
