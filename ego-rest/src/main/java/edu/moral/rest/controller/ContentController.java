package edu.moral.rest.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.moral.bean.EgoResult;
import edu.moral.rest.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Resource
	private ContentService contentService;
	
	@RequestMapping("/category/{cid}")
	@ResponseBody
	public EgoResult getContentList(@PathVariable long cid){
		EgoResult result = null;
		try {
			result = contentService.getContentList(cid);
		} catch (Exception e) {
			e.printStackTrace();
			return EgoResult.build(500, e.getMessage());
		}
		return result;
	}

}
