package com.store.hive.model.products;

import com.google.gson.annotations.SerializedName;
import com.store.hive.model.JSONModel;

/**
 * Created by tinashe
 */
public class Product extends JSONModel{

    @SerializedName("productId")
    private String productId;

    @SerializedName("productName")
    private String productName;

    @SerializedName("description")
    private String description;

    @SerializedName("category")
    private String category;

    @SerializedName("price")
    private String price;

    @SerializedName("imgUrl")
    private String imgUrl;


    public Product(String productName, String description, String category, String price) {
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public Product(String productId, String productName, String description, String category, String price, String imgUrl) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public boolean hasImage(){
        return imgUrl != null;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
