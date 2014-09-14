package com.storehive.app.domain;

import java.util.List;

public class Store {

	private String id;
	private String ownerEmail;
	private String shopName;
	private String description;
	private String lastOpenedDAte;
	private double latitude;
	private double longitude;
	private boolean isOpen;
	private List<Products> products;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLastOpenedDAte() {
		return lastOpenedDAte;
	}
	public void setLastOpenedDAte(String lastOpenedDAte) {
		this.lastOpenedDAte = lastOpenedDAte;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public List<Products> getProducts() {
		return products;
	}
	public void setProducts(List<Products> products) {
		this.products = products;
	}
	
	
	
}
