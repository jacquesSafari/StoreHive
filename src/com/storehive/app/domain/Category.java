package com.storehive.app.domain;

public class Category {
	
	private String id;
	private String categoryName;
	private String categoryDescription;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName
				+ ", categoryDescription=" + categoryDescription + "]";
	}
	
	
}
