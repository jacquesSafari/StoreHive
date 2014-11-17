package main.java.com.storehive.application.services.crud.impl;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
	public Storeowner findById(Class<Storeowner> s,Integer id) throws NoResultException{
		em.getTransaction().begin();
		em.flush();
		Storeowner st = em.find(Storeowner.class, id);
		em.getTransaction().commit();
		return st;
	}

	@Override
	public List<Storeowner> findAll() {
		List<Storeowner> storeOwnerList = em.createQuery("SELECT s FROM Storeowner s", Storeowner.class).getResultList();
		return storeOwnerList;
	}

	@Override
	public Storeowner findSingleItemByQuery(String query) throws NoResultException{
		Query q = em.createQuery("select s from Storeowner s where s.email = :email").setParameter("email", query);
		return (Storeowner)q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Storeowner> findByQuery(String query) {
		List<Storeowner> raw = em.createNativeQuery("select * from storeowner where email = :email").setParameter("email", query).getResultList();
		return (raw.size()>0)?raw:null;
	}

	@Override
	public Storeowner createEntity(Storeowner entity) throws ConstraintViolationException {
		EntityTransaction t = em.getTransaction();
		Storeowner entityInserted = null;
		try{
			t.begin();
			em.persist(entity);
	        em.flush();
	        em.refresh(entity);
	        t.commit();
	        
	        entityInserted = findById(Storeowner.class,entity.getId());
		}catch(Exception e){
			if(t.isActive()) {
                t.rollback();
            }
		}
        return entityInserted;
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

	@Override
	public void deleteByQueryWithId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Storeowner findByIdNativeQuery(Integer id) {
		Query q = em.createNativeQuery("select * from storeowner where id = :id",Storeowner.class).setParameter("id", id);
		Storeowner s = (Storeowner)q.getSingleResult();
		return s;
	}

}
