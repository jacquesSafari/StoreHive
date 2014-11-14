package main.java.com.storehive.application.services.crud.impl;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import main.java.com.storehive.application.domain.Storeowner;
import main.java.com.storehive.application.listeners.EMListener;
import main.java.com.storehive.application.services.crud.StoreOwnerCrudService;

@Stateful
public class StoreOwnerCrudServiceImpl implements StoreOwnerCrudService{

	private EntityManager em; 
	
	public StoreOwnerCrudServiceImpl(){
		em = EMListener.createEntityManager();
	}
	
	@Override
	public Storeowner findById(Class<Storeowner> s,Integer id) {
		return em.find(s, id);
	}

	@Override
	public List<Storeowner> findAll() {
		List<Storeowner> storeOwnerList = em.createQuery("SELECT s FROM Storeowner s", Storeowner.class).getResultList();
		return storeOwnerList;
	}

	@Override
	public Storeowner findSingleItemByQuery(String query) throws NoResultException{
		Query q = em.createQuery(query);
		return (Storeowner)q.getSingleResult();
	}

	@Override
	public List<Storeowner> findByQuery(String query) {
		return null;
	}

	@Override
	public Storeowner createEntity(Storeowner entity) throws ConstraintViolationException {
		em.getTransaction( ).begin( );
		em.persist(entity);
        em.flush();
        em.refresh(entity);
        em.getTransaction( ).commit();
        return entity;
	}

	@Override
	public void delete(Storeowner entity) {
		em.getTransaction( ).begin( );
		em.remove(entity);
        em.getTransaction( ).commit();
	}

	@Override
	public Storeowner update(Storeowner entity) {
		return (Storeowner)em.merge(entity);
	}

}
