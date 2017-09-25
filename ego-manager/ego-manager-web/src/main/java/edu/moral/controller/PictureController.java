package edu.moral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.moral.bean.PictureResult;
import edu.moral.service.PictureService;

@Controller
@RequestMapping("/pic")
public class PictureController {

	@Resource
	private PictureService pictureService;
	
	@RequestMapping("/upload")
	@ResponseBody
	public PictureResult upload(MultipartFile uploadFile){
		//调用service上传图片
		PictureResult pictureResult = null;
		try {
			pictureResult = pictureService.uploadFile(uploadFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回上传结果
		return pictureResult;
		
	}
}
