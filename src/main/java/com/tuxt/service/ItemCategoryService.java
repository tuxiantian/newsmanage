package com.tuxt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuxt.dao.ItemCategoryDao;
import com.tuxt.domain.ItemCategorieInfo;

@Service
public class ItemCategoryService {
	@Autowired
	private ItemCategoryDao itemcategorydao;
	public   void saveOrder(String[] order)
	{
		itemcategorydao.saveOrder(order);
	}
	public  String GetCategoryOption(int categoryId, int noCategoryId) {
		return itemcategorydao.GetCategoryOption(categoryId, noCategoryId);
	}
	public ItemCategorieInfo getItemCategorieInfo(Integer id)
	{
		return itemcategorydao.getItemCategorieInfo(id);
	}
	public  void save(ItemCategorieInfo categorieInfo) {
		itemcategorydao.save(categorieInfo);
	}
	public  void update(ItemCategorieInfo categorieInfo) {
		itemcategorydao.update(categorieInfo);
	}
}
