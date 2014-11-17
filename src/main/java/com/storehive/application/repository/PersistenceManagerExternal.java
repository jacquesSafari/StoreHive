package main.java.com.storehive.application.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum PersistenceManagerExternal {
		INSTANCE;
		private EntityManagerFactory emFactory;
		private PersistenceManagerExternal() {
			// "jpa-example" was the value of the name attribute of the
			// persistence-unit element.
			emFactory = Persistence.createEntityManagerFactory("StoreHivePU");
		}
		public EntityManager getEntityManager() {
			return emFactory.createEntityManager();
		}
		public void close() {
			emFactory.close();
		}
}
