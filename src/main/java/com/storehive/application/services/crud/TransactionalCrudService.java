package main.java.com.storehive.application.services.crud;

import main.java.com.storehive.application.domain.Transaction;
import main.java.com.storehive.application.repository.JPAService;

public interface TransactionalCrudService extends JPAService<Transaction, Integer>{

}
