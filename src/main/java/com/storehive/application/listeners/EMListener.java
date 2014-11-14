package main.java.com.storehive.application.listeners;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EMListener implements ServletContextListener {

	private static EntityManagerFactory emf;

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	emf.close();
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
       emf = Persistence.createEntityManagerFactory("StoreHivePU");
    }
	
    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }

        return emf.createEntityManager();
    }
}
