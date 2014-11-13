package main.java.com.storehive.application.services.crud.impl;

import java.util.ArrayList;
import java.util.List;

import main.java.com.storehive.application.domain.StoreOwner;
import main.java.com.storehive.application.repository.MongoResource;
import main.java.com.storehive.application.services.crud.StoreOwnerCrudService;
import main.java.com.storehive.application.utilities.ErrorCodes;
import main.java.com.storehive.application.utilities.ResponseResult;
import main.java.com.storehive.application.utilities.Utilities;
import main.java.com.storehive.application.utilities.factory.ApplicationFactory;

import org.bson.types.ObjectId;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.mongodb.morphia.Morphia;

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
		if(!doesStoreOwnerExist(a.getEmail())){
			try{
				StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
				BasicDBObject storeOwner = new BasicDBObject();
				storeOwner.put("fullname", a.getFullname());
				storeOwner.put("email", a.getEmail());
				storeOwner.put("registrationDate", a.getRegistrationDate());
				storeOwner.put("deviceId", a.getDeviceID());
				storeOwner.put("password", encryptor.encryptPassword(a.getPassword()));
				
				DBCollection storeOwners = getDB().getCollection("store_owners_collection");
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
			output.setErrorMessage("email already exists");
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
		DBCollection storeOwnerCollection = getDB().getCollection("store_owners_collection");
		DBCursor cursor = storeOwnerCollection.find();
		List<StoreOwner> allStoreOwners = new ArrayList<StoreOwner>();
		while(cursor.hasNext()){
			StoreOwner found = ApplicationFactory.buildStoreOwner((BasicDBObject)cursor.next());
			allStoreOwners.add(found);
		}
		return allStoreOwners;
	}

	@Override
	public ResponseResult updateEntity(StoreOwner a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteEntity(StoreOwner a) {
		DBCollection storeOwner = getDB().getCollection("store_owners_collection");
		BasicDBObject toBeRemoved = new BasicDBObject();
//		toBeRemoved.put("_id", new ObjectId(a.getId()));
		storeOwner.remove(toBeRemoved);
		return true;
	}

	@Override
	public boolean doesStoreOwnerExist(String username) {
		BasicDBObject storeOwner = new BasicDBObject("email",username);
		DBCursor storeOwners = getDB().getCollection("store_owners_collection").find(storeOwner);
		
		if(storeOwners.hasNext())
			return true;
		
		return false;
	}

	@Override
	public StoreOwner findStoreOwnerByEmail(String email) {
		BasicDBObject query = new BasicDBObject("email",email);
		DBCursor ownersFound = getDB().getCollection("store_owners_collection").find(query);
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
