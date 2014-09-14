package com.storehive.app.restapi;

import java.net.UnknownHostException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.storehive.app.services.app.BusinessServices;
import com.storehive.app.services.app.impl.BusinessServicesImpl;
import com.storehive.app.utilities.Message;

@Path("/business")
public class BusinessRestService {

	private BusinessServices bss;
	
	public BusinessRestService() throws UnknownHostException {
		bss = new BusinessServicesImpl();
	}
	
	@POST
	@Path("/newStoreOwner")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public JSONObject registerClient(JSONObject newClient){
		Message success = bss.registerClient(newClient);
		JSONObject message = new JSONObject();
		
		if(success.getPrimaryMessage().equals("true")){
			message.put("isSuccessfull", success.getPrimaryMessage());
		}else{
			message.put("isSuccessfull", success.getPrimaryMessage());
			message.put("errorMessage", success.getSupportingMessage());
		}
		return message;
	}
	
	
	@GET
	@Produces("application/json")
	@SuppressWarnings("unchecked")
	public JSONObject testRest(){
		JSONObject j = new JSONObject();
		j.put("name", "tyrone");
		return j;
	}
	
	
}
