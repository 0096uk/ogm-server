package com.greenmart.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

@Component
public class DiskStorageServiceImpl implements StorageService {
	@Value("${disk.upload.basepath}")
	private String BASEPATH;
	@Override
	public List<String> loadAll() {
		File dirPath = new File(BASEPATH);
		return List.of(dirPath.list());
	}

	@Override
	public String store(MultipartFile file) {
		String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
		File filePath = new File(BASEPATH, fileName);
		try(FileOutputStream out = new FileOutputStream(filePath)) {
			FileCopyUtils.copy(file.getInputStream(), out);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Resource load(String fileName) {
		File filePath = new File(BASEPATH, fileName);
		if(filePath.exists())
			return new FileSystemResource(filePath);
		return null;
	}

	@Override
	public void delete(String fileName) {
		File filePath = new File(BASEPATH, fileName);
		if(filePath.exists())
			filePath.delete();
	}

	@Override
	public String azstore(MultipartFile file) {
		
		String constr = "DefaultEndpointsProtocol=https;AccountName=azstorageogm;AccountKey=Ro4m7e18A0GIwNvRPDdF8RpEAZSTUuuiH9oqY7xrUvlbw9AG5V2Dz8UUCSuYcNYVK4ryT6+6eT6w+AStpgvDjg==;EndpointSuffix=core.windows.net";
		
		BlobContainerClient container = new BlobContainerClientBuilder().connectionString(constr).containerName("greenmartimages").buildClient();
		
		String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
		
		BlobClient blob = container.getBlobClient(fileName);
		
		try {
			blob.upload(file.getInputStream(), file.getSize(), true);
			return fileName;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}

