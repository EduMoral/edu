package edu.moral.service;

import edu.moral.bean.EUDataGridResult;
import edu.moral.pojo.TbItem;

public interface ItemService {
	
	public EUDataGridResult getItemList(int page,int rows) throws Exception;
	
	public void saveItem(TbItem item,String desc,String itemParams)throws Exception;
}
