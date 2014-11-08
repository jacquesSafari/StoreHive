package main.java.com.storehive.application.domain;

import java.util.List;

public class ShoppingList {

	private String id;
	private String dateCreated;
	private boolean allObjectsFound;
	private List<Items> items;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public boolean isAllObjectsFound() {
		return allObjectsFound;
	}
	public void setAllObjectsFound(boolean allObjectsFound) {
		this.allObjectsFound = allObjectsFound;
	}
	public List<Items> getItems() {
		return items;
	}
	public void setItems(List<Items> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "ShoppingList [id=" + id + ", dateCreated=" + dateCreated
				+ ", allObjectsFound=" + allObjectsFound + ", items=" + items
				+ "]";
	}
	
	
	
}
