package com.tuxt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.stereotype.Repository;

import cn.itcast.jdbc.TxQueryRunner;

import com.mysql.jdbc.Connection;
import com.tuxt.domain.ItemCategorieInfo;
import com.tuxt.domain.ItemInfo;
import com.tuxt.utils.CommonUtils;
import com.tuxt.utils.DBUtil;

@Repository
public class ItemDao {

	private QueryRunner qr = new TxQueryRunner();
	public void save(ItemInfo itemInfo) {
		String sql = "insert into items (title,detail,category,status,deleteFlag) values(?,?,?,?,?)";
		try {
			qr.update(sql,itemInfo.getTitle(),itemInfo.getDetail(),itemInfo.getC().getId(),itemInfo.getStatus(),itemInfo.getDeleteFlag());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void update(ItemInfo itemInfo) {
		String sql = "update items set title=? ,detail=? ,category=? where itemid=?";
		try {
			qr.update(sql,itemInfo.getTitle(),itemInfo.getDetail(),itemInfo.getC().getId(),itemInfo.getItemid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public List<ItemInfo> getItemInfos() {
		List<ItemInfo> items=new ArrayList<ItemInfo>();
		String sql="select i.itemid,i.title,i.category,i.detail,i.status,i.deleteflag,"
				+ "c.id,c.pid,c.categoryName,c.orderBy,c.categoryMark,c.deleteFlag"
				+ " from items i,itemcategories c where i.category=c.id and i.deleteflag=0";
		try {
			List<Map<String, Object>> maps=qr.query(sql, new MapListHandler());
			for (Map<String, Object> map : maps) {
				ItemInfo itemInfo= CommonUtils.toBean(map,ItemInfo.class);
				ItemCategorieInfo categorieInfo=CommonUtils.toBean(map, ItemCategorieInfo.class);
				itemInfo.setC(categorieInfo);
				items.add(itemInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
	public List<ItemInfo> searchitems(String condition){
		List<ItemInfo> items=new ArrayList<ItemInfo>();
		String sql="select i.itemid,i.title,i.category,i.detail,i.status,i.deleteflag,"
				+ "c.id,c.pid,c.categoryName,c.orderBy,c.categoryMark,c.deleteFlag"
				+ " from items i,itemcategories c where i.category=c.id and i.deleteflag=0 and i.title like '%"+condition+"%'";
		try {
			List<Map<String, Object>> maps=qr.query(sql, new MapListHandler());
			for (Map<String, Object> map : maps) {
				ItemInfo itemInfo= CommonUtils.toBean(map,ItemInfo.class);
				ItemCategorieInfo categorieInfo=CommonUtils.toBean(map, ItemCategorieInfo.class);
				itemInfo.setC(categorieInfo);
				items.add(itemInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
	public ItemInfo get(Integer id) {
		ItemInfo itemInfo=null;
		String sql="select * from items where itemid=?";
		try {
			itemInfo=qr.query(sql, new BeanHandler<ItemInfo>(ItemInfo.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemInfo;
	}
	public void delete(Integer id) {
		String sql="update items set deleteflag=1 where itemid=?";
		try {
			qr.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
