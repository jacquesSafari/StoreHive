package main.java.com.storehive.application.restapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.java.com.storehive.application.services.app.SearchServices;
import main.java.com.storehive.application.services.app.impl.SearchServicesImpl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Path("/searchServices")
public class SearchServicesAPI {

	SearchServices ss;
	
	public SearchServicesAPI(){
		ss = new SearchServicesImpl();
	}
	
	@POST
	@Path("/findStoreWithCategory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String findStoreWithCategory(String shoppingList){
		JSONArray allStoresInRange = new JSONArray();
		try{
			JSONParser jp = new JSONParser();
			Object c = jp.parse(shoppingList);
			JSONObject parsedShoppingList = (JSONObject)c;
			
			allStoresInRange = ss.searchByCategory(parsedShoppingList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return allStoresInRange.toJSONString();
	}
	
}
