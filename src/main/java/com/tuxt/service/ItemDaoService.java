package com.tuxt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuxt.dao.ItemDao;
import com.tuxt.domain.ItemInfo;

@Service
public class ItemDaoService {
	@Autowired
	private ItemDao itemdao;
	public void save(ItemInfo itemInfo) {
		itemdao.save(itemInfo);
	}
	public void update(ItemInfo itemInfo) {
		itemdao.update(itemInfo);
	}
	public List<ItemInfo> getItemInfos() {
	return	itemdao.getItemInfos();
	}
	public List<ItemInfo> searchitems(String condition){
		return	itemdao.searchitems(condition);
	}
	public ItemInfo get(Integer id) {
		return itemdao.get(id);
	}
	public void delete(Integer id) {
		itemdao.delete(id);
	}
}
