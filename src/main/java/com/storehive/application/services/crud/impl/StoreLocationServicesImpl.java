package main.java.com.storehive.application.services.crud.impl;

import java.util.List;

import javax.persistence.EntityManager;

import main.java.com.storehive.application.domain.Storelocation;
import main.java.com.storehive.application.listeners.EMListener;
import main.java.com.storehive.application.services.crud.StoreLocationServices;

public class StoreLocationServicesImpl implements StoreLocationServices {

	private EntityManager em; 
	
	public StoreLocationServicesImpl(){
		em = EMListener.createEntityManager();
	}
	
	@Override
	public Storelocation findById(Class<Storelocation> t, Integer id) {
		return em.find(t, id);	
	}

	@Override
	public List<Storelocation> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Storelocation findSingleItemByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Storelocation> findByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Storelocation createEntity(Storelocation entity) {
		em.getTransaction( ).begin( );
		em.persist(entity);
        em.flush();
        em.refresh(entity);
        em.getTransaction( ).commit();
        return entity;
	}

	@Override
	public void delete(Storelocation entity) {
		em.getTransaction( ).begin( );
		em.remove(entity);
        em.getTransaction( ).commit();
	}

	@Override
	public Storelocation update(Storelocation entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
