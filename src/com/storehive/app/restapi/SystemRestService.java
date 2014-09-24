package com.storehive.app.restapi;

import java.net.UnknownHostException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.storehive.app.domain.Category;
import com.storehive.app.services.app.StoreOperationServices;
import com.storehive.app.services.app.impl.StoreOperationServicesImpl;

@Path("/storeoperations")
public class SystemRestService {
	
	private StoreOperationServices ss;
	
	public SystemRestService() throws UnknownHostException {
		ss = new StoreOperationServicesImpl();
	}
	
	@GET
	@Path("/categories")
	public JSONArray getAllCategories(){
		List<Category> allCategories = ss.getCategoriesAvailable();
		JSONArray all = new JSONArray();
		if(allCategories.size()>0){
			for(Category c:allCategories){
				JSONObject j = new JSONObject();
				j.put("_id", c.getId());
				j.put("categoryName", c.getCategoryName());
				j.put("categoryDescription", c.getCategoryDescription());
				all.add(j);
			}
		}
		
		return all;
	}
}
