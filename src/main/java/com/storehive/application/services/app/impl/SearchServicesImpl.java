package main.java.com.storehive.application.services.app.impl;

import java.util.List;

import main.java.com.storehive.application.domain.Category;
import main.java.com.storehive.application.domain.Product;
import main.java.com.storehive.application.domain.Store;
import main.java.com.storehive.application.domain.Storelocation;
import main.java.com.storehive.application.services.app.SearchServices;
import main.java.com.storehive.application.services.crud.CategoryCrudService;
import main.java.com.storehive.application.services.crud.StoreLocationCrudServices;
import main.java.com.storehive.application.services.crud.impl.CategoryCrudServiceImpl;
import main.java.com.storehive.application.services.crud.impl.StoreLocationServicesImpl;
import main.java.com.storehive.application.utilities.gps_algorithm.Ellipsoid;
import main.java.com.storehive.application.utilities.gps_algorithm.GeodeticCalculator;
import main.java.com.storehive.application.utilities.gps_algorithm.GlobalPosition;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SearchServicesImpl implements SearchServices {

	private CategoryCrudService cs;
	private StoreLocationCrudServices slcs;
	
	public SearchServicesImpl(){
		cs = new CategoryCrudServiceImpl();
		slcs = new StoreLocationServicesImpl();
	}
	
	@Override
	public JSONArray searchByCategory(JSONObject searchParameters) {
		JSONArray allStores = new JSONArray();
		
		//get user location
		Storelocation userLocation = new Storelocation();
		userLocation.setLatitude(searchParameters.get("latitude").toString());
		userLocation.setLongitude(searchParameters.get("longitude").toString());
		
		//get all categories
		JSONArray allCategories = (JSONArray)searchParameters.get("categoryList");
		
		if(allCategories.size()>0){

			for(int i = 0;i < allCategories.size();i++){
				JSONObject jsonCategory = (JSONObject)allCategories.get(i);
				Integer categoryId = Integer.valueOf(jsonCategory.get("categoryId").toString());
				Category userListCategory = cs.findById(Category.class, categoryId);
				if(userListCategory!=null){
					//get all stores from Categories
					List<Product> allProducts = userListCategory.getProducts();
					for(Product p: allProducts){
						Store s = p.getStore();
						
						//perform calculation parse values
						if(getLatestLocation(s.getId())!=null){
							System.out.println("here");
							double storeLat = Double.valueOf(getLatestLocation(s.getId()).getLatitude());
							double storelong = Double.valueOf(getLatestLocation(s.getId()).getLongitude());
							double userLat = Double.valueOf(userLocation.getLatitude());
							double userLong = Double.valueOf(userLocation.getLongitude());
							
							if(isStoreInRange(storeLat, storelong, userLat, userLong)){
								//build list 
								JSONObject storeAvailable = new JSONObject();
								storeAvailable.put("storeName", ""+s.getShopName());
								storeAvailable.put("latitude", ""+storeLat);
								storeAvailable.put("longitude", ""+storelong);
								storeAvailable.put("link", "/inventoryServices/viewAllStoreProducts/"+s.getId());
								allStores.add(storeAvailable);
							}
						}
					}
				}
			}
		}
		return allStores;
	}
	
	private boolean isStoreInRange(double storeLat,double storelong, double userLat,double userLong){
		//calculate distance between stores and users
		GeodeticCalculator geoCalc = new GeodeticCalculator();

		Ellipsoid reference = Ellipsoid.WGS84;  

		GlobalPosition pointA = new GlobalPosition(storeLat, storelong, 0.0); // Point A

		GlobalPosition userPos = new GlobalPosition(userLat, userLong, 0.0); // Point B

		double distance = geoCalc.calculateGeodeticCurve(reference, userPos, pointA).getEllipsoidalDistance(); // Distance between Point A and Point B
		
		if(distance/1000<5.0){
			return true;
		}
		return false;
	}
	
	private Storelocation getLatestLocation(Integer id){
		//get latest result for store location
		List<Storelocation> all = slcs.findAll();
		System.out.println(id);
		for(Storelocation l: all){
			try{
				System.out.println(l.getStore().getId());
				if(l.getStore().getId()==id)
					return l;
			}catch(NullPointerException npe){
			  System.out.println("Storelocation has a null store id");	
			}
			
		}
		return null;
	}
}
