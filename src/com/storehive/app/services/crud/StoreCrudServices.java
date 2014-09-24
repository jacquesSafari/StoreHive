package com.storehive.app.services.crud;

import com.storehive.app.domain.Store;
import com.storehive.app.repository.MongoResporitoryService;

public interface StoreCrudServices extends MongoResporitoryService<Store> {

	public Store findStoreByProperty(String property,String value);
}
