package main.java.com.storehive.application.services.crud.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import main.java.com.storehive.application.domain.Transaction;
import main.java.com.storehive.application.listeners.EMListener;
import main.java.com.storehive.application.services.crud.TransactionalCrudService;

public class TransactionalCrudServiceImpl implements TransactionalCrudService{

	private EntityManager em; 
	
	public TransactionalCrudServiceImpl(){
		em = EMListener.createEntityManager();
	}
	
	@Override
	public Transaction findById(Class<Transaction> t, Integer id) {
		 return em.find(t, id);
	}

	@Override
	public List<Transaction> findAll() {
		List<Transaction> transactionList = em.createQuery("SELECT t FROM Transaction t", Transaction.class).getResultList();
		return transactionList;
	}

	@Override
	public Transaction findSingleItemByQuery(String query) {
		Query q = em.createQuery(query);
		return (Transaction)q.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> findByQuery(String query) {
		return (List<Transaction>)em.createQuery("From Transaction where s_id = :id").setParameter("id", query).getResultList();
	}

	@Override
	public Transaction createEntity(Transaction entity) {
		em.getTransaction( ).begin( );
		em.persist(entity);
        em.flush();
        em.refresh(entity);
        em.getTransaction( ).commit();
        return entity;
	}

	@Override
	public void delete(Transaction entity) {
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
	public Transaction update(Transaction entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
