package edu.moral.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import edu.moral.bean.EUDataGridResult;
import edu.moral.mapper.TbContentMapper;
import edu.moral.pojo.TbContent;
import edu.moral.pojo.TbContentExample;
import edu.moral.pojo.TbContentExample.Criteria;
import edu.moral.service.ContentService;

@Service("contentService")
public class ContentServiceImpl implements ContentService {

	@Resource
	private TbContentMapper contentMapper;

	@Override
	public EUDataGridResult getContentList(long catId, int page, int rows) throws Exception {
		try {
			// 根据category_id查询内容列表
			TbContentExample example = new TbContentExample();
			Criteria criteria = example.createCriteria();
			criteria.andCategoryIdEqualTo(catId);
			// 分页处理
			PageHelper.startPage(page, rows);
			List<TbContent> list = contentMapper.selectByExample(example);
			// 取分页信息
			PageInfo<TbContent> pageInfo = new PageInfo<>(list);
			EUDataGridResult result = new EUDataGridResult();
			result.setTotal(pageInfo.getTotal());
			result.setRows(list);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public void saveContent(TbContent content) throws Exception {
		try {
			Date date = new Date();
			content.setCreated(date);
			content.setUpdated(date);
			contentMapper.insert(content);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public void editContent(TbContent content) throws Exception {
		try {
			Date date = new Date();
			content.setUpdated(date);
			TbContentExample example = new TbContentExample();
			Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo(content.getId());
			
			contentMapper.updateByExampleSelective(content, example);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public void deleteContents(long[] ids) throws Exception {
		try {
			for(long id : ids){
				contentMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

}
