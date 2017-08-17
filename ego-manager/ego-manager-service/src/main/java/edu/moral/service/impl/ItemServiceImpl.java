package edu.moral.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import edu.moral.bean.EUDataGridResult;
import edu.moral.mapper.TbItemDescMapper;
import edu.moral.mapper.TbItemMapper;
import edu.moral.pojo.TbItem;
import edu.moral.pojo.TbItemDesc;
import edu.moral.pojo.TbItemDescExample;
import edu.moral.pojo.TbItemExample;
import edu.moral.pojo.TbItemExample.Criteria;
import edu.moral.service.ItemService;
import edu.moral.utils.IDUtils;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Resource
	private TbItemMapper itemMapper;
	
	@Resource
	private TbItemDescMapper itemDescMapper;

	/**
	 * 商品列表查询
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) throws Exception {

		try {
			// 查询商品列表
			TbItemExample example = new TbItemExample();
			// 分页处理
			PageHelper.startPage(page, rows);
			List<TbItem> list = itemMapper.selectByExample(example);
			// 创建一个返回值对象
			EUDataGridResult result = new EUDataGridResult();
			result.setRows(list);
			// 取记录总条数
			PageInfo<TbItem> pageInfo = new PageInfo<>(list);
			result.setTotal(pageInfo.getTotal());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public void saveItem(TbItem item, String desc, String itemParams) throws Exception {
		try {
			Date date = new Date();
			//获得商品id
			long id = IDUtils.genItemId();
			//添加商品信息
			item.setId(id);
			//商品状态，1-正常，2-下架，3-删除
			item.setStatus((byte) 1);
			item.setCreated(date);
			item.setUpdated(date);
			itemMapper.insert(item);
			//添加商品描述
			//创建TbItemDesc对象
			TbItemDesc itemDesc = new TbItemDesc();
			//获得一个商品id
			itemDesc.setItemId(id);
			itemDesc.setItemDesc(desc);
			itemDesc.setCreated(date);
			itemDesc.setUpdated(date);
			//插入数据
			itemDescMapper.insert(itemDesc);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public TbItemDesc getItemDesc(Long id) throws Exception {
		TbItemDesc itemDesc = null;
		try {
			itemDesc = itemDescMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
		return itemDesc;
	}

	@Override
	public void updateItem(TbItem item, String desc, String itemParams) throws Exception {
		try {
			Date date = new Date();
			item.setUpdated(date);
			TbItemExample example = new TbItemExample();
			Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo(item.getId());
			itemMapper.updateByExampleSelective(item, example);
			//添加商品描述
			//创建TbItemDesc对象
			TbItemDesc itemDesc = new TbItemDesc();
			//获得一个商品id
			itemDesc.setItemDesc(desc);
			itemDesc.setUpdated(date);
			TbItemDescExample descExample = new TbItemDescExample();
			edu.moral.pojo.TbItemDescExample.Criteria descCriteria = descExample.createCriteria();
			descCriteria.andItemIdEqualTo(item.getId());
			//插入数据
			itemDescMapper.updateByExampleSelective(itemDesc, descExample);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public void deleteItems(List<Long> ids) throws Exception {
		try {
			TbItemDescExample descExample = new TbItemDescExample();
			edu.moral.pojo.TbItemDescExample.Criteria descCriteria = descExample.createCriteria();
			descCriteria.andItemIdIn(ids);
			itemDescMapper.deleteByExample(descExample);
			
			for(Long id : ids){
				itemMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

}
