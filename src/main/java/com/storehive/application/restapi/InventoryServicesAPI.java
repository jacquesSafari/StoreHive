package main.java.com.storehive.application.restapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.java.com.storehive.application.services.app.InventoryServices;
import main.java.com.storehive.application.services.app.impl.InventoryServicesImpl;
import main.java.com.storehive.application.utilities.ResponseResult;
import main.java.com.storehive.application.utilities.ResponseResultEnum;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Path("/inventoryServices")
@SuppressWarnings("unchecked")
public class InventoryServicesAPI {

	private InventoryServices ivs;
	
	public InventoryServicesAPI(){
		ivs = new InventoryServicesImpl();
	}
	
	@POST
	@Path("/addProductToInventory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addProduct(String newProd){
		JSONObject message = new JSONObject();
		try{
		JSONParser jp = new JSONParser();
		Object c = jp.parse(newProd);
		JSONObject newProduct = (JSONObject)c;
		
		ResponseResult success = ivs.addProductToStore(newProduct);

		if(success.isSuccessful())
			message.put(ResponseResultEnum.link, success.getLink());
		
		message.put(ResponseResultEnum.isSuccessful, success.isSuccessful());
		message.put(ResponseResultEnum.statusCode, success.getErrorCode());
		message.put(ResponseResultEnum.statusMessage, success.getErrorMessage());
		}catch(Exception e){
			e.printStackTrace();
		}
		return message.toJSONString();
	}

//	@Deprecated
	@PUT
	@Path("/updateProductDetails")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateProductDetails(String updatedProduct){
		JSONObject success = new JSONObject();
		try{
		JSONParser jp = new JSONParser();
		Object c = jp.parse(updatedProduct);
		JSONObject toBeUpdatedProduct = (JSONObject)c;
		
		success = ivs.updateProduct(toBeUpdatedProduct);

		}catch(Exception e){
			e.printStackTrace();
		}
		return success.toJSONString();
	}
	
	@DELETE
	@Path("/removeProduct/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String removeProduct(@PathParam("id")Integer id){
		JSONObject message = new JSONObject();
		ResponseResult r = ivs.deleteProductFromInventory(id);
		
		message.put(ResponseResultEnum.isSuccessful, r.isSuccessful());
		message.put(ResponseResultEnum.statusCode, r.getErrorCode());
		message.put(ResponseResultEnum.statusMessage, r.getErrorCode());
		
		return message.toJSONString();
	}
	
	@GET
	@Path("/viewProduct/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String viewProduct(@PathParam("id")Integer id){
		return ivs.viewProductDetails(id).toJSONString();
	}
	
	@GET
	@Path("/viewAllProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public String viewAllProducts(){
		return ivs.getAllProducts().toJSONString();
	}
	
	@GET
	@Path("/getAllCategories")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllCategories(){
		return ivs.getAllCategories().toJSONString();
	}
}
