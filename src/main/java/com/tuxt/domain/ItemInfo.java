package com.tuxt.domain;


public class ItemInfo {
	private int itemid;
	private String detail;
    private int  status, deleteFlag;
    private String title;
    private ItemCategorieInfo c;

   
	public String getDetail() {
		return detail;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ItemCategorieInfo getC() {
		return c;
	}
	public void setC(ItemCategorieInfo c) {
		this.c = c;
	}
	
    
}
