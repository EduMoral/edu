package edu.moral.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.moral.bean.EUTreeNode;
import edu.moral.mapper.TbItemCatMapper;
import edu.moral.pojo.TbItemCat;
import edu.moral.pojo.TbItemCatExample;
import edu.moral.pojo.TbItemCatExample.Criteria;
import edu.moral.service.ItemCatService;


@Service("itemCatService")
public class ItemCatServiceImpl implements ItemCatService {

	@Resource
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<EUTreeNode> getItemCatList(Long parentId) throws Exception {
		try {
			TbItemCatExample example = new TbItemCatExample();
			Criteria criteria = example.createCriteria();

			criteria.andParentIdEqualTo(parentId);
			// 执行查询
			List<TbItemCat> list = itemCatMapper.selectByExample(example);
			// 转换成EasyUITreeNode列表
			List<EUTreeNode> resultList = new ArrayList<>();
			for (TbItemCat tbItemCat : list) {
				// 创建一个节点对象
				EUTreeNode node = new EUTreeNode();
				node.setId(tbItemCat.getId());
				node.setText(tbItemCat.getName());
				node.setState(tbItemCat.getIsParent() ? "closed" : "open");
				// 添加到列表中
				resultList.add(node);
			}
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

}
