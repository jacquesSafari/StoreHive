package main.java.com.storehive.application.repository;

import java.util.List;

import main.java.com.storehive.application.utilities.ResponseResult;

public interface MongoResporitoryService<AnyType> {

	public ResponseResult createEntity(AnyType a);
	
	public AnyType findEntityById(String ID);
	
	public List<AnyType> getAllEntities();
	
	public ResponseResult updateEntity(AnyType a);
	
	public boolean deleteEntity(AnyType a);
	
}
