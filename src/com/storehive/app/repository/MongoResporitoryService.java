package com.storehive.app.repository;

import java.util.List;

public interface MongoResporitoryService<AnyType> {

	public String createEntity(AnyType a);
	
	public AnyType findEntityById(String ID);
	
	public List<AnyType> getAllEntities();
	
	public String updateEntity(AnyType a);
	
	public boolean deleteEntity(AnyType a);
	
}
