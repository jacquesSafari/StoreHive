package main.java.com.storehive.application.services.app.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.com.storehive.application.domain.Product;
import main.java.com.storehive.application.domain.Store;
import main.java.com.storehive.application.domain.Transaction;
import main.java.com.storehive.application.domain.Transactiongood;
import main.java.com.storehive.application.services.app.TransactionalServices;
import main.java.com.storehive.application.services.crud.ProductCrudServices;
import main.java.com.storehive.application.services.crud.StoreCrudServices;
import main.java.com.storehive.application.services.crud.TransactionalCrudService;
import main.java.com.storehive.application.services.crud.TransactionalGoodCrudService;
import main.java.com.storehive.application.services.crud.impl.ProductCrudServicesImpl;
import main.java.com.storehive.application.services.crud.impl.StoreCrudServicesImpl;
import main.java.com.storehive.application.services.crud.impl.TransactionGoodCrudServiceImpl;
import main.java.com.storehive.application.services.crud.impl.TransactionalCrudServiceImpl;
import main.java.com.storehive.application.utilities.ResponseResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TransactionalServicesImpl implements TransactionalServices {

	StoreCrudServices scs;
	TransactionalGoodCrudService tgcs;
	TransactionalCrudService tcs;
	ProductCrudServices pcs;
	
	public TransactionalServicesImpl(){
		scs = new StoreCrudServicesImpl();
		tgcs = new TransactionGoodCrudServiceImpl();
		tcs = new TransactionalCrudServiceImpl();
		pcs = new ProductCrudServicesImpl();
	}
	
	@Override
	public ResponseResult sellProduct(JSONObject transaction) {
		ResponseResult r = new ResponseResult();
		int total = 0, transactionTotal = 0;
		Store current = scs.findById(Store.class, Integer.valueOf(transaction.get("storeId").toString()));
		Transaction t = new Transaction();
		t.setStore(current);
		t.setTimeStamp(new Date());
		
		List<Transactiongood> tgoods = new ArrayList<Transactiongood>();
		JSONArray ja = (JSONArray)transaction.get("productList");
		try{
		for(int i = 0;i < ja.size();i++){
			JSONObject product = (JSONObject)ja.get(i);
			Product p = pcs.findById(Product.class, Integer.valueOf(product.get("productId").toString()));
			int quantity = Integer.valueOf(product.get("quantity").toString());
			int leftOverProductQuantity = p.getProductQuantity()-quantity;
			if(leftOverProductQuantity>=0){
				//	initialize transaction object
				Transactiongood good = new Transactiongood();
				good.setProduct(p);
				good.setQuantity(quantity);
				total += (quantity*p.getProductPrice());
				good.setTotal(quantity*p.getProductPrice());
				good.setTransaction(t);
				tgoods.add(good);
				tgcs.createEntity(good);
				
				p.setProductQuantity(leftOverProductQuantity);
				pcs.update(p);
				
				transactionTotal++;
			}
		}
		if(transactionTotal>0){
			t.setTransactiongoods(tgoods);
			t.setAmountDue(total);
			tcs.createEntity(t);
			r.setErrorMessage("Number transactions succeeded: "+transactionTotal);
		}else{
			r.setErrorMessage("No transactions succeeded");
		}
		}catch(Exception e){
			r.setErrorMessage("Something went wrong while making sales");
			e.printStackTrace();
		}
		return r;
	}

}
