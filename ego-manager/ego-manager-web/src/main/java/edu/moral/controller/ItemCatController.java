package edu.moral.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.moral.bean.EUTreeNode;
import edu.moral.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	
	@Resource
	private ItemCatService itemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getItemCatList(@RequestParam(value="id", defaultValue="0")Long parentId) {
		List<EUTreeNode> list = null;
		try {
			list = itemCatService.getItemCatList(parentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
