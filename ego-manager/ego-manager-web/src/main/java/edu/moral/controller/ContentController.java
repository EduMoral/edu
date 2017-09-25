package edu.moral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.moral.bean.EUDataGridResult;
import edu.moral.bean.EgoResult;
import edu.moral.pojo.TbContent;
import edu.moral.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	
	@Resource
	private ContentService contentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult queryContentList(Long categoryId,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="30")Integer rows){
		EUDataGridResult result = null;
		
		try {
			result = contentService.getContentList(categoryId, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public EgoResult saveContent(TbContent content){
		try {
			contentService.saveContent(content);
		} catch (Exception e) {
			e.printStackTrace();
			return EgoResult.build(500, "insert error");
		}
		
		return EgoResult.ok();
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public EgoResult eidtContent(TbContent content){
		try {
			contentService.editContent(content);
		} catch (Exception e) {
			e.printStackTrace();
			return EgoResult.build(500, "edit error");
		}
		return EgoResult.ok();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public EgoResult removeContent(long[] ids){
		try {
			contentService.deleteContents(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return EgoResult.build(500, "delete error");
		}
		return EgoResult.ok();
	}
}
