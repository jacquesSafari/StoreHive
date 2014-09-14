package com.storehive.app.utilities.factory;

import java.util.HashMap;

import com.storehive.app.domain.StoreOwner;

public class ApplicationFactory {

	public static StoreOwner buildStoreOwner(HashMap<String,String> ownerDetails){
		StoreOwner s = new StoreOwner();
		s.setName(ownerDetails.get("name"));
		s.setSurname(ownerDetails.get("surname"));
		s.setPassword(ownerDetails.get("password"));
		s.setUsername(ownerDetails.get("username"));
		s.setDeviceID(ownerDetails.get("deviceID"));
		s.setRegistrationDate(ownerDetails.get("registrationDate"));
		
		return s;
	}
}
