package main.java.com.storehive.application.restapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.java.com.storehive.application.services.app.TransactionalServices;
import main.java.com.storehive.application.services.app.impl.TransactionalServicesImpl;
import main.java.com.storehive.application.utilities.ResponseResult;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Path("/storeTransactionServices")
@SuppressWarnings("unchecked")
public class StoreTransactionServicesAPI {

	TransactionalServices ts;
	
	public StoreTransactionServicesAPI(){
		ts = new TransactionalServicesImpl();
	}
	
	@POST
	@Path("/sellStoreItem")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String sellProducts(String s){
		JSONObject message = new JSONObject();
		JSONParser jp = new JSONParser();
		
		try {
			Object c = jp.parse(s);
			JSONObject j = (JSONObject)c;
			ResponseResult r = ts.sellProduct(j);
			message.put("message", r.getErrorMessage());
		}catch(Exception e){
			e.printStackTrace();
		}
		return message.toJSONString();
	}
}
