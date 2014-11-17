package com.store.hive.model.response;

import com.google.gson.annotations.SerializedName;
import com.store.hive.model.JSONModel;
import com.store.hive.model.products.Category;

import java.util.List;

/**
 * Created by tinashe.
 */
public class GetCategoriesResponse extends JSONModel {

    @SerializedName("categories")
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
