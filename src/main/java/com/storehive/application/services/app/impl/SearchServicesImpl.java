package main.java.com.storehive.application.services.app.impl;

import java.util.List;

import main.java.com.storehive.application.domain.Category;
import main.java.com.storehive.application.domain.Product;
import main.java.com.storehive.application.domain.Store;
import main.java.com.storehive.application.domain.Storelocation;
import main.java.com.storehive.application.services.app.SearchServices;
import main.java.com.storehive.application.services.crud.CategoryCrudService;
import main.java.com.storehive.application.services.crud.impl.CategoryCrudServiceImpl;
import main.java.com.storehive.application.utilities.gps_algorithm.Ellipsoid;
import main.java.com.storehive.application.utilities.gps_algorithm.GeodeticCalculator;
import main.java.com.storehive.application.utilities.gps_algorithm.GlobalPosition;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SearchServicesImpl implements SearchServices {

	private CategoryCrudService cs;
	
	public SearchServicesImpl(){
		cs = new CategoryCrudServiceImpl();
	}
	
	@Override
	public JSONArray searchByCategory(JSONObject searchParameters) {
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
					}
				}
			}
		}
		
		
		
		
		//calculate distance between stores and users
		
		return null;
	}
	
	private boolean isStoreInRange(double storeLat,double storelong, double userLat,double userLong){
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
}
