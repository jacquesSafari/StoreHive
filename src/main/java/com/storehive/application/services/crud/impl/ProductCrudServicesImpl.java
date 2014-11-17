package main.java.com.storehive.application.services.crud.impl;

import java.util.List;

import javax.persistence.EntityManager;

import main.java.com.storehive.application.domain.Product;
import main.java.com.storehive.application.listeners.EMListener;
import main.java.com.storehive.application.services.crud.ProductCrudServices;

public class ProductCrudServicesImpl implements ProductCrudServices {
	
	private EntityManager em; 
	
	public ProductCrudServicesImpl() {
		em = EMListener.createEntityManager();
	}
	
	public ProductCrudServicesImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Product findById(Class<Product> s,Integer id) {
		return em.find(s, id);	
	}

	@Override
	public List<Product> findAll() {
		List<Product> productList = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
		return productList;
	}

	@Override
	public Product findSingleItemByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findByQuery(String query) {
		List<Product> productList = em.createQuery("FROM Product where s_id = :id", Product.class).setParameter("id", query).getResultList();
		return productList;
	}

	@Override
	public Product createEntity(Product entity) {
		em.getTransaction( ).begin( );
		em.persist(entity);
        em.flush();
        em.refresh(entity);
        em.getTransaction( ).commit();
        return entity;
	}

	@Override
	public void delete(Product entity) {
		em.getTransaction( ).begin( );
		em.remove(entity);
        em.getTransaction( ).commit();
	}

	@Override
	public Product update(Product entity) {
		Product old = findById(Product.class, entity.getId()); 
		em.getTransaction().begin();
		old.setProductName(entity.getProductName());
		old.setProductDescription(entity.getProductDescription());
		old.setProductPrice(entity.getProductPrice());
		old.setProductQuantity(entity.getProductQuantity());
		old.setCategory(entity.getCategory());
		old.setStore(entity.getStore());
		em.getTransaction().commit();
		return old;
	}
	
	@Override
	public void deleteByQueryWithId(Integer id) {
		em.getTransaction().begin();
		em.createNativeQuery("DELETE from product where id = :id").setParameter("id", id).executeUpdate();
		em.getTransaction().commit();
		
	}

}
