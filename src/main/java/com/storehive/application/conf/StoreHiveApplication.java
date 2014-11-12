package main.java.com.storehive.application.conf;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;

import main.java.com.storehive.application.restapi.InventoryServicesAPI;
import main.java.com.storehive.application.restapi.StoreOwnerServicesAPI;
import main.java.com.storehive.application.restapi.StoreServicesAPI;

@ApplicationPath("api")//defines relative base url for all services in deployment
public class StoreHiveApplication extends JAXRSConfiguration {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	
	
	public StoreHiveApplication() {
		
		singletons.add(new StoreOwnerServicesAPI());//add our rest resources
		singletons.add(new StoreServicesAPI());
		singletons.add(new InventoryServicesAPI());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return empty;
	}
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
