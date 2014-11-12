package main.java.com.storehive.application.conf;

import java.util.Collections;
import java.util.Set;
import javax.ws.rs.core.Application;

//tells our application server which JAX-RS components we want to register
public abstract class JAXRSConfiguration extends Application{

	private static final Set<Object> emptySet = Collections.emptySet();
	
	public abstract Set<Class<?>> getClasses();
	
	public Set<Object> getSingletons() {
		return emptySet;
	}
}
