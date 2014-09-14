package com.storehive.app.repository;

import java.util.List;

import com.storehive.app.utilities.Message;

public interface MongoResporitoryService<AnyType> {

	public Message createEntity(AnyType a);
	
	public AnyType findEntityById(String ID);
	
	public List<AnyType> getAllEntities();
	
	public Message updateEntity(AnyType a);
	
	public boolean deleteEntity(AnyType a);
	
}
