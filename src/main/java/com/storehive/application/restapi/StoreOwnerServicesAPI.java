package main.java.com.storehive.application.restapi;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.java.com.storehive.application.domain.Storeowner;
import main.java.com.storehive.application.services.app.StoreServices;
import main.java.com.storehive.application.services.app.impl.StoreServicesImpl;
import main.java.com.storehive.application.utilities.ResponseResult;
import main.java.com.storehive.application.utilities.ResponseResultEnum;

import org.codehaus.jackson.map.ObjectMapper;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.json.simple.JSONObject;

@Path("/storeOwnerServices")
@SuppressWarnings("unchecked")
public class StoreOwnerServicesAPI {

	StoreServices ss;
	
	public StoreOwnerServicesAPI(){
		ss = new StoreServicesImpl();
	}
	
	@POST
	@Path("/registerNewStoreOwner")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String registerClient(String s){
		ResponseResult success = new ResponseResult();
		StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
		
		ObjectMapper mapper = new ObjectMapper();
		
		JSONObject message = new JSONObject();
		
		try {
			Storeowner so = mapper.readValue(s, Storeowner.class);
			String clearTextPassword = so.getPassword();
			so.setRegisteredDate(new Date());
			so.setPassword(spe.encryptPassword(clearTextPassword));
			
			success = ss.registerNewClient(so);
			message.put(ResponseResultEnum.isSuccessful, success.isSuccessful());
			message.put(ResponseResultEnum.statusCode, success.getErrorCode());
			message.put(ResponseResultEnum.link, success.getLink());
			message.put("id", so.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message.toJSONString();
	}
	
	@GET
	@Path("/viewStoreOwnerDetails/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String viewStoreOwnersProfile(@PathParam("id")Integer id){
		JSONObject j = ss.viewUserProfile(id);
		return j.toJSONString();
	}
	
	@PUT
	@Path("/loginStoreOwner/{email}-{password}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String loginClient(@PathParam("email")String email,@PathParam("password")String password){
		ResponseResult success = ss.loginUser(email,password);
		JSONObject message = new JSONObject();
		
		if(success.isSuccessful()){
			message.put(ResponseResultEnum.isSuccessful, success.isSuccessful());
			message.put(ResponseResultEnum.link, success.getLink());
			message.put("id", success.getErrorMessage());
		}else{
			message.put(ResponseResultEnum.isSuccessful, success.isSuccessful());
			message.put(ResponseResultEnum.statusCode, success.getErrorCode());
			message.put(ResponseResultEnum.statusMessage, success.getErrorMessage());
		}
		return message.toJSONString();
	}

	@PUT
	@Path("/updateStoreOwnerDetails")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateStoreOwnerDetails(String s){
		ObjectMapper mapper = new ObjectMapper();
		
		JSONObject message = new JSONObject();
		
		try {
			Storeowner so = mapper.readValue(s, Storeowner.class);
			message = ss.updateUserProfile(so);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message.toJSONString();
	}
	
	@DELETE
	@Path("/deleteStoreOwnerProfile/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deregisterStoreOwner(@PathParam("id")Integer id){
		JSONObject message = new JSONObject();
		ResponseResult r = ss.deleteStoreOwnerProfile(id);
		
		message.put(ResponseResultEnum.isSuccessful, r.isSuccessful());
		message.put(ResponseResultEnum.statusCode, r.getErrorCode());
		message.put(ResponseResultEnum.statusMessage, r.getErrorCode());
		
		return message.toJSONString();
	}
}
