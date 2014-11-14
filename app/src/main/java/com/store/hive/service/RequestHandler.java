package com.store.hive.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.store.hive.R;
import com.store.hive.model.people.StoreOwner;
import com.store.hive.model.response.ResponseResult;


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

    public void handleRequest(Context context, String url, Class<T> returnType, Object jsonRequest, Response.Listener<T> listener ){
        GSonRequest request = new GSonRequest(getBaseUrl(context) + url, returnType, jsonRequest, listener, getDefaultErrorListener(context));

        getRequestQueue(context).add(request);
    }


    private Response.ErrorListener getDefaultErrorListener(final Context context){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, context.getString(R.string.sh_error_default), Toast.LENGTH_LONG).show();
            }
        };
    }

    private String getBaseUrl(Context context){
        return context.getString(R.string.sh_api_url);
    }


}
