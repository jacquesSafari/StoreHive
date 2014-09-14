package com.storehive.app.services.crud.impl;

import java.net.UnknownHostException;
import java.util.List;

import org.apache.catalina.connector.OutputBuffer;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.storehive.app.domain.StoreOwner;
import com.storehive.app.services.crud.StoreOwnerCrudService;
import com.storehive.app.utilities.Message;

public class StoreOwnerCrudServiceImpl implements StoreOwnerCrudService{

	private MongoClient client;
	public StoreOwnerCrudServiceImpl() throws UnknownHostException{
		client = new MongoClient();
	}
	
	private DB getDB(){
		return client.getDB("storehive");
	}
	@Override
	public Message createEntity(StoreOwner a) {
		Message output = new Message();
		if(!doesStoreOwnerExist(a.getUsername())){
			try{
				StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
				BasicDBObject storeOwner = new BasicDBObject();
				storeOwner.put("username", a.getUsername());
				storeOwner.put("name", a.getName());
				storeOwner.put("surname", a.getSurname());
				storeOwner.put("registration_date", a.getRegistrationDate());
				storeOwner.put("device_id", a.getDeviceID());
				storeOwner.put("password", encryptor.encryptPassword(a.getPassword()));
				
				DBCollection storeOwners = getDB().getCollection("storeowners");
				storeOwners.insert(storeOwner);
				output.setPrimaryMessage("true");
			}catch(NullPointerException e){
				output.setPrimaryMessage("false");
				output.setSupportingMessage("something crazy went wrong, dont stress");
			}
		}else{
			output.setPrimaryMessage("false");
			output.setSupportingMessage("username exists");
		}
		return output;
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
	public Message updateEntity(StoreOwner a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteEntity(StoreOwner a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doesStoreOwnerExist(String username) {
		BasicDBObject storeOwner = new BasicDBObject("username",username);
		DBCursor storeOwners = getDB().getCollection("storeowners").find(storeOwner);
		
		if(storeOwners.hasNext())
			return true;
		
		return false;
	}

}
