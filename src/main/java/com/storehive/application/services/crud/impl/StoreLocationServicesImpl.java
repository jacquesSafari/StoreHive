package main.java.com.storehive.application.services.crud.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import main.java.com.storehive.application.domain.Storelocation;
import main.java.com.storehive.application.domain.Storeowner;
import main.java.com.storehive.application.listeners.EMListener;
import main.java.com.storehive.application.services.crud.StoreLocationCrudServices;

public class StoreLocationServicesImpl implements StoreLocationCrudServices {

	private EntityManager em; 
	
	public StoreLocationServicesImpl(){
		em = EMListener.createEntityManager();
	}
	
	public StoreLocationServicesImpl(EntityManager em){
		this.em = em;
	}
	
	@Override
	public Storelocation findById(Class<Storelocation> t, Integer id) {
		return em.find(t, id);	
	}

	@Override
	public List<Storelocation> findAll() {
		return (List<Storelocation>)em.createQuery("from Storelocation").getResultList();
	}

	@Override
	public Storelocation findSingleItemByQuery(String query) {
		return null;
	}

	@Override
	public List<Storelocation> findByQuery(String query) {
		return null;
	}

	@Override
	public Storelocation createEntity(Storelocation entity) {
		EntityTransaction t = em.getTransaction();
		try{
			t.begin();
			em.persist(entity);
	        em.flush();
	        em.refresh(entity);
	        t.commit();
		}catch(Exception e){
			if(t.isActive()) {
                t.rollback();
            }
		}
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

	@Override
	public void deleteByQueryWithId(Integer id) {
		em.getTransaction().begin();
		em.createNativeQuery("DELETE from storelocation where s_id = :sid").setParameter("sid", id).executeUpdate();
		em.getTransaction().commit();
	}

}
