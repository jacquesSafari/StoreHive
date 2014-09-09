package com.storehive.app.services.crud.impl;

import java.net.UnknownHostException;
import java.util.List;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.storehive.app.domain.StoreOwner;
import com.storehive.app.services.crud.StoreOwnerCrudService;

public class StoreOwnerCrudServiceImpl implements StoreOwnerCrudService{

	private MongoClient client;
	
	public StoreOwnerCrudServiceImpl() throws UnknownHostException{
		client = new MongoClient();
	}
	
	private DB getDB(){
		return client.getDB("storehive");
	}
	@Override
	public String createEntity(StoreOwner a) {
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		BasicDBObject storeOwner = new BasicDBObject();
		storeOwner.put("username", a.getUsername());
		storeOwner.put("name", a.getName());
		storeOwner.put("surname", a.getSurname());
		storeOwner.put("registration_date", a.getRegistrationDate());
		storeOwner.put("password", encryptor.encryptPassword(a.getPassword()));
		
		DBCollection storeOwners = getDB().getCollection("storeowners");
		storeOwners.insert(storeOwner);
		return a.getUsername();
	}

	@Override
	public StoreOwner findEntityById(String ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StoreOwner> getAllEntities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateEntity(StoreOwner a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteEntity(StoreOwner a) {
		// TODO Auto-generated method stub
		return false;
	}

}
