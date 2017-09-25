package edu.moral.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.moral.mapper.TbItemCatMapper;
import edu.moral.pojo.TbItemCat;
import edu.moral.pojo.TbItemCatExample;
import edu.moral.pojo.TbItemCatExample.Criteria;
import edu.moral.rest.pojo.ItemCat;
import edu.moral.rest.pojo.ItemCatResult;
import edu.moral.rest.service.ItemCatService;


@Service("itemCatService")
public class ItemCatServiceImpl implements ItemCatService {

	@Resource
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public ItemCatResult queryCategory() throws Exception {
		
		try {
			ItemCatResult result = new ItemCatResult();
			result.setData(getItemCatList(0L));
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	private List<?> getItemCatList(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		//查询parentid为0的分类信息
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<Object> dataList = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			//判断是否为父节点
			if (tbItemCat.getIsParent()) {
				ItemCat itemCat = new ItemCat();
				itemCat.setUrl("/category/" + tbItemCat.getId() + ".html");
				itemCat.setName(tbItemCat.getName());
				//递归调用
				itemCat.setItem(getItemCatList(tbItemCat.getId()));
				//添加到列表
				dataList.add(itemCat);
			} else {
				String catItem = "/item/" + tbItemCat.getId() + ".html|" + tbItemCat.getName();
				dataList.add(catItem);
			}
		}
		return dataList;
	}

	

}
