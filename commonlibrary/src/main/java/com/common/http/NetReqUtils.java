package com.common.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Jacko.Huang on 2017/3/2.
 */

public final class NetReqUtils {


        private static final String TAG ="NetReqUtils";
        //public final static String BASE_URL="http://123.207.238.69:8897";
        //public final static String BASE_URL="http://113.106.61.54:802";//开发
        //public  static String BASE_URL="https://123.207.238.69:8898";//测试
        public static boolean init_base_url=false;
        public static String BASE_URL="http://47.105.162.222";

        private static final String BASE_URL_FILE="server_url.config";

        private static String getString(InputStream inputStream) {
                InputStreamReader inputStreamReader = null;
                try {
                        inputStreamReader = new InputStreamReader(inputStream, "gbk");
                } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                }
                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuffer sb = new StringBuffer("");
                String line;
                try {
                        while ((line = reader.readLine()) != null) {
                                sb.append(line);
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return sb.toString();
        }

        public static void initBaseUrl(){
                FileInputStream is=null;
                if(!init_base_url){
                        try {
                                File file=new File(Environment.getExternalStorageDirectory().getPath(),BASE_URL_FILE);
                                if(file.exists()&&file.isFile()){
                                        is=new FileInputStream(file);
                                        String Str=getString(is).trim();
                                        if(Str!=null&&!Str.isEmpty()){
                                                BASE_URL=Str;
                                        }
                                }
                                Log.i(TAG,"BASE_URL="+BASE_URL);
                                init_base_url  =true;
                        }catch (Exception e){
                                e.printStackTrace();
                        }finally {
                                if(is!=null){
                                        try {
                                                is.close();
                                        } catch (IOException e) {
                                                e.printStackTrace();
                                        }
                                }
                        }


                }
        }

        private final static String  APP_ID="3C2H5B8331DDA4DF332DS823D83H8W2G";
        private final static String  APP_TOKEN="4H8EH85H8WEYG8P0Y875H3UD72MTIDK9";



        final public static String   ACTION_CATEGORY="/books/categorymenu";;  //获取图书分类信息
        final public static String   ACTION_BOOKS_LIST="/books/bookslist/";;  //获取图书列表信息
        final public static String   ACTION_BOOKS_INFO="/books/books_info/";;  //获取图书详情信息,根据ID
        final public static String   ACTION_BOOKSKEY_INFO="/books/bookskey_info/";//获取图书详情信息,根据书名
        final public static String   ACTION_SAVE_FEEL="/books/savefeel/";;  //保存读后感内容
        final public static String   ACTION_GET_PZ_LIST="/books/getpzlist/";;  //获取图书批注内容
        final public static String   ACTION_GET_FEEL_LIST="/books/getfeellist/";;  //获取读后感列表
        final public static String   ACTION_GET_READ_NUM="/books/readnum/";;  //阅读人数更新接口



        private static String getVersionName(Context context,String packageName){
                String versionName ="";
                try {
                        PackageManager pm=context.getPackageManager();
                        PackageInfo packageInfo = pm.getPackageInfo(packageName, 0);
                        versionName = packageInfo.versionName;
                }catch (Exception e){
                        e.printStackTrace();
                }
                return versionName;
        }

        public static HashMap<String, Object> GetCommomParams(Context context){
                HashMap<String, Object> map=new HashMap<String, Object>();

                return map;

        }




        public static HashMap<String, String> getCommomHeader(Context context){
                HashMap<String, String> map=new HashMap<String, String>();

                return map;
        }


        private static String GetToken(){
                return APP_TOKEN;
        }



        private static String md5(String src) {
                try {
                        MessageDigest md = MessageDigest.getInstance("MD5");
                        byte[] b = md.digest(src.getBytes("utf-8"));
                        return byte2HexStr(b);
                } catch (Throwable e) {
                        throw new AssertionError(e);
                }
        }

        private static String byte2HexStr(byte[] b) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < b.length; i++) {
                        String s = Integer.toHexString(b[i] & 0xFF);
                        if (s.length() == 1) {
                                sb.append("0");
                        }
                        sb.append(s.toUpperCase());
                }
                return sb.toString();
        }


        private   static String GetCurTimeStamp(){
                SimpleDateFormat formatter  =   new  SimpleDateFormat("yyyyMMddHHmmss");
                Date    curDate    =   new Date(System.currentTimeMillis());
                String    str    =    formatter.format(curDate);
                return str;
        }



        public static <T> GsonRequest<T> addGsonRequest(Context context,int method, Object tag, HashMap<String, String> ExtraHeader, HashMap<String, Object> ExtraParams, String url,
                                                 Class<T> clazz, Response.Listener<T> listener,
                                                 Response.ErrorListener errorListener){
                if(context==null) {
                        //throw new InvalidParameterException("Parameter context is null!");
                        return  null;
                }
                HashMap<String, String> header=getCommomHeader(context);
                HashMap<String, Object> params=GetCommomParams(context);

                if(ExtraHeader!=null) header.putAll(ExtraHeader);
                if(ExtraParams!=null) params.putAll(ExtraParams);
                if(url.startsWith("/")){
                        url=BASE_URL+url;
                }
                Log.i(TAG,"addGsonRequest--"+url);
                return  VolleyManager.GetVolleyManager(context).addGsonRequest(method, tag, header, params,url, clazz, listener, errorListener);

        }


        public static <T> GsonRequest<T> addGsonRequestWithPriority(Context context,int method, Object tag, HashMap<String, String> ExtraHeader, HashMap<String, Object> ExtraParams, String url,
                                                        Class<T> clazz, Response.Listener<T> listener,
                                                        Response.ErrorListener errorListener,Request.Priority priority){
                if(context==null) {
                        //throw new InvalidParameterException("Parameter context is null!");
                        return null;
                }
                HashMap<String, String> header=getCommomHeader(context);
                HashMap<String, Object> params=GetCommomParams(context);

                if(ExtraHeader!=null) header.putAll(ExtraHeader);
                if(ExtraParams!=null) params.putAll(ExtraParams);
                if(url.startsWith("/")){
                        url=BASE_URL+url;
                }
                Log.i(TAG,"addGsonRequest--"+url);
                return  VolleyManager.GetVolleyManager(context).addGsonRequestWithPriority(method, tag, header, params,url, clazz, listener, errorListener,priority);

        }


        public static  void CancelRequest(Context context,Object tag){
                VolleyManager.GetVolleyManager(context).cancel(tag);

        }


}
