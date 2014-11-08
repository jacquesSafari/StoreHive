package main.java.com.storehive.application.domain;

public class Items {
	
	private String id;
	private String itemName;
	private Category itemCategory;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Category getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(Category itemCategory) {
		this.itemCategory = itemCategory;
	}
	
	@Override
	public String toString() {
		return "Items [id=" + id + ", itemName=" + itemName + ", itemCategory="
				+ itemCategory + "]";
	}
	
	
}
