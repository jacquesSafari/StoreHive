package com.storehive.app.services.app;

import org.json.simple.JSONObject;

import com.storehive.app.utilities.ResponseResult;



public interface BusinessServices {
	public ResponseResult registerClient(JSONObject jsonObject);
	
	public ResponseResult loginClient(JSONObject jsonObject);
	
}
