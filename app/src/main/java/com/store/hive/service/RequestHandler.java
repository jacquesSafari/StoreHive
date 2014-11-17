package com.store.hive.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.store.hive.R;
import com.store.hive.model.products.Category;
import com.store.hive.model.request.RegisterStoreRequest;
import com.store.hive.model.response.GetCategoriesResponse;
import com.store.hive.model.response.RegisterStoreResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tinashe
 */
@SuppressWarnings("unchecked")
public class RequestHandler<T>{

    private static final String TAG = RequestHandler.class.getName();

    private RequestQueue mRequestQueue;

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


    public ImageLoader getImageLoader() {

        return mImageLoader;
    }

    public void handleRequest(Context context, int method, String url, Class<T> returnType, Object jsonRequest, Response.Listener<T> listener ){

        GSonRequest request = new GSonRequest(method, getBaseUrl(context) + url, returnType, jsonRequest, listener, getDefaultErrorListener(context));

        getRequestQueue(context).add(request);
    }

    public void registerStore(Context context, String url, RegisterStoreRequest request, Response.Listener<JSONObject> listener){
        JSONObject object = new JSONObject();
        try{
            object.put("shopName", request.getShopName());
            object.put("description", request.getDescription());
            object.put("ownerId", request.getOwnerId());
        }catch (JSONException ex){

        }


        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, getBaseUrl(context) + url,object,
                listener,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                      Log.d(TAG, error.getMessage());
                    }
                });

        getRequestQueue(context).add(jsObjRequest);
    }

    public void getAllCategories(Context context, String url, final Response.Listener<GetCategoriesResponse> listener){

        StringRequest request = new StringRequest(getBaseUrl(context) + url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onResponse(convertToJson(response));


            }
        }, getDefaultErrorListener(context));

        getRequestQueue(context).add(request);
    }

    private GetCategoriesResponse convertToJson(String jsonArray){



        List<Category> categories = new ArrayList<Category>();
        GetCategoriesResponse response = new GetCategoriesResponse();
        Gson gson = GsonUtil.getInstance();

        try{
            JSONArray array = new JSONArray(jsonArray);

            JsonParser parser = new JsonParser();

            for(int i = 0; i < array.length(); i++){
                JSONObject object = (JSONObject) array.get(i);
                Log.d(TAG, object.getString("category_name"));
               // JsonObject jsonObject = (JsonObject)parser.parse(object.toString());
                Category category = new Category();
                category.setCategoryName(object.getString("category_name"));
                category.setCategoryDescription(object.getString("category_description"));
               // Log.d(TAG, category.getCategoryName());

                categories.add(category);
            }

            response.setCategories(categories);



        }catch (JSONException ex){

        }

        return response;
    }


    private Response.ErrorListener getDefaultErrorListener(final Context context){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d(TAG, "DefaultErrorListener\n"+volleyError.getMessage());
                Toast.makeText(context, context.getString(R.string.sh_error_default), Toast.LENGTH_LONG).show();
            }
        };
    }

    private String getBaseUrl(Context context){
        return context.getString(R.string.sh_api_url);
    }


}
