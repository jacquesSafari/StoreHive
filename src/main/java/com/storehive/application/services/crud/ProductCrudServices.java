package main.java.com.storehive.application.services.crud;

import main.java.com.storehive.application.domain.Products;
import main.java.com.storehive.application.repository.MongoResporitoryService;

public interface ProductCrudServices extends MongoResporitoryService<Products> {
	
	public boolean doesProductExist(String productName);
	
	public Products findStoreByProperty(String property,String value);
}
