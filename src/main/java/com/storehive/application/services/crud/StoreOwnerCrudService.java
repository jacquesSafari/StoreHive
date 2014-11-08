package main.java.com.storehive.application.services.crud;

import main.java.com.storehive.application.domain.StoreOwner;
import main.java.com.storehive.application.repository.MongoResporitoryService;

public interface StoreOwnerCrudService extends MongoResporitoryService<StoreOwner> {
	
	public boolean doesStoreOwnerExist(String username);
	
	public StoreOwner findStoreOwnerByUsername(String username);
}
