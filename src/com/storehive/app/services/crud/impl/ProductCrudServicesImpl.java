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
import com.storehive.app.domain.Products;
import com.storehive.app.services.crud.ProductCrudServices;
import com.storehive.app.utilities.ErrorCodes;
import com.storehive.app.utilities.ResponseResult;
import com.storehive.app.utilities.factory.ApplicationFactory;

public class ProductCrudServicesImpl implements ProductCrudServices {

	private MongoClient client;
	
	public ProductCrudServicesImpl() throws UnknownHostException{
		client = new MongoClient();
	}
	
	private DB getDB(){
		return client.getDB("storehive");
	}
	
	@Override
	public ResponseResult createEntity(Products a) {
		ResponseResult output = new ResponseResult();
		if(!doesProductExist(a.getProductName())){
			try{
				Category c = a.getCategory();
				BasicDBObject category = new BasicDBObject();
				category.put("_id", new ObjectId(c.getId()));
				category.put("categoryName", c.getCategoryName());
				category.put("categoryDescription", c.getCategoryDescription());
				
				BasicDBObject newProduct = new BasicDBObject();
				newProduct.put("productName", a.getProductName());
				newProduct.put("description", a.getDescription());
				newProduct.put("price", a.getPrice());
				newProduct.put("category", category);
				
				DBCollection storeProduct = getDB().getCollection("product_collection");
				storeProduct.insert(newProduct);
				output.setSuccessful(true);
			}catch(NullPointerException e){
				e.printStackTrace();
				output.setSuccessful(false);
				output.setErrorCode(ErrorCodes.PROD_ERR_2);
				output.setErrorMessage("something crazy went wrong, dont stress");
			}
		}else{
			output.setSuccessful(false);
			output.setErrorCode(ErrorCodes.PROD_ERR_1);
			output.setErrorMessage("product already exists");
		}
		return output;
	}

	@Override
	public Products findEntityById(String ID) {
		BasicDBObject product = new BasicDBObject("_id",new ObjectId(ID));
		DBCursor findQuery = getDB().getCollection("product_collection").find(product);
		Products productFound = null;
		
		if(findQuery.hasNext())
			productFound = ApplicationFactory.buildProduct((BasicDBObject)findQuery.next());
		
		return productFound;
	}

	@Override
	public List<Products> getAllEntities() {
		List<Products> allProducts = null;
		DBCursor findAll = getDB().getCollection("product_collection").find();
		
		if(findAll.hasNext()){
			allProducts = new ArrayList<Products>();
			while(findAll.hasNext()){
				Products p = ApplicationFactory.buildProduct((BasicDBObject)findAll.next());
				allProducts.add(p);
			}
		}
		return allProducts;
	}

	@Override
	public ResponseResult updateEntity(Products a) {
		ResponseResult output = new ResponseResult();
		try{
			Category c = a.getCategory();
			BasicDBObject category = new BasicDBObject();
			category.put("_id", new ObjectId(c.getId()));
			category.put("categoryName", c.getCategoryName());
			category.put("categoryDescription", c.getCategoryDescription());
			
			BasicDBObject toUpdate = new BasicDBObject();
			toUpdate.put("productName", a.getProductName());
			toUpdate.put("description", a.getDescription());
			toUpdate.put("price", a.getPrice());
			toUpdate.put("category", category );
			
			BasicDBObject productToUpdate = new BasicDBObject("_id", new ObjectId(a.getId()));
			DBCollection storeProduct = getDB().getCollection("product_collection");
			storeProduct.update(productToUpdate, toUpdate);
			output.setSuccessful(true);
		}catch(NullPointerException e){
			e.printStackTrace();
			output.setSuccessful(false);
			output.setErrorCode(ErrorCodes.PROD_ERR_3);
			output.setErrorMessage("something crazy went wrong, dont stress");
		}
		return output;
	}

	@Override
	public boolean deleteEntity(Products a) {
		DBCollection storeProduct = getDB().getCollection("product_collection");
		BasicDBObject toBeRemoved = new BasicDBObject();
		toBeRemoved.put("_id", new ObjectId(a.getId()));
		storeProduct.remove(toBeRemoved);
		return true;
	}

	@Override
	public boolean doesProductExist(String productName) {
		BasicDBObject product = new BasicDBObject("productName",productName);
		DBCursor storeProduct = getDB().getCollection("product_collection").find(product);
		
		if(storeProduct.hasNext())
			return true;
		
		return false;
	}
	
	@Override
	public Products findStoreByProperty(String property,String value){
		BasicDBObject product = new BasicDBObject(property,value);
		DBCursor findQuery = getDB().getCollection("product_collection").find(product);
		Products productFound = null;
		
		if(findQuery.hasNext())
			productFound = ApplicationFactory.buildProduct((BasicDBObject)findQuery.next());
		
		return productFound;
	}

}
