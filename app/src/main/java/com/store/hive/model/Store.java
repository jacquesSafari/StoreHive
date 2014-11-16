package com.store.hive.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tinashe.
 */
public class Store extends JSONModel{

    @SerializedName("storeId")
    private int storeId;

    @SerializedName("ownerId")
    private int ownerId;

    @SerializedName("shopName")
    private String shopName;

    @SerializedName("description")
    private String description;

    @SerializedName("isOpen")
    private boolean isOpen;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;


    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
