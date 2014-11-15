package main.java.com.storehive.application.repository;

import java.util.List;

public interface JPAService<T, K>{

	public T findById(Class<T> t,K id);
	
	public List<T> findAll();
	
	public T findSingleItemByQuery(String query);
	
	public List<T> findByQuery(String query);
	
	public T createEntity(T entity);
	
	public void delete(T entity);
	
	public void deleteByQueryWithId(Integer id);
	
	public T update(T entity);
}
