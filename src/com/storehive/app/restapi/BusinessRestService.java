package com.storehive.app.restapi;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;
import org.json.simple.JSONObject;

import com.storehive.app.services.app.BusinessServices;
import com.storehive.app.services.app.impl.BusinessServicesImpl;
import com.storehive.app.utilities.ResponseResult;
import com.storehive.app.utilities.ResponseResultEnum;

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
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
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
	
	@GET
	@Path("push")
	@Produces(SseFeature.SERVER_SENT_EVENTS)
	@SuppressWarnings("unchecked")
	public EventOutput testRest(){
		final EventOutput eventOutput = new EventOutput();
		
		new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        Thread.sleep(5000);
                    	final OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
                        eventBuilder.name("message-to-client");
                        eventBuilder.data(String.class,"Hello world " + i + "!");
                        
                        final OutboundEvent event = eventBuilder.build();
                        eventOutput.write(event);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(
                        "Error when writing the event.", e);
                } catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
                    try {
                        eventOutput.close();
                    } catch (IOException ioClose) {
                        throw new RuntimeException(
                            "Error when closing the event output.", ioClose);
                    }
                }
            }
            
        }).start();
        return eventOutput;
	}
	
	
}
