package edu.moral.service;

import edu.moral.bean.EUDataGridResult;
import edu.moral.pojo.TbContent;

public interface ContentService {

	public EUDataGridResult getContentList(long catId,int page,int rows) throws Exception;
	
	public void saveContent(TbContent content)throws Exception;
	
	public void editContent(TbContent content)throws Exception;
	
	public void deleteContents(long[] ids) throws Exception;
}
