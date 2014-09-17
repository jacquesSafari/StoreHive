package com.storehive.app.utilities.factory;

import com.mongodb.BasicDBObject;
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
}
