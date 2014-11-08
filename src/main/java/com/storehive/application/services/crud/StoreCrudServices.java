package main.java.com.storehive.application.services.crud;

import main.java.com.storehive.application.domain.Store;
import main.java.com.storehive.application.repository.MongoResporitoryService;

public interface StoreCrudServices extends MongoResporitoryService<Store> {

	public Store findStoreByProperty(String property,String value);
}
