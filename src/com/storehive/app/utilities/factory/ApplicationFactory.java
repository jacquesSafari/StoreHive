package com.storehive.app.utilities.factory;

import com.mongodb.BasicDBObject;
import com.storehive.app.domain.Category;
import com.storehive.app.domain.Location;
import com.storehive.app.domain.Products;
import com.storehive.app.domain.Store;
import com.storehive.app.domain.StoreOwner;

public class ApplicationFactory {

	public static StoreOwner buildStoreOwner(BasicDBObject ownerDetails){
		StoreOwner s = new StoreOwner();
		s.setName(ownerDetails.get("name").toString());
		s.setSurname(ownerDetails.get("surname").toString());
		s.setPassword(ownerDetails.get("password").toString());
		s.setUsername(ownerDetails.get("username").toString());
		s.setDeviceID(ownerDetails.get("deviceId").toString());
		s.setRegistrationDate(ownerDetails.get("registrationDate").toString());
		
		return s;
	}
	
	public static Category buildCategory(BasicDBObject ownerDetails){
		Category s = new Category();
		s.setId(ownerDetails.getObjectId("_id").toString());
		s.setCategoryName(ownerDetails.get("categoryName").toString());
		s.setCategoryDescription(ownerDetails.get("categoryDescription").toString());
		return s;
	}
	
	public static Store buildStore(BasicDBObject storeDetails){
		Store s = new Store();
		s.setId(storeDetails.getObjectId("_id").toString());
		s.setDescription(storeDetails.getString("description"));
		s.setLastOpenedDate(storeDetails.getString("lastOpenedDate"));
		s.setOwnerEmail(storeDetails.getString("ownerEmail"));
		s.setShopName(storeDetails.getString("shopName"));
		s.setOpen(storeDetails.getBoolean("isOpen"));
		s.setLocation(buildLocation((BasicDBObject)storeDetails.get("location")));
		return s;
	}
	
	private static Location buildLocation(BasicDBObject locationDetails){
		Location l = new Location();
		l.setLatitude(locationDetails.getString("latitude"));
		l.setLongitude(locationDetails.getString("longitude"));
		
		return l;
	}
	
	public static Products buildProduct(BasicDBObject productDetails){
		Products p = new Products();
		p.setId(productDetails.getObjectId("_id").toString());
		p.setProductName(productDetails.getString("productName"));
		p.setPrice(productDetails.getString("price"));
		p.setDescription(productDetails.getString("description"));
		p.setCategory(buildCategory((BasicDBObject)productDetails.get("category")));
		return p;
	}
}
