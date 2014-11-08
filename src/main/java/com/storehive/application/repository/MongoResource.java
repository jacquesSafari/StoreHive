package main.java.com.storehive.application.repository;

import java.net.UnknownHostException;

import javax.validation.constraints.NotNull;

import org.jetbrains.annotations.Nullable;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public enum MongoResource {

	INSTANCE;
	private MongoClient mongoClient;
	
	private MongoResource() { 
		try { 
			if (mongoClient == null) 
				mongoClient = getClient(); 
		} catch (Exception e){ 
			e.printStackTrace(); 
		} 
	} 
	
	@Nullable 
	private MongoClient getClient() { 
		try { 
			return new MongoClient( "localhost", 27017); 
		}catch (UnknownHostException uh) { 
			uh.printStackTrace(); 
			} 
		return null; 
	} 
	
	@Nullable
	public Datastore getDatastore(@NotNull String dbName) {
	    return new Morphia().createDatastore(mongoClient,dbName);
	}
}
