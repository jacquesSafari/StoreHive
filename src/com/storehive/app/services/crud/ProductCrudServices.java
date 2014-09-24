package com.storehive.app.services.crud;

import com.storehive.app.domain.Products;
import com.storehive.app.repository.MongoResporitoryService;

public interface ProductCrudServices extends MongoResporitoryService<Products> {
	
	public boolean doesProductExist(String productName);
	
	public Products findStoreByProperty(String property,String value);
}
