package com.store.hive.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.store.hive.R;
import com.store.hive.model.people.StoreOwner;
import com.store.hive.model.response.ResponseResult;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tinashe
 */
public class RequestHandler{

    private static final String TAG = RequestHandler.class.getName();
    private RequestQueue mRequestQueue;

    private static RequestHandler ourInstance = new RequestHandler();

    public static RequestHandler getInstance() {
        return ourInstance;
    }

    private RequestHandler() {
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

    public void registerStoreOwner(StoreOwner storeOwner, Context context, final OnRequestCompleteLister callBack){
        String url = context.getString(R.string.sh_api_url) + context.getString(R.string.sp_api_register_user_path);

        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    boolean isSuccess = Boolean.valueOf(response.getString("isSuccessfull"));

                    if(isSuccess){
                        callBack.onRequestComplete(new ResponseResult(true));
                    } else {
                        String reason = response.getString("errorMessage");
                        callBack.onRequestComplete(new ResponseResult(false, reason));
                    }
                }catch (JSONException ex){
                    Log.d(TAG, ex.getMessage());
                    callBack.onRequestError();
                }


            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "That didn't work: "+ error.toString());
                callBack.onRequestError();
            }
        };

        JSONObject jsonObject = createJsonObject(storeOwner);
        if(jsonObject != null){
            JsonObjectRequest req =new JsonObjectRequest(url, jsonObject, listener, errorListener);
            getRequestQueue(context).add(req);
        }

    }

    private JSONObject createJsonObject(StoreOwner storeOwner){
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("name", storeOwner.getFirstName());
        jsonObject.addProperty("surname", storeOwner.getLastName());
        jsonObject.addProperty("username", storeOwner.getUserName());
        jsonObject.addProperty("password", storeOwner.getPassword());
        jsonObject.addProperty("deviceID", storeOwner.getDeviceID());

        try{
            JSONObject JsonObject= new JSONObject(jsonObject.toString());
            return JsonObject;
        }catch (JSONException ex){
            return  null;
        }
    }




}
