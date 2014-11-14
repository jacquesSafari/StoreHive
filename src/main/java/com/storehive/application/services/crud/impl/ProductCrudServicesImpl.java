package main.java.com.storehive.application.services.crud.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import main.java.com.storehive.application.domain.Product;
import main.java.com.storehive.application.services.crud.ProductCrudServices;

public class ProductCrudServicesImpl implements ProductCrudServices {
	
	@PersistenceContext(unitName="StoreHivePU")
    EntityManager em;
	
	@Override
	public Product findById(Class<Product> s,Integer id) {
		em.find(s.getClass(), id);	
		return null;
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findSingleItemByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product createEntity(Product entity) {
		this.em.persist(entity);
        this.em.flush();
        this.em.refresh(entity);
        return entity;
	}

	@Override
	public void delete(Product entity) {
		this.em.remove(entity);
	}

	@Override
	public Product update(Product entity) {
		return (Product)this.em.merge(entity);
	}

}
