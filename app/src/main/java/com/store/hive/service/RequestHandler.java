package com.store.hive.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.store.hive.R;
import com.store.hive.model.people.StoreOwner;
import com.store.hive.model.people.StoreOwnerEnum;
import com.store.hive.model.response.ResponseResult;
import com.store.hive.model.response.ResponseResultEnum;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tinashe
 */
public class RequestHandler{

    private static final String TAG = RequestHandler.class.getName();

    private RequestQueue mRequestQueue;

    private OnRequestCompleteLister callBack;

    private ImageLoader mImageLoader;

    private static RequestHandler ourInstance;

    public static synchronized RequestHandler getInstance(Context context) {

        if(ourInstance == null){
            ourInstance = new RequestHandler(context);
        }

        return ourInstance;
    }

    private RequestHandler(Context context) {
        mRequestQueue = getRequestQueue(context);

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });

    }

    public RequestQueue getRequestQueue(Context mCtx) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
       // getRequestQueue().add(req);
    }

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d(TAG, error.toString());
            callBack.onRequestError();
        }
    };

    public ImageLoader getImageLoader() {

        return mImageLoader;
    }

    public void registerStoreOwner(StoreOwner storeOwner, Context context, OnRequestCompleteLister requestCallBack){
        String url = context.getString(R.string.sh_api_url) + context.getString(R.string.sh_api_register_user_path);
        this.callBack = requestCallBack;

        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    boolean isSuccess = Boolean.valueOf(response.getString(ResponseResultEnum.isSuccessful.getKey()));

                    if(isSuccess){
                        callBack.onRequestComplete(new ResponseResult(true));
                    } else {
                        String reason = response.getString(ResponseResultEnum.errorMessage.getKey());
                        String errorCode = response.getString(ResponseResultEnum.errorCode.getKey());
                        callBack.onRequestComplete(new ResponseResult(isSuccess, reason, errorCode));
                    }
                }catch (JSONException ex){
                    Log.d(TAG, ex.getMessage());
                    callBack.onRequestError();
                }


            }
        };


        JSONObject jsonObject = createJsonObject(storeOwner);
        if(jsonObject != null){
            JsonObjectRequest req =new JsonObjectRequest(url, jsonObject, listener, errorListener);
            getRequestQueue(context).add(req);
        } else {
            callBack.onRequestError();
        }

    }

    public void authenticateStoreOwner(Context context, String user_name, String password, OnRequestCompleteLister requestCallBack){
        String url = context.getString(R.string.sh_api_url) + context.getString(R.string.sh_api_login_path);
        this.callBack = requestCallBack;

        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try{
                    boolean isSuccess = Boolean.valueOf(response.getString(ResponseResultEnum.isSuccessful.getKey()));

                    if(isSuccess){
                        callBack.onRequestComplete(new ResponseResult(true));
                    } else {
                        String reason = response.getString(ResponseResultEnum.errorMessage.getKey());
                        String errorCode = response.getString(ResponseResultEnum.errorCode.getKey());
                        callBack.onRequestComplete(new ResponseResult(isSuccess, reason, errorCode));
                    }
                }catch (JSONException ex){
                    Log.d(TAG, ex.getMessage());
                    callBack.onRequestError();
                }
            }
        };


        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(StoreOwnerEnum.username.getKey(), user_name);
        jsonObject.addProperty(StoreOwnerEnum.password.getKey(), password);

        try{
            JSONObject object = new JSONObject(jsonObject.toString());
            JsonObjectRequest request = new JsonObjectRequest(url, object, listener, errorListener);

            getRequestQueue(context).add(request);
        }catch (JSONException ex){
            callBack.onRequestError();
            Log.d(TAG, ex.getMessage());
        }

    }

    private JSONObject createJsonObject(StoreOwner storeOwner){
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty(StoreOwnerEnum.name.getKey(), storeOwner.getFirstName());
        jsonObject.addProperty(StoreOwnerEnum.surname.getKey(), storeOwner.getLastName());
        jsonObject.addProperty(StoreOwnerEnum.username.getKey(), storeOwner.getUserName());
        jsonObject.addProperty(StoreOwnerEnum.password.getKey(), storeOwner.getPassword());
        jsonObject.addProperty(StoreOwnerEnum.deviceId.getKey(), storeOwner.getDeviceID());

        try{
            return new JSONObject(jsonObject.toString());
        }catch (JSONException ex){
            return  null;
        }
    }




}
