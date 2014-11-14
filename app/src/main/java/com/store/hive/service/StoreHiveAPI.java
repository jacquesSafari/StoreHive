package com.store.hive.service;

import android.content.Context;

import com.android.volley.Response;
import com.store.hive.R;
import com.store.hive.model.people.StoreOwner;
import com.store.hive.model.response.ResponseResult;

/**
 * Created by tinashe
 */

@SuppressWarnings("unchecked")
public class StoreHiveAPI {


    public static void registerStoreOwner(Context context, StoreOwner storeOwner, Response.Listener listener){
        String url = context.getString(R.string.sh_api_register_user);
        RequestHandler.getInstance(context).handleRequest(context, url, ResponseResult.class, storeOwner, listener);
    }


    public static void authenticateStoreOwner(Context context, StoreOwner storeOwner, Response.Listener listener){
        String url = context.getString(R.string.sh_api_login_path);
        RequestHandler.getInstance(context).handleRequest(context, url, ResponseResult.class, storeOwner, listener);
    }
}
