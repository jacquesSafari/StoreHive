package main.java.com.storehive.application.services.crud.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import main.java.com.storehive.application.domain.Category;
import main.java.com.storehive.application.services.crud.CategoryCrudService;

public class CategoryCrudServiceImpl implements CategoryCrudService {

	@PersistenceContext(unitName="StoreHivePU")
    EntityManager em;
	
	@Override
	public Category findById(Class<Category> s,Integer id) {
		em.find(s.getClass(), id);	
		return null;
	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return null;
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
		this.em.persist(entity);
        this.em.flush();
        this.em.refresh(entity);
        return entity;
	}

	@Override
	public void delete(Category entity) {
		this.em.remove(entity);
	}

	@Override
	public Category update(Category entity) {
		return (Category)this.em.merge(entity);
	}

}
