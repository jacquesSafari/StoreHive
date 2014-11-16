package main.java.com.storehive.application.services.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface SearchServices {

	public JSONArray searchByCategory(JSONObject searParameters);
}
