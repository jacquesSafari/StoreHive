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
import com.storehive.app.domain.Category;
import com.storehive.app.services.crud.CategoryCrudService;
import com.storehive.app.utilities.ErrorCodes;
import com.storehive.app.utilities.ResponseResult;
import com.storehive.app.utilities.factory.ApplicationFactory;

public class CategoryCrudServiceImpl implements CategoryCrudService {

	private MongoClient client;
	public CategoryCrudServiceImpl() throws UnknownHostException{
		client = new MongoClient();
	}
	
	private DB getDB(){
		return client.getDB("storehive");
	}
	
	@Override
	public ResponseResult createEntity(Category a) {
		ResponseResult output = new ResponseResult();
		if(!doesCategoryExist(a.getCategoryName())){
			try{
				BasicDBObject category = new BasicDBObject();
				category.put("categoryName", a.getCategoryName());
				category.put("categoryDescription", a.getCategoryDescription());

				DBCollection categories = getDB().getCollection("categories");
				categories.insert(category);
				output.setSuccessful(true);
			}catch(NullPointerException e){
				output.setSuccessful(false);
				output.setErrorCode(ErrorCodes.CAT_ERR_1);
				output.setErrorMessage("something crazy went wrong, dont stress");
			}
		}else{
			output.setSuccessful(false);
			output.setErrorCode(ErrorCodes.CAT_ERR_2);
			output.setErrorMessage("category exists");
		}
		return output;
	}

	@Override
	public Category findEntityById(String ID) {
		BasicDBObject category = new BasicDBObject("_id",new ObjectId(ID));
		DBCursor findQuery = getDB().getCollection("categories").find(category);
		Category categoryFound = null;
		
		if(findQuery.hasNext())
			categoryFound = ApplicationFactory.buildCategory((BasicDBObject)findQuery.next());
		
		return categoryFound;
	}

	@Override
	public List<Category> getAllEntities() {
		DBCursor findQuery = getDB().getCollection("categories").find();
		List<Category> categoriesFound = new ArrayList<Category>();
		
		while(findQuery.hasNext()){
			Category a = ApplicationFactory.buildCategory((BasicDBObject)findQuery.next());
			categoriesFound.add(a);
		}
		
		return categoriesFound;
	}

	@Override
	public ResponseResult updateEntity(Category a) {
		ResponseResult output = new ResponseResult();
		
		try{
			DBCollection categoryCollection = getDB().getCollection("categories");
			BasicDBObject toUpdate = new BasicDBObject();
			toUpdate.put("categoryName", a.getCategoryName());
			toUpdate.put("categoryDescription", a.getCategoryDescription());
			
			BasicDBObject categoryToUpdate = new BasicDBObject("_id", new ObjectId(a.getId()));
			categoryCollection.update(categoryToUpdate, toUpdate);
			
			output.setSuccessful(true);
		}catch(Exception e){
			output.setSuccessful(false);
			output.setErrorCode(ErrorCodes.CAT_ERR_3);
			output.setErrorMessage(e.getMessage());
		}
		return output;
	}

	@Override
	public boolean deleteEntity(Category a) {
		DBCollection categoryCollection = getDB().getCollection("categories");
		BasicDBObject toBeRemoved = new BasicDBObject();
		toBeRemoved.put("_id", new ObjectId(a.getId()));
		categoryCollection.remove(toBeRemoved);
		return true;
	}
	
	@Override
	public boolean doesCategoryExist(String categoryName) {
		BasicDBObject category = new BasicDBObject("categoryName",categoryName);
		DBCursor categoryFound = getDB().getCollection("categories").find(category);
		
		if(categoryFound.hasNext())
			return true;
		
		return false;
	}

	@Override
	public Category findCategoryByName(String categoryName) {
		BasicDBObject category = new BasicDBObject("categoryName",categoryName);
		DBCursor findQuery = getDB().getCollection("categories").find(category);
		Category categoryFound = null;
		
		if(findQuery.hasNext())
			categoryFound = ApplicationFactory.buildCategory((BasicDBObject)findQuery.next());
		
		return categoryFound;
	}

	@Override
	public int countCategoriesAvailable() {
		return getDB().getCollection("categories").find().count();
	}


}
