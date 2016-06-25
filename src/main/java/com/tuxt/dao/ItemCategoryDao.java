package com.tuxt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.stereotype.Repository;

import cn.itcast.jdbc.TxQueryRunner;

import com.tuxt.domain.ItemCategorieInfo;
import com.tuxt.utils.CommonUtils;

@Repository
public class ItemCategoryDao {
	
	private  QueryRunner qr = new TxQueryRunner();
	public  List<ItemCategorieInfo> GetCategoryTree()
	{
		List<ItemCategorieInfo> olsCategoryTree=null;
		String sql = "select * from ItemCategories where DeleteFlag=0 order by OrderBy asc,Id asc";
		try {
			olsCategoryTree=toItemCategorieInfoList(qr.query(sql, new MapListHandler()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SortCategoryTree(olsCategoryTree);
	}
	public ItemCategorieInfo getItemCategorieInfo(Integer id)
	{
		String sql="select * from itemcategories where id="+id;
		ItemCategorieInfo itemCategorieInfo=null;
		try {
			itemCategorieInfo=toItemCategorieInfo(qr.query( sql, new MapHandler()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemCategorieInfo;
	}
	private  ItemCategorieInfo toItemCategorieInfo(Map<String,Object> map) {
		ItemCategorieInfo category = CommonUtils.toBean(map, ItemCategorieInfo.class);
		int pid = (Integer) map.get("pid");// 如果是一级分类，那么pid是null
		if(pid != 0) {//如果父分类ID不为空，
			ItemCategorieInfo parent = new ItemCategorieInfo();
			parent.setId(pid);
			category.setParent(parent);
		}
		return category;
	}
	public  List<ItemCategorieInfo> toItemCategorieInfoList(List<Map<String,Object>> mapList)
	{
		List<ItemCategorieInfo> categoryList = new ArrayList<ItemCategorieInfo>();//创建一个空集合
		for(Map<String,Object> map : mapList) {//循环遍历每个Map
			ItemCategorieInfo c = toItemCategorieInfo(map);//把一个Map转换成一个Category
			categoryList.add(c);//添加到集合中
		}
		return categoryList;//返回集合
	}
	public  List<ItemCategorieInfo> FindRootCategoryList(List<ItemCategorieInfo> categoryTree)
	{
		List<ItemCategorieInfo> root = null;
		if (categoryTree!=null) {
			root = new ArrayList<ItemCategorieInfo>();
			for (ItemCategorieInfo itemCategorieInfo : categoryTree) {
				if (itemCategorieInfo.getParent() == null) {
					root.add(itemCategorieInfo);
				}
			}
		}
		return root;
	}
	public  List<ItemCategorieInfo> SortCategoryTree(List<ItemCategorieInfo> categorySorce)
	{
		List<ItemCategorieInfo> olsCategoryTree = new ArrayList<ItemCategorieInfo>();
		List<ItemCategorieInfo> olsRootCategory=FindRootCategoryList(categorySorce);
		if (olsRootCategory!=null) {
			for (ItemCategorieInfo itemCategorieInfo : olsRootCategory) {
				PushCategoryTree(olsCategoryTree, categorySorce, itemCategorieInfo, 0);
			}
		}
		return olsCategoryTree;
	}
	public  List<ItemCategorieInfo> FindChildCategoryTree(List<ItemCategorieInfo> categorySorce,int categoryId)
	{
		List<ItemCategorieInfo> childList=new ArrayList<ItemCategorieInfo>();
		for (ItemCategorieInfo itemCategorieInfo : categorySorce) {
			if (itemCategorieInfo.getParent()!=null&&itemCategorieInfo.getParent().getId()==categoryId) {
				childList.add(itemCategorieInfo);
			}
		}
		return childList;
	}
	public   void PushCategoryTree(List<ItemCategorieInfo> categoryTree, List<ItemCategorieInfo> categorySorce, ItemCategorieInfo category, int tree)
	{
		category.setTreeLevel(tree+1);
		categoryTree.add(category);
		List<ItemCategorieInfo> olsChildCategory=FindChildCategoryTree(categorySorce, category.getId());
		for (ItemCategorieInfo child : olsChildCategory) {
			PushCategoryTree(categoryTree, categorySorce, child, category.getTreeLevel());
		}
	}
	/**
	 * 保存设定次序后的类别
	 * @param order
	 */
	public   void saveOrder(String[] order)
	{
		int i=0;
		String strSql;
		List<ItemCategorieInfo> olsInfos = GetCategoryTree();

		for (ItemCategorieInfo info : olsInfos)
		{
			strSql = "update itemcategories set OrderBy = " + order[i] + " where Id=" + info.getId();
			try {
				qr.update(strSql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			i++;
		}
	}
	/**
	 * 获取类别的下拉列表，不同级别的类别名显示不同的颜色；且类别编号等于
	 * categoryId的类别处于选中状态;当修改类别编号时，
	 * 编号等于noCategoryId的类别不在类别树中
	 * @param categoryId
	 * @param noCategoryId
	 * @return
	 */
	public  String GetCategoryOption(int categoryId, int noCategoryId) {
		StringBuffer strRtn=new StringBuffer();
		int intIID, intTreeLevel;
		//获取类别树集合
		List<ItemCategorieInfo> olsCategoryTree = GetCategoryTree();
		for (ItemCategorieInfo category : olsCategoryTree) {
			if (category.getId()==noCategoryId) {
				continue;
			}
			intTreeLevel=category.getTreeLevel();
			intIID=category.getId();
			strRtn.append("<option value=" + intIID);
			if (intTreeLevel == 1)
				strRtn.append("");
			else if (intTreeLevel == 2)
				strRtn.append(" class=red");
			else if (intTreeLevel == 3)
				strRtn.append( " class=blue");
			else if (intTreeLevel == 4)
				strRtn.append(" class=green");
			else{}
			if (intIID == categoryId)
				strRtn.append(" selected");
			strRtn.append( ">");
			strRtn.append(CommonUtils.GetRepeatSign("&nbsp;&nbsp;&nbsp;&nbsp;", (intTreeLevel - 1)));
			if (category.getParent()!=null) {
				strRtn.append("┣ ");
			}
			strRtn.append(category.getCategoryName()+ "</option>");

		}
		return strRtn.toString();
	}
	public  void save(ItemCategorieInfo categorieInfo) {
		String sql="insert into itemcategories (pid,categoryName,deleteFlag) values(?,?,?)";
		try {
			qr.update(sql, categorieInfo.getParent().getId(),categorieInfo.getCategoryName(),0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public  void update(ItemCategorieInfo categorieInfo) {
		String sql="update itemcategories set pid=?,categoryName=? where id=?";
		try {
			qr.update( sql, categorieInfo.getParent().getId(),categorieInfo.getCategoryName(),categorieInfo.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
