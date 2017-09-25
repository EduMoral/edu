package edu.moral.rest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.moral.bean.EgoResult;
import edu.moral.mapper.TbContentMapper;
import edu.moral.pojo.TbContent;
import edu.moral.pojo.TbContentExample;
import edu.moral.rest.service.ContentService;

@Service("contentService")
public class ContentServiceImpl implements ContentService{

	@Resource
	private TbContentMapper contentMapper;
	
	@Override
	public EgoResult getContentList(long id) throws Exception {
		try {
			TbContentExample example = new TbContentExample();
			edu.moral.pojo.TbContentExample.Criteria criteria = example.createCriteria();
			criteria.andCategoryIdEqualTo(id);
			List<TbContent> list = contentMapper.selectByExample(example);
			
			return EgoResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}
}
