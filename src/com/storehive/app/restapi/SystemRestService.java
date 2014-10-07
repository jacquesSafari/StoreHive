package com.storehive.app.restapi;

import java.net.UnknownHostException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.storehive.app.domain.Category;
import com.storehive.app.services.app.StoreOperationServices;
import com.storehive.app.services.app.impl.StoreOperationServicesImpl;
import com.storehive.app.utilities.ResponseResult;
import com.storehive.app.utilities.ResponseResultEnum;

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
	
	@POST
	@Path("/newStore")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public JSONObject registerClient(JSONObject newStore){
		
		ResponseResult success = ss.registerStore(newStore);
		JSONObject message = new JSONObject();
		
		if(success.isSuccessful()){
			message.put(ResponseResultEnum.isSuccessful, success.isSuccessful());
			message.put("ownerEmail", success.getErrorMessage());
		}else{
			message.put(ResponseResultEnum.isSuccessful, success.isSuccessful());
			message.put(ResponseResultEnum.errorCode, success.getErrorCode());
			message.put(ResponseResultEnum.errorMessage, success.getErrorMessage());
		}
		return message;
	}
	
	@POST
	@Path("/newProduct")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public JSONObject addProduct(JSONObject newProduct){
		
		ResponseResult success = ss.addProductToStore(newProduct);
		JSONObject message = new JSONObject();
		
		if(success.isSuccessful()){
			message.put(ResponseResultEnum.isSuccessful, success.isSuccessful());
			message.put("productName", success.getErrorMessage());
		}else{
			message.put(ResponseResultEnum.isSuccessful, success.isSuccessful());
			message.put(ResponseResultEnum.errorCode, success.getErrorCode());
			message.put(ResponseResultEnum.errorMessage, success.getErrorMessage());
		}
		return message;
	}
}
