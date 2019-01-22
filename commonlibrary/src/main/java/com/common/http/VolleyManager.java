package com.common.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Jacko.Huang on 2017/2/17.
 */

public class VolleyManager {

    private static VolleyManager mVolleyManager = null;
    private RequestQueue mRequestQueue;

    private VolleyManager() {

    }

    /**
     * @param context
     */
    private VolleyManager(Context context) {
        //mRequestQueue = Volley.newRequestQueue(context,new OkHttp3Stack(getOkHttpClient()));
        mRequestQueue = Volley.newRequestQueue(context);
    }

    /**
     * 单例模式
     *
     * @return VolleyManager instance
     */
    public static VolleyManager GetVolleyManager(Context context) {
        if(mVolleyManager==null){
            mVolleyManager=new VolleyManager(context.getApplicationContext());
        }
        return  mVolleyManager;
    }

    public <T> Request<T> add(Request<T> request) {
        //FakeX509TrustManager.allowAllSSL();
        //FakeX509TrustManager.NukeSSLCerts.nuke();
        return mRequestQueue.add(request);//添加请求到队列
    }

    /**
     * @param tag
     * @param url
     * @param listener
     * @param errorListener
     * @return
     */
    public StringRequest addStrRequest(Object tag, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(url, listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }

    /**
     * @param tag
     * @param method
     * @param url
     * @param listener
     * @param errorListener
     * @return
     */
    public StringRequest addStrRequest(Object tag, int method, String url, Response.Listener<String> listener,
                                    Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(method, url, listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }



    /**
     * Get方法
     *
     * @param tag
     * @param url
     * @param clazz
     * @param listener
     * @param errorListener
     * @param <T>
     * @return
     */
    public <T> GsonRequest<T> addGsonGetRequest(Object tag, String url, Class<T> clazz, Response.Listener<T> listener,
                                             Response.ErrorListener errorListener) {
        GsonRequest<T> request = new GsonRequest<T>(url, clazz, listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }

    /**
     *
     *
     * @param tag
     * @param params
     * @param url
     * @param clazz
     * @param listener
     * @param errorListener
     * @param <T>
     * @return
     */
    public <T> GsonRequest<T> addGsonRequest(int method,Object tag, Map<String, String> header,Map<String, Object> params, String url,
                                              Class<T> clazz, Response.Listener<T> listener,
                                              Response.ErrorListener errorListener) {
        GsonRequest<T> request = new GsonRequest<T>(method, header,params, url, clazz, listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }

    public <T> GsonRequest<T> addGsonRequestWithPriority(int method,Object tag, Map<String, String> header,Map<String, Object> params, String url,
                                             Class<T> clazz, Response.Listener<T> listener,
                                             Response.ErrorListener errorListener,Request.Priority priority) {
        GsonRequest<T> request = new GsonRequest<T>(method, header,params, url, clazz, listener, errorListener).setPriority(priority);
        request.setTag(tag);
        add(request);
        return request;
    }

    /**
     *
     *
     * @param url
     * @param jsonObject
     * @param listener
     * @param errorListener
     */
    public JsonObjectRequest addjsonRequest(int method, Object tag, String url, JSONObject jsonObject, Response.Listener<JSONObject> listener,
                                            Response.ErrorListener errorListener) {
        JsonObjectRequest jsonObjectRequest;
        jsonObjectRequest = new JsonObjectRequest(method, url, jsonObject,
                    listener, errorListener);
        jsonObjectRequest.setTag(tag);
        add(jsonObjectRequest);
        return  jsonObjectRequest;

    }


    /**
     * 取消请求
     *
     * @param tag
     */
    public void cancel(Object tag) {
        mRequestQueue.cancelAll(tag);
    }





}
