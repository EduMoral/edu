package edu.moral.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.moral.bean.EUTreeNode;
import edu.moral.bean.EgoResult;
import edu.moral.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Resource
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContentCategoryList(@RequestParam(value="id", defaultValue="0")long parentid){
		List<EUTreeNode> list = null;
		try {
			list = contentCategoryService.getContentCategotyList(parentid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public EgoResult appendNode(long parentId,String name){
		EgoResult result = null;
		try {
			result = contentCategoryService.addNode(parentId, name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	@RequestMapping("/update")
	@ResponseBody
	public EgoResult renameNode(long id,String name){
		EgoResult result = null;
		try {
			result = contentCategoryService.updateNode(id, name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public EgoResult removeNode(Long parentId,long id){
		EgoResult result = null;
		try {
			result = contentCategoryService.deleteNode(parentId, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
