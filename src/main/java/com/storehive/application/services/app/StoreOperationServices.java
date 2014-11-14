package main.java.com.storehive.application.services.app;

import main.java.com.storehive.application.domain.Store;
import main.java.com.storehive.application.utilities.ResponseResult;

import org.json.simple.JSONObject;


public interface StoreOperationServices {

	public ResponseResult registerStore(Store s);
	
	public JSONObject viewStoreDetails(Integer id);
	
	public JSONObject openStore(Store s);
	
	public JSONObject closeStore(Store s);
}
