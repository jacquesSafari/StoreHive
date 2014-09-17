package com.storehive.app.utilities;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.media.sse.EventInput;
import org.glassfish.jersey.media.sse.InboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;

public class Test {

	public static void main(String[] args) {
		Client client = ClientBuilder.newBuilder().register(SseFeature.class).build();
		
		WebTarget target = client.target("http://localhost:8080/api/business/push");
		 
		EventInput eventInput = target.request().get(EventInput.class);
		while (!eventInput.isClosed()) {
		    final InboundEvent inboundEvent = eventInput.read();
		    if (inboundEvent == null) {
		        break;
		    }
		    System.out.println(inboundEvent.getName() + "; "+ inboundEvent.readData(String.class));
		}
	}

}
