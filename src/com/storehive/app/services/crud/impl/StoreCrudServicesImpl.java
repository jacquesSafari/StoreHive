package com.storehive.app.services.crud.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.storehive.app.domain.Store;
import com.storehive.app.services.crud.StoreCrudServices;
import com.storehive.app.utilities.ErrorCodes;
import com.storehive.app.utilities.ResponseResult;
import com.storehive.app.utilities.factory.ApplicationFactory;

public class StoreCrudServicesImpl implements StoreCrudServices {

	private MongoClient client;
	
	public StoreCrudServicesImpl() throws UnknownHostException{
		client = new MongoClient();
	}
	
	private DB getDB(){
		return client.getDB("storehive");
	}
	
	@Override
	public ResponseResult createEntity(Store a) {
		ResponseResult output = new ResponseResult();
		DBCollection storeCollection = getDB().getCollection("store_collection");
		
		try{
			BasicDBObject storeLocation = new  BasicDBObject();
			storeLocation.put("latitude", a.getLocation().getLatitude());
			storeLocation.put("longitude",a.getLocation().getLongitude());
			
			BasicDBObject newStore = new BasicDBObject();
			newStore.put("shopName",a.getShopName());
			newStore.put("description", a.getDescription());			
			newStore.put("ownerEmail", a.getOwnerEmail());
			newStore.put("lastOpenedDAte", "");
			newStore.put("isOpen", false);
			newStore.put("location", storeLocation);
			
			storeCollection.insert(newStore);
			output.setSuccessful(true);
		}catch(Exception e){
			output.setSuccessful(false);
			output.setErrorCode(ErrorCodes.STORE_ERR_1);
			output.setErrorMessage("something crazy went wrong, dont stress");
		}
		return output;
	}

	@Override
	public Store findEntityById(String ID) {
		BasicDBObject store = new BasicDBObject("_id",new ObjectId(ID));
		DBCursor findQuery = getDB().getCollection("store_collection").find(store);
		Store storeFound = null;
		
		if(findQuery.hasNext())
			storeFound = ApplicationFactory.buildStore((BasicDBObject)findQuery.next());
		
		return storeFound;			
	}

	@Override
	public List<Store> getAllEntities() {
		List<Store> allStores = null;
		DBCursor findAll = getDB().getCollection("store_collection").find();
		
		if(findAll.hasNext()){
			allStores = new ArrayList<Store>();
			while(findAll.hasNext()){
				Store s = ApplicationFactory.buildStore((BasicDBObject)findAll.next());
				allStores.add(s);
			}
		}
		return allStores;
	}

	@Override
	public ResponseResult updateEntity(Store a) {
		ResponseResult output = new ResponseResult();
		try{
			DBCollection storeCollection = getDB().getCollection("store_collection");
			
			BasicDBObject storeLocation = new  BasicDBObject();
			storeLocation.put("latitude", a.getLocation().getLatitude());
			storeLocation.put("longitude",a.getLocation().getLongitude());
			
			BasicDBObject toUpdate = new BasicDBObject();
			toUpdate.put("shopName",a.getShopName());
			toUpdate.put("description", a.getDescription());			
			toUpdate.put("ownerEmail", a.getOwnerEmail());
			toUpdate.put("lastOpenedDate", a.getLastOpenedDate());
			toUpdate.put("isOpen", a.isOpen());
			toUpdate.put("location", storeLocation);
			
			BasicDBObject storeToUpdate = new BasicDBObject("_id", new ObjectId(a.getId()));
			storeCollection.update(storeToUpdate, toUpdate);
			output.setSuccessful(true);
		}catch(Exception e){
			e.printStackTrace();
			output.setSuccessful(false);
			output.setErrorCode(ErrorCodes.STORE_ERR_3);
			output.setErrorMessage("something went wrong while updating store, dont stress");
		}
		return output;
	}

	@Override
	public boolean deleteEntity(Store a) {
		DBCollection storeCollection = getDB().getCollection("store_collection");
		BasicDBObject toBeRemoved = new BasicDBObject();
		toBeRemoved.put("_id", new ObjectId(a.getId()));
		storeCollection.remove(toBeRemoved);
		return true;
	}
	
	@Override
	public Store findStoreByProperty(String property,String value){
		BasicDBObject store = new BasicDBObject(property,value);
		DBCursor findQuery = getDB().getCollection("store_collection").find(store);
		Store storeFound = null;
		
		if(findQuery.hasNext())
			storeFound = ApplicationFactory.buildStore((BasicDBObject)findQuery.next());
		
		return storeFound;
	}

}
