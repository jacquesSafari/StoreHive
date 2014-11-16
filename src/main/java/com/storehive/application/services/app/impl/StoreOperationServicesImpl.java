package main.java.com.storehive.application.services.app.impl;

import java.util.ArrayList;
import java.util.List;

import main.java.com.storehive.application.domain.Store;
import main.java.com.storehive.application.domain.Storelocation;
import main.java.com.storehive.application.services.app.StoreOperationServices;
import main.java.com.storehive.application.services.crud.StoreCrudServices;
import main.java.com.storehive.application.services.crud.impl.StoreCrudServicesImpl;
import main.java.com.storehive.application.utilities.ErrorCodes;
import main.java.com.storehive.application.utilities.ResponseResult;

import org.joda.time.DateTime;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class StoreOperationServicesImpl implements StoreOperationServices{

	StoreCrudServices scs;
	
	public StoreOperationServicesImpl(){
		scs = new StoreCrudServicesImpl();
	}
	
	@Override
	public ResponseResult registerStore(Store s) {
		ResponseResult r = new ResponseResult();
		try{
			Store created = scs.createEntity(s);
		
			r.setLink("/api/storeServices/viewStoreDetails/"+created.getId());
			r.setErrorMessage(""+created.getId()+"");
			r.setSuccessful(true);
		}catch(Exception e){
			r.setErrorCode(ErrorCodes.STORE_ERR_1);
			r.setErrorMessage("There was an error creating the store");
			r.setSuccessful(false);
			e.printStackTrace();
		}
		return r;
	}
	
	@Override
	public JSONObject viewStoreDetails(Integer id) {
		JSONObject j = new JSONObject();
		Store s = scs.findById(Store.class, id);
		
		if(s!=null){
			j.put("shopName", s.getShopName());
			j.put("description", s.getDescription());
		}
		return j;
	}

	@Override
	public JSONObject openStore(JSONObject openStore) {
		JSONObject json = new JSONObject();
		
		Storelocation sl = new Storelocation();
		sl.setLatitude(openStore.get("latitude").toString());
		sl.setLongitude(openStore.get("longitude").toString());
		List<Storelocation> storeLocations = new ArrayList<Storelocation>();
		storeLocations.add(sl);
		
		Integer storeId = Integer.valueOf(openStore.get("storeId").toString());
		
		Store storeToOpen = new Store();
	    storeToOpen.setId(storeId);
		storeToOpen.setLastOpenedDate(new DateTime().toDate());
		storeToOpen.setIsOpen(openStore.get("isOpen").toString());
		storeToOpen.setStorelocations(storeLocations);
		
		Store updated = scs.update(storeToOpen);
		
		if(updated!=null){
			json.put("isSuccessfull", true);
		}else{
			json.put("isSuccessfull", false);
		}
		
		return json;
	}

	@Override
	public JSONObject closeStore(JSONObject closeStore) {
		JSONObject json = new JSONObject();
		
		
		Integer storeId = Integer.valueOf(closeStore.get("storeId").toString());
		
		Store storeToOpen = new Store();
	    storeToOpen.setId(storeId);
		storeToOpen.setLastOpenedDate(new DateTime().toDate());
		storeToOpen.setIsOpen(closeStore.get("isOpen").toString());
		
		Store updated = scs.update(storeToOpen);
		
		if(updated!=null){
			json.put("isSuccessfull", true);
		}else{
			json.put("isSuccessfull", false);
		}
		
		return json;
	}

}
