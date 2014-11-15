package main.java.com.storehive.application.services.app;

import main.java.com.storehive.application.utilities.ResponseResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface InventoryServices {

	public JSONArray getAllCategories();
	
	public ResponseResult addProductToStore(JSONObject productDetails);
	
	public JSONObject viewProductDetails(Integer id);
	
	public JSONArray getAllProducts();
	
	public ResponseResult deleteProductFromInventory(Integer id);
	
	public JSONObject updateProduct(JSONObject productDetails);
}
