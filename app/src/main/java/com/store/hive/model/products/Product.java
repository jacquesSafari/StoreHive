package com.store.hive.model.products;

import com.google.gson.annotations.SerializedName;
import com.store.hive.model.JSONModel;

import java.math.BigDecimal;

/**
 * Created by tinashe
 */
public class Product extends JSONModel{

    @SerializedName("productId")
    private int productId;

    @SerializedName("productName")
    private String productName;

    @SerializedName("productDescription")
    private String description;

    @SerializedName("productQuantity")
    private int productQuantity;

    @SerializedName("categoryId")
    private int categoryId;

    @SerializedName("productPrice")
    private BigDecimal price;

    @SerializedName("imgUrl")
    private String imgUrl;

    public Product(){

    }

    public Product(int productId, String productName, String description, int categoryId, BigDecimal price, String imgUrl) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.categoryId = categoryId;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public boolean hasImage(){
        return imgUrl != null;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
