package edu.moral.service;

import org.springframework.web.multipart.MultipartFile;

import edu.moral.bean.PictureResult;

public interface PictureService {

	public PictureResult uploadFile(MultipartFile file) throws Exception;
	
}
