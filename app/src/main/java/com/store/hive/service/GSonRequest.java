package com.store.hive.service;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;


public class GSonRequest<T> extends JsonRequest<T> {
    private final Gson gson = GsonUtil.getInstance();
    private final Class<T> clazz;

    private final Response.Listener<T> listener;

    /**
     * Make a GET request and return a parsed object from JSON.
     * @param method type of method, GET or POST
     * @param url URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     */
    public GSonRequest(int method, String url, Class<T> clazz, Object jsonRequest,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, (jsonRequest == null) ? null : GsonUtil.getInstance().toJson(jsonRequest), listener,
                errorListener);

        this.clazz = clazz;
        this.listener = listener;
    }

    public GSonRequest(String url, Class<T> returnType, Object jsonRequest, Response.Listener<T> listener,
                       Response.ErrorListener errorListener) {
        this(jsonRequest == null ? Method.GET : Method.POST, url, returnType, jsonRequest,
                listener, errorListener);
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}