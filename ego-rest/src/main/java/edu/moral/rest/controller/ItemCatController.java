package edu.moral.rest.controller;

import javax.annotation.Resource;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.moral.rest.pojo.ItemCatResult;
import edu.moral.rest.service.ItemCatService;

@Controller
@RequestMapping("/item")
public class ItemCatController {

	@Resource
	private ItemCatService itemCatService;
	
	@RequestMapping("/all")
	@ResponseBody
	public MappingJacksonValue queryCategory(String callback){
		MappingJacksonValue jacksonValue = null;
		try {
			ItemCatResult result = itemCatService.queryCategory();
			
			jacksonValue = new MappingJacksonValue(result);
			jacksonValue.setJsonpFunction(callback);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jacksonValue;
	}
}
