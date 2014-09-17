package com.storehive.app.services.crud;

import com.storehive.app.domain.StoreOwner;
import com.storehive.app.repository.MongoResporitoryService;

public interface StoreOwnerCrudService extends MongoResporitoryService<StoreOwner> {
	
	public boolean doesStoreOwnerExist(String username);
	
	public StoreOwner findStoreOwnerByUsername(String username);
}
