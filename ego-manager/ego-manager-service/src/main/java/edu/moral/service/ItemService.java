package edu.moral.service;

import java.util.List;

import edu.moral.bean.EUDataGridResult;
import edu.moral.pojo.TbItem;
import edu.moral.pojo.TbItemDesc;

public interface ItemService {
	
	public EUDataGridResult getItemList(int page,int rows) throws Exception;
	
	public void saveItem(TbItem item,String desc,String itemParams)throws Exception;
	
	public TbItemDesc getItemDesc(Long id) throws Exception;
	
	public void updateItem(TbItem item,String desc,String itemParams)throws Exception;
	
	public void deleteItems(List<Long> ids) throws Exception;
}
