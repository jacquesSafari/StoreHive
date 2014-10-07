package com.storehive.app.services.app.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.storehive.app.domain.Category;
import com.storehive.app.domain.Products;
import com.storehive.app.domain.Store;
import com.storehive.app.services.app.StoreOperationServices;
import com.storehive.app.services.crud.CategoryCrudService;
import com.storehive.app.services.crud.ProductCrudServices;
import com.storehive.app.services.crud.StoreCrudServices;
import com.storehive.app.services.crud.impl.CategoryCrudServiceImpl;
import com.storehive.app.services.crud.impl.ProductCrudServicesImpl;
import com.storehive.app.services.crud.impl.StoreCrudServicesImpl;
import com.storehive.app.utilities.ResponseResult;
import com.storehive.app.utilities.factory.ApplicationFactory;

public class StoreOperationServicesImpl implements StoreOperationServices {

	private CategoryCrudService cs;
	private StoreCrudServices scs;
	private ProductCrudServices pcs;
	
	public StoreOperationServicesImpl() throws UnknownHostException{
		cs = new CategoryCrudServiceImpl();
		scs = new StoreCrudServicesImpl();
		pcs = new ProductCrudServicesImpl();
	}
	@Override
	public List<Category> getCategoriesAvailable() {
		List<Category> allCategories = new ArrayList<Category>();
		
		if(cs.countCategoriesAvailable()>0){
			allCategories.addAll(cs.getAllEntities());
		}
		
		return allCategories;
	}
	
	@Override
	public ResponseResult registerStore(JSONObject jsonObject) {
		ResponseResult output = new ResponseResult();
		
		BasicDBObject storeDetails = new BasicDBObject();
		BasicDBObject location = new BasicDBObject();
		
		Object l = jsonObject.get("location");
		Gson gson = new Gson();
		
		try {
			JSONObject loc = (JSONObject) new JSONParser().parse(gson.toJson(l));
		
			location.put("latitude", loc.get("latitude"));
			location.put("longitude", loc.get("longitude"));
		
			storeDetails.put("shopName", jsonObject.get("shopName").toString());
			storeDetails.put("description", jsonObject.get("description").toString());
			storeDetails.put("ownerEmail", jsonObject.get("ownerEmail").toString());
			storeDetails.put("lastOpenedDate", new DateTime().toString());
			storeDetails.put("isOpen", jsonObject.get("isOpen"));
			storeDetails.put("location", location);
			
			Store s = ApplicationFactory.buildStore(storeDetails);
			output = scs.createEntity(s);
			
			if(output.isSuccessful()){
				output.setErrorMessage(scs.findStoreByProperty("ownerEmail",s.getOwnerEmail()).getOwnerEmail());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return output;
	}
	
	@Override
	public ResponseResult addProductToStore(JSONObject jsonObject) {
		ResponseResult output = new ResponseResult();
		
		BasicDBObject category = new BasicDBObject();
		BasicDBObject product = new BasicDBObject();
		
		Object l = jsonObject.get("category");
		Gson gson = new Gson();
		
		try {
			JSONObject cat = (JSONObject) new JSONParser().parse(gson.toJson(l));
			
			category.put("_id", new ObjectId(cat.get("_id").toString()));
			category.put("categoryName", cat.get("categoryName").toString());
			category.put("categoryDescription", cat.get("categoryDescription").toString());
			
			product.put("productName", jsonObject.get("productName").toString());
			product.put("description", jsonObject.get("description").toString());
			product.put("price", jsonObject.get("price").toString());
			product.put("category", category);
			
			Products p = ApplicationFactory.buildProduct(product);
			output = pcs.createEntity(p);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return output;
	}

}
