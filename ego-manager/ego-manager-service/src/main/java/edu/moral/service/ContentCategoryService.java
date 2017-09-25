package edu.moral.service;

import java.util.List;

import edu.moral.bean.EUTreeNode;
import edu.moral.bean.EgoResult;

public interface ContentCategoryService {
	
	public List<EUTreeNode> getContentCategotyList(long parentId) throws Exception;
	
	public EgoResult addNode(long parentId,String name) throws Exception;
	
	public EgoResult updateNode(long id,String name) throws Exception;
	
	public EgoResult deleteNode(Long parentId,long id) throws Exception;
}
