package com.store.hive.model.products;

import com.google.gson.annotations.SerializedName;
import com.store.hive.model.JSONModel;

/**
 * Created by tinashe.
 */
public class Category extends JSONModel{

    @SerializedName("id")
    private String id;

    @SerializedName("categoryName")
    private String categoryName;

    @SerializedName("categoryDescription")
    private String categoryDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
