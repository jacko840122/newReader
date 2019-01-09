package com.common.http;

import android.telecom.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.Map;

import static com.android.volley.DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;

/**
 * Created by Jacko.Huang on 2017/2/17.
 */

public class GsonRequest<T> extends Request<T> {
    private final WeakReference<Response.Listener<T>> mListener;

    private WeakReference<Response.ErrorListener> mWeakRefErrorListener;

    private Gson mGson;

    private Class<T> mClass;
    Map<String, Object> mParams=null;
    Map<String, String> mHeader=null;
    public static final int TIMEOUT_MS = 30000;
    protected static final String PROTOCOL_CHARSET = "utf-8";
    /** Content type for request. */
    private static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    public GsonRequest(int method, Map<String, String> header,Map<String, Object>Params,String url, Class<T> clazz, Response.Listener<T> listener,
                            Response.ErrorListener errorListener) {
        super(method, url, null);
        mGson = new Gson();
        mClass = clazz;
        mListener = new WeakReference<Response.Listener<T>>(listener);
        mWeakRefErrorListener=new WeakReference<Response.ErrorListener>(errorListener);
        mParams=Params;
        mHeader=header;
        setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_MS, 2/*DEFAULT_MAX_RETRIES*/, DEFAULT_BACKOFF_MULT));
    }




    public GsonRequest(String url, Class<T> clazz, Response.Listener<T> listener,
                       Response.ErrorListener errorListener) {
        this(Method.GET, null,null,url, clazz, listener, errorListener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,"utf-8"/*HttpHeaderParser.parseCharset(response.headers)*/);
            return Response.success(mGson.fromJson(jsonString, mClass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        if(mListener!=null&&mListener.get()!=null&&response!=null){
            mListener.get().onResponse(response);
        }

    }

    @Override
    public Response.ErrorListener getErrorListener() {
        if(mWeakRefErrorListener!=null&&mWeakRefErrorListener.get()!=null){
            return mWeakRefErrorListener.get();
        }else {
            return null;
        }
    }

    @Override
    protected void onFinish() {
        super.onFinish();
        mWeakRefErrorListener=null;

    }

    @Override
    public void deliverError(VolleyError error) {
        if(mWeakRefErrorListener!=null&&mWeakRefErrorListener.get()!=null){
            mWeakRefErrorListener.get().onErrorResponse(error);
        }
    }

    protected Map<String, Object> getParams2() throws AuthFailureError {
        return mParams;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
       if(mHeader==null) return super.getHeaders();
        return mHeader;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        Map<String, Object> params = getParams2();
        if (params != null && params.size() > 0) {
            try {
                return encodeParameters(params, getParamsEncoding());
            }catch (Exception err){
                Log.i("ggg",err.toString());
            }

        }
        return null;
    }


    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    private byte[] encodeParameters(Map<String, Object> params, String paramsEncoding) {
        try {

            return new JSONObject(params).toString().getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }

    private Priority mPriority=Priority.NORMAL;

    @Override
    public Priority getPriority() {
        return mPriority;
    }


    public GsonRequest<T>setPriority(Priority priority){
        mPriority=priority;
        return this;
    }
}
