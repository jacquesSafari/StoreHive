package main.java.com.storehive.application.restapi;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.java.com.storehive.application.domain.Store;
import main.java.com.storehive.application.domain.Storeowner;
import main.java.com.storehive.application.services.app.StoreOperationServices;
import main.java.com.storehive.application.services.app.impl.StoreOperationServicesImpl;
import main.java.com.storehive.application.services.crud.StoreOwnerCrudService;
import main.java.com.storehive.application.services.crud.impl.StoreOwnerCrudServiceImpl;
import main.java.com.storehive.application.utilities.ResponseResult;
import main.java.com.storehive.application.utilities.ResponseResultEnum;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Path("/storeServices")
@SuppressWarnings("unchecked")
public class StoreServicesAPI {
	
	private StoreOperationServices ss;
	private StoreOwnerCrudService scs;
	
	public StoreServicesAPI(){
		ss = new StoreOperationServicesImpl();
		scs = new StoreOwnerCrudServiceImpl();
	}
	
	@POST
	@Path("/registerNewStore")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String registerClient(String s){
		ResponseResult r = new ResponseResult();
		JSONObject message = new JSONObject();
		JSONParser jp = new JSONParser();
		Object c;
		try {
			c = jp.parse(s);
			JSONObject j = (JSONObject)c;
			
			Integer storeOwnerId = Integer.valueOf(j.get("ownerId").toString());
			System.out.println(storeOwnerId);
			Storeowner storeBelongTo = scs.findById(Storeowner.class, storeOwnerId);

			Store store = new Store();
			store.setDescription(j.get("description").toString());
			store.setShopName(j.get("shopName").toString());
			store.setOwnerEmail(storeBelongTo.getEmail());
			store.setStoreowner(storeBelongTo);
			store.setIsOpen("false");
			
			r = ss.registerStore(store);
			
			if(r.isSuccessful()){
				message.put(ResponseResultEnum.isSuccessful, r.isSuccessful());
				message.put(ResponseResultEnum.link, r.getLink());
				message.put("storeId", r.getErrorMessage());
			}else{
				message.put(ResponseResultEnum.isSuccessful, r.isSuccessful());
				message.put(ResponseResultEnum.statusCode, r.getErrorCode());
				message.put(ResponseResultEnum.statusMessage, r.getErrorMessage());
			}
			
		}catch (NoResultException e) {
			message.put(ResponseResultEnum.isSuccessful, false);
			message.put(ResponseResultEnum.statusMessage, "No User Found With That Id");
			e.printStackTrace();
		}catch (ParseException e) {
			message.put(ResponseResultEnum.isSuccessful, r.isSuccessful());
			message.put(ResponseResultEnum.statusCode, r.getErrorCode());
			message.put(ResponseResultEnum.statusMessage, e);
			e.printStackTrace();
		}catch (NullPointerException e) {
			message.put(ResponseResultEnum.isSuccessful, false);
			message.put(ResponseResultEnum.statusMessage, "Something terrible went wrong, a null pointer");
			e.printStackTrace();
		}
		
		return message.toJSONString();
	}
	
	@POST
	@Path("/registerNewStorePart2")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String registerClientPart2(String s){
		ResponseResult r = new ResponseResult();
		JSONObject message = new JSONObject();
		JSONParser jp = new JSONParser();
		Object c;
		try {
			c = jp.parse(s);
			JSONObject j = (JSONObject)c;
			
			Integer storeOwnerId = Integer.valueOf(j.get("ownerId").toString());
			System.out.println(storeOwnerId);
			Storeowner storeBelongTo = scs.findById(Storeowner.class, storeOwnerId);

			Store store = new Store();
			store.setDescription(j.get("description").toString());
			store.setShopName(j.get("shopName").toString());
			store.setOwnerEmail(storeBelongTo.getEmail());
			store.setStoreowner(storeBelongTo);
			store.setIsOpen("false");
			
			r = ss.registerStore(store);
			
			if(r.isSuccessful()){
				message.put(ResponseResultEnum.isSuccessful, r.isSuccessful());
				message.put(ResponseResultEnum.link, r.getLink());
				message.put("storeId", r.getErrorMessage());
			}else{
				message.put(ResponseResultEnum.isSuccessful, r.isSuccessful());
				message.put(ResponseResultEnum.statusCode, r.getErrorCode());
				message.put(ResponseResultEnum.statusMessage, r.getErrorMessage());
			}
			
		}catch (NoResultException e) {
			message.put(ResponseResultEnum.isSuccessful, false);
			message.put(ResponseResultEnum.statusMessage, "No User Found With That Id");
			e.printStackTrace();
		}catch (ParseException e) {
			message.put(ResponseResultEnum.isSuccessful, r.isSuccessful());
			message.put(ResponseResultEnum.statusCode, r.getErrorCode());
			message.put(ResponseResultEnum.statusMessage, e);
			e.printStackTrace();
		}catch (NullPointerException e) {
			message.put(ResponseResultEnum.isSuccessful, false);
			message.put(ResponseResultEnum.statusMessage, "Something terrible went wrong, a null pointer");
			e.printStackTrace();
		}
		
		return message.toJSONString();
	}
	
	
	@GET
	@Path("/viewStoreDetails/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String viewStoreOwnersProfile(@PathParam("id")Integer id){
		JSONObject j = ss.viewStoreDetails(id);
		return j.toJSONString();
	}
	
	@POST
	@Path("/openStore")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String openStore(String s){
		
		JSONObject message = new JSONObject();
		JSONParser jp = new JSONParser();
		Object c;
		try {
			c = jp.parse(s);
			JSONObject j = (JSONObject)c;
			
			message = ss.openStore(j);
		}catch(Exception e){
			e.printStackTrace();
		}
		return message.toJSONString();
	}
	
	@POST
	@Path("/closeStore")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String closeStore(String s){

		JSONObject message = new JSONObject();
		JSONParser jp = new JSONParser();
		Object c;
		try {
			c = jp.parse(s);
			JSONObject j = (JSONObject)c;
			
			message = ss.closeStore(j);
		}catch(Exception e){
			e.printStackTrace();
		}
		return message.toJSONString();
	}
}
