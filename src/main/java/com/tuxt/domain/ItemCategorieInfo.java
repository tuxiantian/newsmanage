package com.tuxt.domain;


public class ItemCategorieInfo {
	private int id;
	private ItemCategorieInfo parent;
	private String categoryName;
	private int treeLevel;
	private int orderBy;
	private String categoryMark;
	private int deleteFlag;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public ItemCategorieInfo getParent() {
		return parent;
	}
	public void setParent(ItemCategorieInfo parent) {
		this.parent = parent;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getTreeLevel() {
		return treeLevel;
	}
	public void setTreeLevel(int treeLevel) {
		this.treeLevel = treeLevel;
	}
	
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	public String getCategoryMark() {
		return categoryMark;
	}
	public void setCategoryMark(String categoryMark) {
		this.categoryMark = categoryMark;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
