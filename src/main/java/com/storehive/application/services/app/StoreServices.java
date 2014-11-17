package main.java.com.storehive.application.services.app;

import main.java.com.storehive.application.domain.Storeowner;
import main.java.com.storehive.application.utilities.ResponseResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface StoreServices {

	public ResponseResult registerNewClient(JSONObject s);
	
	public JSONArray getAllStoreOwners();
	
	public JSONObject viewUserProfile(Integer id);
	
	public JSONObject updateUserProfile(Storeowner s);
	
	public ResponseResult deleteStoreOwnerProfile(Integer id);
		
	public ResponseResult loginUser(String username,String password);
	
}
