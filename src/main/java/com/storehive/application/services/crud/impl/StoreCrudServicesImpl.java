package main.java.com.storehive.application.services.crud.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import main.java.com.storehive.application.domain.Product;
import main.java.com.storehive.application.domain.Store;
import main.java.com.storehive.application.listeners.EMListener;
import main.java.com.storehive.application.services.crud.StoreCrudServices;

public class StoreCrudServicesImpl implements StoreCrudServices {

	private EntityManager em; 
	
	public StoreCrudServicesImpl() {
		em = EMListener.createEntityManager();
	}
	
	public StoreCrudServicesImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Store findById(Class<Store> s,Integer id) {
		return em.find(s, id);	
	}

	@Override
	public List<Store> findAll() {
		List<Store> storeList = em.createQuery("SELECT p FROM Store p", Store.class).getResultList();
		return storeList;
	}

	@Override
	public Store findSingleItemByQuery(String query) {
		
		return null;
	}

	@Override
	public List<Store> findByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Store createEntity(Store entity) {
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
		old.setLastOpenedDate(entity.getLastOpenedDate());
		
		if(entity.getStorelocations()!=null)
			old.getStorelocations().add(entity.getStorelocations().get(0));
		
		em.flush();
        em.refresh(old);
        
		em.getTransaction().commit();
		return old;
	}
	@Override
	public void deleteByQueryWithId(Integer id) {
		em.getTransaction().begin();
		int deleted = em.createNativeQuery("DELETE from store where s_id = :sid").setParameter("sid", id).executeUpdate();
		em.getTransaction().commit();
	}
}
