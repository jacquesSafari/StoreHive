package main.java.com.storehive.application.services.crud;

import main.java.com.storehive.application.domain.Storeowner;
import main.java.com.storehive.application.repository.JPAService;


public interface StoreOwnerCrudService extends JPAService<Storeowner, Integer>{

	public Storeowner findByIdNativeQuery(Integer id);
}
