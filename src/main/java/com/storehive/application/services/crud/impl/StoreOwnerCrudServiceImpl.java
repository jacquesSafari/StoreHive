package main.java.com.storehive.application.services.crud.impl;

import java.util.List;

import main.java.com.storehive.application.domain.StoreOwner;
import main.java.com.storehive.application.repository.MongoResource;
import main.java.com.storehive.application.services.crud.StoreOwnerCrudService;
import main.java.com.storehive.application.utilities.ErrorCodes;
import main.java.com.storehive.application.utilities.ResponseResult;
import main.java.com.storehive.application.utilities.factory.ApplicationFactory;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class StoreOwnerCrudServiceImpl implements StoreOwnerCrudService{

	private MongoResource resource;
	
	public StoreOwnerCrudServiceImpl(){
		resource = MongoResource.INSTANCE;
	}
	
	private DB getDB(){
		return resource.getDatastore("storehive").getDB();
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
