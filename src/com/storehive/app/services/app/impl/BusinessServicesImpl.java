package com.storehive.app.services.app.impl;

import java.net.UnknownHostException;
import java.util.HashMap;

import org.joda.time.DateTime;
import org.json.simple.JSONObject;

import com.storehive.app.domain.StoreOwner;
import com.storehive.app.services.app.BusinessServices;
import com.storehive.app.services.crud.StoreOwnerCrudService;
import com.storehive.app.services.crud.impl.StoreOwnerCrudServiceImpl;
import com.storehive.app.utilities.factory.ApplicationFactory;

public class BusinessServicesImpl implements BusinessServices{

	private StoreOwnerCrudService stcs;
	
	public BusinessServicesImpl() throws UnknownHostException {
		stcs = new StoreOwnerCrudServiceImpl();
	}
	@Override
	public String registerClient(JSONObject newClient) {
		// TODO Auto-generated method stub
		HashMap<String, String> clientDetails = new HashMap<String, String>();
		clientDetails.put("username", newClient.get("username").toString());
		clientDetails.put("name", newClient.get("name").toString());
		clientDetails.put("surname", newClient.get("surname").toString());
		clientDetails.put("registrationDate", new DateTime().toString());
		clientDetails.put("password",newClient.get("password").toString());
		
		StoreOwner a = ApplicationFactory.buildStoreOwner(clientDetails);
		return stcs.createEntity(a);
	}

}
