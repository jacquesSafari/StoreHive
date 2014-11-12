package main.java.com.storehive.application.restapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.java.com.storehive.application.services.app.StoreOperationServices;
import main.java.com.storehive.application.services.app.impl.StoreOperationServicesImpl;
import main.java.com.storehive.application.utilities.ResponseResult;
import main.java.com.storehive.application.utilities.ResponseResultEnum;

import org.json.simple.JSONObject;

@Path("/storeServices")
@SuppressWarnings("unchecked")
public class StoreServicesAPI {
	
	private StoreOperationServices ss;
	
	public StoreServicesAPI(){
		ss = new StoreOperationServicesImpl();
	}
	
	@POST
	@Path("/registerNewStore")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
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
	
//	openStore
	
//	closeStore
}
