package main.java.com.storehive.application.services.app;

import main.java.com.storehive.application.utilities.ResponseResult;

import org.json.simple.JSONObject;



public interface BusinessServices {
	
	public ResponseResult registerClient(JSONObject jsonObject);
	
	public ResponseResult loginClient(JSONObject jsonObject);
	
}
