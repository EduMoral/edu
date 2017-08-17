package edu.moral.service;

import java.util.List;

import edu.moral.bean.EUTreeNode;

public interface ItemCatService {

	public List<EUTreeNode> getItemCatList(Long parentId) throws Exception;
}
