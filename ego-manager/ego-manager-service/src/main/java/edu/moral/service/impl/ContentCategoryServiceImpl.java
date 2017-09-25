package edu.moral.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.moral.bean.EUTreeNode;
import edu.moral.bean.EgoResult;
import edu.moral.mapper.TbContentCategoryMapper;
import edu.moral.pojo.TbContentCategory;
import edu.moral.pojo.TbContentCategoryExample;
import edu.moral.pojo.TbContentCategoryExample.Criteria;
import edu.moral.service.ContentCategoryService;

@Service("contentCategoryService")
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Resource
	private TbContentCategoryMapper contentCategoryMapper;
	
	
	@Override
	public List<EUTreeNode> getContentCategotyList(long parentId) throws Exception {
		try {
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(parentId);
			List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
			List<EUTreeNode> resultList = new ArrayList<>();
			for (TbContentCategory tbContentCategory : list) {
				EUTreeNode node = new EUTreeNode();
				node.setId(tbContentCategory.getId());
				node.setText(tbContentCategory.getName());
				// 判断是否是父节点
				if (tbContentCategory.getIsParent()) {
					node.setState("closed");
				} else {
					node.setState("open");
				}
				resultList.add(node);
			}
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}


	@Override
	public EgoResult addNode(long parentId,String name) throws Exception {
		Date date = new Date();
		//添加一个新节点
		//创建一个节点对象
		TbContentCategory node = new TbContentCategory();
		node.setName(name);
		node.setParentId(parentId);
		node.setIsParent(false);
		node.setCreated(date);
		node.setUpdated(date);
		node.setSortOrder(1);
		//状态。可选值:1(正常),2(删除)
		node.setStatus(1);
		//插入新节点。需要返回主键
		contentCategoryMapper.insert(node);
		//判断如果父节点的isparent不是true修改为true
		//取父节点的内容
		TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parentNode.getIsParent()) {
			parentNode.setIsParent(true);
			parentNode.setUpdated(date);
			contentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		//把新节点返回
		return EgoResult.ok(node);
	}


	@Override
	public EgoResult updateNode(long id, String name) throws Exception {
		try {
			Date date = new Date();
			TbContentCategory node = new TbContentCategory();
			node.setName(name);
			node.setUpdated(date);
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo(id);
			contentCategoryMapper.updateByExampleSelective(node, example);
			return EgoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}


	@Override
	public EgoResult deleteNode(Long parentId, long id) throws Exception {
		
		try {
			Date date = new Date();
			parentId = contentCategoryMapper.selectByPrimaryKey(id).getParentId();
			contentCategoryMapper.deleteByPrimaryKey(id);
			
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(parentId);
			int cnt = contentCategoryMapper.selectByExample(example).size();
			if(cnt == 0){
				example = new TbContentCategoryExample();
				criteria = example.createCriteria();
				criteria.andIdEqualTo(parentId);
				TbContentCategory record = new TbContentCategory();
				record.setIsParent(false);
				record.setUpdated(date);
				contentCategoryMapper.updateByExampleSelective(record, example);
			}
			
			return EgoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

}
