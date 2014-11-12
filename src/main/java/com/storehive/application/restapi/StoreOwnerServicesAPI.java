package main.java.com.storehive.application.restapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.java.com.storehive.application.services.app.BusinessServices;
import main.java.com.storehive.application.services.app.impl.BusinessServicesImpl;
import main.java.com.storehive.application.utilities.ResponseResult;
import main.java.com.storehive.application.utilities.ResponseResultEnum;

import org.json.simple.JSONObject;

@Path("/storeOwnerServices")
@SuppressWarnings("unchecked")
public class StoreOwnerServicesAPI {

	private BusinessServices bss;
	
	public StoreOwnerServicesAPI(){
		bss = new BusinessServicesImpl();
	}
	
	@POST
	@Path("/registerNewStoreOwner")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject registerClient(JSONObject newClient){
		
		ResponseResult success = bss.registerClient(newClient);
		JSONObject message = new JSONObject();
		
		if(success.isSuccessful()){
			message.put(ResponseResultEnum.isSuccessful, success.isSuccessful());
		}else{
			message.put(ResponseResultEnum.isSuccessful, success.isSuccessful());
			message.put(ResponseResultEnum.errorCode, success.getErrorCode());
			message.put(ResponseResultEnum.errorMessage, success.getErrorMessage());
		}
		return message;
	}
	
	@POST
	@Path("/loginStoreOwner")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject loginClient(JSONObject toBeLoggedIn){
		
		ResponseResult success = bss.loginClient(toBeLoggedIn);
		JSONObject message = new JSONObject();
		
		if(success.isSuccessful()){
			message.put(ResponseResultEnum.isSuccessful, success.isSuccessful());
		}else{
			message.put(ResponseResultEnum.isSuccessful, success.isSuccessful());
			message.put(ResponseResultEnum.errorCode, success.getErrorCode());
			message.put(ResponseResultEnum.errorMessage, success.getErrorMessage());
		}
		return message;
	}

	// updateStoreOwnerDetails
	
	// viewStoreOwnerDetails
	
	// deleteStoreOwnerProfile
	
}
