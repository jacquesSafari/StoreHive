package main.java.com.storehive.application.services.crud.impl;

import java.util.List;

import javax.persistence.EntityManager;

import main.java.com.storehive.application.domain.Store;
import main.java.com.storehive.application.listeners.EMListener;
import main.java.com.storehive.application.services.crud.StoreCrudServices;

public class StoreCrudServicesImpl implements StoreCrudServices {

	private EntityManager em; 
	
	public StoreCrudServicesImpl() {
		em = EMListener.createEntityManager();
	}
	@Override
	public Store findById(Class<Store> s,Integer id) {
		return em.find(s, id);	
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
		em.getTransaction( ).begin( );
		em.persist(entity);
        em.flush();
        em.refresh(entity);
        em.getTransaction( ).commit();
        return entity;
	}

	@Override
	public void delete(Store entity) {
		em.getTransaction( ).begin( );
		em.remove(entity);
        em.getTransaction( ).commit();
	}

	@Override
	public Store update(Store entity) {
		Store old = findById(Store.class, entity.getId()); 
		em.getTransaction().begin();
		old.setIsOpen(entity.getIsOpen());
		old.setStorelocation(entity.getStorelocation());
		old.setLastOpenedDate(entity.getLastOpenedDate());
		em.getTransaction().commit();
		return old;
	}


}
