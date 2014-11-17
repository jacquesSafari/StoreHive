package main.java.com.storehive.application.services.crud.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.Transaction;

import main.java.com.storehive.application.domain.Transactiongood;
import main.java.com.storehive.application.listeners.EMListener;
import main.java.com.storehive.application.services.crud.TransactionalGoodCrudService;

public class TransactionGoodCrudServiceImpl implements TransactionalGoodCrudService {

	private EntityManager em; 
	
	public TransactionGoodCrudServiceImpl(){
		em = EMListener.createEntityManager();
	}
	
	@Override
	public Transactiongood findById(Class<Transactiongood> t, Integer id) {
		return em.find(t, id);
	}

	@Override
	public List<Transactiongood> findAll() {
		List<Transactiongood> transactionList = em.createQuery("SELECT t FROM Transactiongood t", Transactiongood.class).getResultList();
		return transactionList;
	}

	@Override
	public Transactiongood findSingleItemByQuery(String query) {
		Query q = em.createQuery(query);
		return (Transactiongood)q.getSingleResult();
	}

	@Override
	public List<Transactiongood> findByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transactiongood createEntity(Transactiongood entity) {
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
	public void delete(Transactiongood entity) {
		em.getTransaction( ).begin( );
		em.remove(entity);
        em.getTransaction( ).commit();
	}

	@Override
	public void deleteByQueryWithId(Integer id) {
		em.getTransaction().begin();
		em.createNativeQuery("DELETE from transaction where id = :id").setParameter("id", id).executeUpdate();
		em.getTransaction().commit();
	}

	@Override
	public Transactiongood update(Transactiongood entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
