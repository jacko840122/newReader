package com.github.reader.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtil {

    public static String getSavePath(String path, int page) {
        File saveDir=new File(Environment.getExternalStorageDirectory().getPath()+File.separator+"bynote");
        String fileName=encode(path+page)+".note";
        if(!saveDir.exists()) saveDir.mkdirs();
        return saveDir.getPath()+File.separator+fileName;
    }


    public static String getmThumbnailDir(String bookFilePath) {
        File saveDir=new File(Environment.getExternalStorageDirectory().getPath()+File.separator+"Thumbnail"+File.separator+encode(bookFilePath));
        if(!saveDir.exists()) saveDir.mkdirs();
        return saveDir.getPath();
    }

    public static String encode(String str) {
        // MessageDigest专门用于加密的类
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] result = messageDigest.digest(str.getBytes()); // 得到加密后的字符组数

            StringBuffer sb = new StringBuffer();

            for (byte b : result) {
                int num = b & 0xff; // 这里的是为了将原本是byte型的数向上提升为int型，从而使得原本的负数转为了正数
                String hex = Integer.toHexString(num); //这里将int型的数直接转换成16进制表示
                //16进制可能是为1的长度，这种情况下，需要在前面补0，
                if (hex.length() == 1) {
                    sb.append(0);
                }
                sb.append(hex);
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean saveBitMap(Bitmap bitmap, String path) {
        Boolean ret=false;
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 0, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ret;
    }
}
