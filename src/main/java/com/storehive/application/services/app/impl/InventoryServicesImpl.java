package main.java.com.storehive.application.services.app.impl;

import java.util.List;

import main.java.com.storehive.application.domain.Category;
import main.java.com.storehive.application.domain.Product;
import main.java.com.storehive.application.domain.Store;
import main.java.com.storehive.application.services.app.InventoryServices;
import main.java.com.storehive.application.services.crud.CategoryCrudService;
import main.java.com.storehive.application.services.crud.ProductCrudServices;
import main.java.com.storehive.application.services.crud.StoreCrudServices;
import main.java.com.storehive.application.services.crud.impl.CategoryCrudServiceImpl;
import main.java.com.storehive.application.services.crud.impl.ProductCrudServicesImpl;
import main.java.com.storehive.application.services.crud.impl.StoreCrudServicesImpl;
import main.java.com.storehive.application.utilities.ErrorCodes;
import main.java.com.storehive.application.utilities.ResponseResult;
import main.java.com.storehive.application.utilities.factory.ApplicationFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class InventoryServicesImpl implements InventoryServices {

	CategoryCrudService ccs;
	ProductCrudServices pcs;
	StoreCrudServices scs;
	
	public InventoryServicesImpl(){
		ccs = new CategoryCrudServiceImpl();
		pcs = new ProductCrudServicesImpl();
		scs = new StoreCrudServicesImpl();
	}
	
	@Override
	public JSONArray getAllCategories() {
		JSONArray ja = new JSONArray();
		
		List<Category> categories = ccs.findAll();
		
		for(Category c:categories){
			JSONObject jo = new JSONObject();
			jo.put("id", c.getId());
			jo.put("category_name", c.getCategoryName());
			jo.put("category_description", c.getCategoryDescription());
			ja.add(jo);
		}
		return ja;
	}

	@Override
	public ResponseResult addProductToStore(JSONObject productDetails) {
		Category c = ccs.findById(Category.class,Integer.valueOf(productDetails.get("categoryId").toString()));
		Store s = scs.findById(Store.class, Integer.valueOf(productDetails.get("storeId").toString())); 
		Product newProduct = ApplicationFactory.buildProduct(productDetails);
		newProduct.setCategory(c);
		newProduct.setStore(s);
		
		Product persisted = pcs.createEntity(newProduct);
		ResponseResult r = new ResponseResult();
		if(persisted!=null){
			r.setSuccessful(true);
			r.setErrorCode(ErrorCodes.PROD_SUC_1);
			r.setLink("/inventoryServices/viewProduct/"+persisted.getId());
		}else{
			r.setSuccessful(false);
			r.setErrorCode(ErrorCodes.PROD_ERR_1);
			r.setErrorMessage("There was an error creating a product.");
		}
			
		return r;
	}

	@Override
	public JSONObject viewProductDetails(Integer id) {
		JSONObject j = new JSONObject();
		Product p = pcs.findById(Product.class, id);
		
		j.put("id", id);
		j.put("productName", p.getProductName());
		j.put("productDescription", p.getProductDescription());
		j.put("productCategory", p.getCategory().getCategoryName());
		j.put("productQuantity", p.getProductQuantity());
		
		return j;
	}

	@Override
	public JSONArray getAllProducts() {
		JSONArray j = new JSONArray();
		List<Product> allProducts = pcs.findAll();
		if(allProducts.size()>0){
			for(Product p:allProducts){
				JSONObject product = new JSONObject();
				product.put("productName", p.getProductName());
				product.put("productDescription", p.getProductDescription());
				product.put("productPrice", p.getProductPrice());
				product.put("productQuantity", p.getProductQuantity());
				product.put("categoryId", ""+p.getCategory().getId());
				product.put("link", "/inventoryServices/viewProduct/"+p.getId());
				j.add(product);
			}
		}else{
			JSONObject message = new JSONObject();
			message.put("Error", "No products in store");
			j.add(message);
		}
		
		return j;
	}

	@Override
	public ResponseResult deleteProductFromInventory(Integer id) {
		ResponseResult r = new ResponseResult();
 
		Product toBeDeleted = pcs.findById(Product.class, id);
		
		if(toBeDeleted!=null){
			pcs.delete(toBeDeleted);
			r.setSuccessful(true);
		}else{
			r.setErrorCode(ErrorCodes.PROD_ERR_2);
			r.setErrorMessage("There was an error trying to remove the product");
			r.setSuccessful(false);
		}
		return r;
	}

	@Override
	public JSONObject updateProduct(JSONObject productDetails) {
		
		Category c = ccs.findById(Category.class,Integer.valueOf(productDetails.get("categoryId").toString()));
		Store s = scs.findById(Store.class, Integer.valueOf(productDetails.get("storeId").toString())); 
		Product toUpdate = ApplicationFactory.buildProduct(productDetails);
		toUpdate.setId(Integer.valueOf(productDetails.get("productId").toString()));
		toUpdate.setCategory(c);
		toUpdate.setStore(s);

		Product updated = pcs.update(toUpdate);
		
		JSONObject j = new JSONObject();
		j.put("productId", updated.getId());
		j.put("productName", updated.getProductName());
		j.put("productDescription", updated.getProductDescription());
		j.put("productPrice", updated.getProductPrice());
		j.put("productQuantity", updated.getProductQuantity());
		j.put("categoryId", updated.getCategory().getId());
		j.put("storeId", updated.getStore().getId());
		
		return j;
	}

}

