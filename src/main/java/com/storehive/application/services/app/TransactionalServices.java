package main.java.com.storehive.application.services.app;

import main.java.com.storehive.application.utilities.ResponseResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface TransactionalServices {

	public ResponseResult sellProduct(JSONObject transaction);
	
	public JSONArray viewAllTransactionsToday(Integer id);
	
	public JSONArray viewAllTransactionsForDate(Integer id, String date);
}
