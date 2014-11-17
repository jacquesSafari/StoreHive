package com.store.hive.service;

import android.content.Context;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.store.hive.R;
import com.store.hive.model.Store;
import com.store.hive.model.people.StoreOwner;
import com.store.hive.model.request.RegisterStoreRequest;
import com.store.hive.model.response.BaseResponse;
import com.store.hive.model.response.GetCategoriesResponse;
import com.store.hive.model.response.RegisterOwnerResponse;
import com.store.hive.model.response.RegisterStoreResponse;

/**
 * Created by tinashe
 */

@SuppressWarnings("unchecked")
public class StoreHiveAPI {


    public static void registerStoreOwner(Context context, StoreOwner storeOwner, Response.Listener listener){
        String url = context.getString(R.string.sh_api_register_user);
        RequestHandler.getInstance(context).handleRequest(context, Method.POST, url, RegisterOwnerResponse.class, storeOwner, listener);
    }

    public static void getStoreOwnerDetails(Context context, int owner, Response.Listener listener){

    }

    public static void getAllCategories(Context context, Response.Listener<GetCategoriesResponse> listener){
        String url = context.getString(R.string.sh_api_get_categories);

        RequestHandler.getInstance(context).getAllCategories(context, url, listener);
    }


    public static void authenticateStoreOwner(Context context, StoreOwner storeOwner, Response.Listener listener){
        String url = context.getString(R.string.sh_api_login_path);
        RequestHandler.getInstance(context).handleRequest(context, Method.PUT, url, BaseResponse.class, storeOwner, listener);
    }

    public static void registerStore(Context context, RegisterStoreRequest store, Response.Listener listener){
        String url = context.getString(R.string.sh_api_register_store); //storeServices/registerNewStore

        RequestHandler.getInstance(context).registerStore(context, url, store, listener);
       // RequestHandler.getInstance(context).handleRequest(context, Method.POST, url, RegisterStoreResponse.class, store, listener);
    }

    public static void openStore(Context context, Store store, Response.Listener listener){
        String url = context.getString(R.string.sh_api_open_store);
        RequestHandler.getInstance(context).handleRequest(context, Method.POST, url, BaseResponse.class, store, listener);
    }

    public static void closeStore(Context context, Store store, Response.Listener listener){
        String url = context.getString(R.string.sh_api_close_store);
        RequestHandler.getInstance(context).handleRequest(context, Method.POST, url, BaseResponse.class, store, listener);
    }
}
