package main.java.com.storehive.application.services.crud.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import main.java.com.storehive.application.domain.Store;
import main.java.com.storehive.application.services.crud.StoreCrudServices;

public class StoreCrudServicesImpl implements StoreCrudServices {

	@PersistenceContext(unitName="StoreHivePU")
    EntityManager em;
	
	@Override
	public Store findById(Class<Store> s,Integer id) {
		em.find(s, id);	
		return null;
	}

	@Override
	public List<Store> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Store findSingleItemByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Store> findByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Store createEntity(Store entity) {
		this.em.persist(entity);
        this.em.flush();
        this.em.refresh(entity);
        return entity;
	}

	@Override
	public void delete(Store entity) {
		this.em.remove(entity);
	}

	@Override
	public Store update(Store entity) {
		return (Store)this.em.merge(entity);
	}


}
