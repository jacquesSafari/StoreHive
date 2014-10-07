package com.storehive.app.services.app;

import java.util.List;

import org.json.simple.JSONObject;

import com.storehive.app.domain.Category;
import com.storehive.app.utilities.ResponseResult;

public interface StoreOperationServices {

	public List<Category> getCategoriesAvailable();
	
	//pre-condition:  need a JSON object container Store Attributes 
	//post-condition: return a response isSuccessful = true and the StoreId
	public ResponseResult registerStore(JSONObject jsonObject);
	
	//pre-condition:  need a JSON object containing Product Attributes 
	//post-condition: return a response isSuccessful = true and the Product Id
	public ResponseResult addProductToStore(JSONObject jsonObject);
	
	
}
