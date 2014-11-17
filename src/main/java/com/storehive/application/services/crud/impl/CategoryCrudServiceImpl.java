package main.java.com.storehive.application.services.crud.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import main.java.com.storehive.application.domain.Category;
import main.java.com.storehive.application.listeners.EMListener;
import main.java.com.storehive.application.services.crud.CategoryCrudService;

public class CategoryCrudServiceImpl implements CategoryCrudService {

	private EntityManager em; 
	
	public CategoryCrudServiceImpl() {
		em = EMListener.createEntityManager();
	}
	
	@Override
	public Category findById(Class<Category> s,Integer id) {
		return em.find(s, id);	
	}

	@Override
	public List<Category> findAll() {
		List<Category> categoryList = em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
		return categoryList;
	}

	@Override
	public Category findSingleItemByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> findByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category createEntity(Category entity) {
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
	public void delete(Category entity) {
		em.getTransaction( ).begin( );
		em.remove(entity);
        em.getTransaction( ).commit();
	}

	@Override
	public Category update(Category entity) {
		return (Category)this.em.merge(entity);
	}

	@Override
	public void deleteByQueryWithId(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
