package main.java.com.storehive.application.utilities.factory;

import java.util.Date;

import main.java.com.storehive.application.domain.Storeowner;

import org.json.simple.JSONObject;


public class ApplicationFactory {

	public static Storeowner buildStoreOwner(JSONObject ownerDetails){
		Storeowner s = new Storeowner();
		s.setEmail(ownerDetails.get("email").toString());
		s.setPassword(ownerDetails.get("password").toString());
		s.setFullname(ownerDetails.get("fullname").toString());
		s.setDeviceId(ownerDetails.get("deviceId").toString());
		s.setRegisteredDate(new Date());
		
		return s;
	}
//	
//	public static Category buildCategory(BasicDBObject ownerDetails){
//		Category s = new Category();
//		s.setId(ownerDetails.getObjectId("_id").toString());
//		s.setCategoryName(ownerDetails.get("categoryName").toString());
//		s.setCategoryDescription(ownerDetails.get("categoryDescription").toString());
//		return s;
//	}
//	
//	public static Store buildStore(BasicDBObject storeDetails){
//		Store s = new Store();
//		s.setDescription(storeDetails.getString("description"));
//		s.setLastOpenedDate(storeDetails.getString("lastOpenedDate"));
//		s.setOwnerEmail(storeDetails.getString("ownerEmail"));
//		s.setShopName(storeDetails.getString("shopName"));
//		s.setOpen(storeDetails.getBoolean("isOpen"));
//		s.setLocation(buildLocation((BasicDBObject)storeDetails.get("location")));
//		return s;
//	}
//	
//	private static Location buildLocation(BasicDBObject locationDetails){
//		Location l = new Location();
//		l.setLatitude(locationDetails.getString("latitude"));
//		l.setLongitude(locationDetails.getString("longitude"));
//		
//		return l;
//	}
//	
//	public static Products buildProduct(BasicDBObject productDetails){
//		Products p = new Products();
//		p.setProductName(productDetails.getString("productName"));
//		p.setPrice(productDetails.getString("price"));
//		p.setDescription(productDetails.getString("description"));
//		p.setCategory(buildCategory((BasicDBObject)productDetails.get("category")));
//		return p;
//	}
}
