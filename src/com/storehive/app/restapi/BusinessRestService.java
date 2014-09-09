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
	public JSONObject registerClient(JSONObject newClient){
		String username = bss.registerClient(newClient);
		JSONObject success = new JSONObject();
		success.put("username", username);
		return success;
		
	}
	
	@GET
	@Produces("application/json")
	public JSONObject testRest(){
		JSONObject j = new JSONObject();
		j.put("name", "tyrone");
		return j;
	}
	
	
}
