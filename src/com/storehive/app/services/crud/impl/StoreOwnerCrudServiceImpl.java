package com.storehive.app.services.crud.impl;

import java.net.UnknownHostException;
import java.util.List;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.storehive.app.domain.StoreOwner;
import com.storehive.app.services.crud.StoreOwnerCrudService;
import com.storehive.app.utilities.ErrorCodes;
import com.storehive.app.utilities.ResponseResult;
import com.storehive.app.utilities.factory.ApplicationFactory;

public class StoreOwnerCrudServiceImpl implements StoreOwnerCrudService{

	private MongoClient client;
	public StoreOwnerCrudServiceImpl() throws UnknownHostException{
		client = new MongoClient();
	}
	
	private DB getDB(){
		return client.getDB("storehive");
	}
	@Override
	public ResponseResult createEntity(StoreOwner a) {
		ResponseResult output = new ResponseResult();
		if(!doesStoreOwnerExist(a.getUsername())){
			try{
				StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
				BasicDBObject storeOwner = new BasicDBObject();
				storeOwner.put("username", a.getUsername());
				storeOwner.put("name", a.getName());
				storeOwner.put("surname", a.getSurname());
				storeOwner.put("registrationDate", a.getRegistrationDate());
				storeOwner.put("deviceId", a.getDeviceID());
				storeOwner.put("password", encryptor.encryptPassword(a.getPassword()));
				
				DBCollection storeOwners = getDB().getCollection("storeowners");
				storeOwners.insert(storeOwner);
				output.setSuccessful(true);
			}catch(NullPointerException e){
				output.setSuccessful(false);
				output.setErrorCode(ErrorCodes.REG_ERR_1);
				output.setErrorMessage("something crazy went wrong, dont stress");
			}
		}else{
			output.setSuccessful(false);
			output.setErrorCode(ErrorCodes.REG_ERR_1);
			output.setErrorMessage("username exists");
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
	public ResponseResult updateEntity(StoreOwner a) {
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

	@Override
	public StoreOwner findStoreOwnerByUsername(String username) {
		BasicDBObject query = new BasicDBObject("username",username);
		DBCursor ownersFound = getDB().getCollection("storeowners").find(query);
		StoreOwner found = null;
		try{
			if(ownersFound.hasNext())
				found = ApplicationFactory.buildStoreOwner((BasicDBObject)ownersFound.next());
		}catch(NullPointerException npe){
			npe.printStackTrace();
		}
		return found;
	}

}
