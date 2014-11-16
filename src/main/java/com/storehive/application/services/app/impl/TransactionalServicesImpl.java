package main.java.com.storehive.application.services.app.impl;

import java.util.ArrayList;
import java.util.Calendar;
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
				
				Transactiongood good = new Transactiongood();
				good.setProduct(p);
				good.setQuantity(quantity);
				total += (quantity*p.getProductPrice());
				good.setTotal(quantity*p.getProductPrice());
				good.setTransaction(t);
				tgoods.add(good);
//				tgcs.createEntity(good);
				
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

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray viewAllTransactionsToday(Integer id) {
		List<Transaction> allToday = tcs.findByQuery(id.toString());
		
		JSONArray transactionList = new JSONArray();
		
		if(allToday!=null&&allToday.size()>0){
			Calendar current = Calendar.getInstance();
			
			for(Transaction t:allToday){
				JSONObject transaction = new JSONObject();
				transaction.put("storeId", id);
				
				JSONArray transactionItemList = new JSONArray();
				
				String temp = current.get(Calendar.YEAR)+"-"+current.get(Calendar.MONTH)+"-"+current.get(Calendar.DATE);
				Calendar convert = dateToCalendar(t.getTimeStamp());
				String now = convert.get(Calendar.YEAR)+"-"+convert.get(Calendar.MONTH)+"-"+convert.get(Calendar.DATE);
				
				if(now.equals(temp)){
					JSONObject transactionItem = new JSONObject();
					
					for(Transactiongood tg: t.getTransactiongoods()){
						transactionItem.put("productName", tg.getProduct().getProductName());
						transactionItem.put("productQuantity", tg.getQuantity());
						transactionItem.put("productPrice", tg.getTotal());
						transactionItem.put("link","/inventoryServices/viewProduct/"+tg.getProduct().getId());
						transactionItemList.add(transactionItem);
					}
				}
				
				transaction.put("transactionList", transactionItemList);
				transaction.put("transactionTotal", ""+t.getAmountDue());
				transactionList.add(transaction);
			}
			
		}else{
			JSONObject noTransactions = new JSONObject();
			noTransactions.put("NoTransactions", "No Transactions for : "+new Date().toString());
		}
		return transactionList;
	}

	@Override
	public JSONArray viewAllTransactionsForDate(Integer id,String date) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Calendar dateToCalendar(Date date){ 
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(date);
	  return cal;
	}
}
