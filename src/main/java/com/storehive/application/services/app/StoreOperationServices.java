package main.java.com.storehive.application.services.app;

import java.util.List;

import main.java.com.storehive.application.domain.Category;
import main.java.com.storehive.application.utilities.ResponseResult;

import org.json.simple.JSONObject;

public interface StoreOperationServices {

	public List<Category> getCategoriesAvailable();
	
	//pre-condition:  need a JSON object container Store Attributes 
	//post-condition: return a response isSuccessful = true and the StoreId
	public ResponseResult registerStore(JSONObject jsonObject);
	
	//pre-condition:  need a JSON object containing Product Attributes 
	//post-condition: return a response isSuccessful = true and the Product Id
	public ResponseResult addProductToStore(JSONObject jsonObject);
	
	
}
