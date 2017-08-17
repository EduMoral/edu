package edu.moral.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.moral.bean.EUDataGridResult;
import edu.moral.bean.EgoResult;
import edu.moral.pojo.TbItem;
import edu.moral.pojo.TbItemDesc;
import edu.moral.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Resource
	private ItemService itemService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(@RequestParam(defaultValue="1")Integer page, @RequestParam(defaultValue="30")Integer rows) {
		EUDataGridResult result = null;
		try {
			result = itemService.getItemList(page, rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public EgoResult saveItem(TbItem item, String desc) {
		// 添加商品信息
		try {
			itemService.saveItem(item, desc, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EgoResult.ok();
	}
	
	@RequestMapping("/edit")
	public String edit(){
		return "item-edit";
	}
	
	@RequestMapping("/query/desc/{id}")
	@ResponseBody
	public TbItemDesc getItemDesc(@PathVariable Long id){
		TbItemDesc itemDesc = null;
		try {
			itemDesc = itemService.getItemDesc(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemDesc;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public EgoResult updateItem(TbItem item,String desc){
		try {
			itemService.updateItem(item, desc, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EgoResult.ok();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public EgoResult deletItems(Long[] ids){
		
		if(ids.length==0){
			return EgoResult.ok();
		}
		List<Long> idList = new ArrayList<>();
		for(Long id : ids){
			idList.add(id);
		}
		
		try {
			itemService.deleteItems(idList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EgoResult.ok();
	}
}
