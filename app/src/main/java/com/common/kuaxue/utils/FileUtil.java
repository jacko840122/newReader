
package com.common.kuaxue.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.bifan.txtreaderlib.ui.HwTxtPlayActivity;
import com.common.Utils.SharePrefUtil;
import com.github.reader.pdf.ui.activity.PdfActivity;
import com.greenlemonmobile.app.constant.DefaultConstant;
import com.greenlemonmobile.app.ebook.books.reader.EpubContext;
import com.greenlemonmobile.app.ebook.entity.FileInfo;
import com.greenlemonmobile.app.ebook.entity.LocalBook;

import org.ebookdroid.CodecType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileUtil {
	public static final String READIUM_FOLDER = ".readium/";
    public static final String ROOT_PATH = "/";
    
    private static String ANDROID_SECURE = ".android_secure";
    
    public static void DeleteDirectory(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            File[] list = fileOrDirectory.listFiles();
            if (list != null && list.length>0) {
                for (File child : fileOrDirectory.listFiles())
                    DeleteDirectory(child);
            }
            fileOrDirectory.delete();
        }
    }
    
    public static void DeleteDirectory(String fileOrDirectory) {        
        DeleteDirectory(new File(fileOrDirectory));
    }
    
    // storage, G M K B
    public static String convertStorage(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }

    public static boolean isSDCardReady() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    // if path1 contains path2
    public static boolean containsPath(String path1, String path2) {
        String path = path2;
        while (path != null) {
            if (path.equalsIgnoreCase(path1))
                return true;

            if (path.equals(ROOT_PATH))
                break;
            path = new File(path).getParent();
        }

        return false;
    }

    public static String makePath(String path1, String path2) {
        if (path1.endsWith(File.separator))
            return path1 + path2;

        return path1 + File.separator + path2;
    }

    public static String getSdDirectory() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    public static boolean isNormalFile(String fullName) {
        return !fullName.endsWith(ANDROID_SECURE);
    }

    public static FileInfo GetFileInfo(String filePath) {
        File lFile = new File(filePath);
        if (!lFile.exists())
            return null;

        FileInfo lFileInfo = new FileInfo();
        lFileInfo.canRead = lFile.canRead();
        lFileInfo.canWrite = lFile.canWrite();
        lFileInfo.isHidden = lFile.isHidden();
        lFileInfo.fileName = FileUtil.getNameFromFilepath(filePath);
        lFileInfo.ModifiedDate = lFile.lastModified();
        lFileInfo.IsDir = lFile.isDirectory();
        lFileInfo.filePath = filePath;
        lFileInfo.fileSize = lFile.length();
        return lFileInfo;
    }

    public static FileInfo GetFileInfo(File f, FilenameFilter filter, boolean showHidden) {
        FileInfo lFileInfo = new FileInfo();
        String filePath = f.getPath();
        File lFile = new File(filePath);
        lFileInfo.canRead = lFile.canRead();
        lFileInfo.canWrite = lFile.canWrite();
        lFileInfo.isHidden = lFile.isHidden();
        lFileInfo.fileName = f.getName();
        lFileInfo.ModifiedDate = lFile.lastModified();
        lFileInfo.IsDir = lFile.isDirectory();
        lFileInfo.filePath = filePath;
        if (lFileInfo.IsDir) {
            int lCount = 0;
            File[] files = lFile.listFiles(filter);

            // null means we cannot access this dir
            if (files == null) {
                return null;
            }

            for (File child : files) {
                if ((!child.isHidden() || showHidden)
                        && FileUtil.isNormalFile(child.getAbsolutePath())) {
                    lCount++;
                }
            }
            lFileInfo.Count = lCount;

        } else {

            lFileInfo.fileSize = lFile.length();

        }
        return lFileInfo;
    }

    public static Drawable getApkIcon(Context context, String path) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            try {
                return pm.getApplicationIcon(appInfo);
            } catch (OutOfMemoryError e) {
                // Log.e(LOG_TAG, e.toString());
            }
        }
        return null;
    }

    public static String getExtFromFilename(String filename) {
        int dotPosition = filename.lastIndexOf('.');
        if (dotPosition != -1) {
            return filename.substring(dotPosition + 1, filename.length());
        }
        return "";
    }

    public static String getNameFromFilename(String filename) {
        int dotPosition = filename.lastIndexOf('.');
        if (dotPosition != -1) {
            return filename.substring(0, dotPosition);
        }
        return "";
    }

    public static String getPathFromFilepath(String filepath) {
        int pos = filepath.lastIndexOf('/');
        if (pos != -1) {
            return filepath.substring(0, pos);
        }
        return "";
    }

    public static String getNameFromFilepath(String filepath) {
        int pos = filepath.lastIndexOf('/');
        if (pos != -1) {
            return filepath.substring(pos + 1);
        }
        return "";
    }

    // return new file path if successful, or return null
    public static String copyFile(String src, String dest) {
        File file = new File(src);
        if (!file.exists() || file.isDirectory()) {
            // Log.v(LOG_TAG, "copyFile: file not exist or is directory, " +
            // src);
            return null;
        }
        FileInputStream fi = null;
        FileOutputStream fo = null;
        try {
            fi = new FileInputStream(file);
            File destPlace = new File(dest);
            if (!destPlace.exists()) {
                if (!destPlace.mkdirs())
                    return null;
            }

            String destPath = FileUtil.makePath(dest, file.getName());
            File destFile = new File(destPath);
            int i = 1;
            while (destFile.exists()) {
                String destName = FileUtil.getNameFromFilename(file.getName()) + " " + i++ + "."
                        + FileUtil.getExtFromFilename(file.getName());
                destPath = FileUtil.makePath(dest, destName);
                destFile = new File(destPath);
            }

            if (!destFile.createNewFile())
                return null;

            fo = new FileOutputStream(destFile);
            int count = DefaultConstant.DEFAULT_BUFFER_SIZE;
            byte[] buffer = new byte[count];
            int read = 0;
            while ((read = fi.read(buffer, 0, count)) != -1) {
                fo.write(buffer, 0, read);
            }

            // TODO: set access privilege

            return destPath;
        } catch (FileNotFoundException e) {
            // Log.e(LOG_TAG, "copyFile: file not found, " + src);
            e.printStackTrace();
        } catch (IOException e) {
            // Log.e(LOG_TAG, "copyFile: " + e.toString());
        } finally {
            try {
                if (fi != null)
                    fi.close();
                if (fo != null)
                    fo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static boolean shouldShowFile(String path) {
        return shouldShowFile(new File(path));
    }

    public static boolean shouldShowFile(File file) {
        // if (file.isHidden())
        // return false;

        // if (file.getName().startsWith("."))
        // return false;

        return true;
    }

    public static boolean setText(View view, int id, String text) {
        TextView textView = (TextView) view.findViewById(id);
        if (textView == null)
            return false;

        textView.setText(text);
        return true;
    }

    public static boolean setText(View view, int id, int text) {
        TextView textView = (TextView) view.findViewById(id);
        if (textView == null)
            return false;

        textView.setText(text);
        return true;
    }

    // comma separated number
    public static String convertNumber(long number) {
        return String.format("%,d", number);
    }

    public static Boolean saveBitMap(Bitmap bitmap, String path) {
        Boolean ret=false;
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
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

    public static class SDCardInfo {
        public long total;

        public long free;
    }

    public static SDCardInfo getSDCardInfo() {
        String sDcString = android.os.Environment.getExternalStorageState();

        if (sDcString.equals(android.os.Environment.MEDIA_MOUNTED)) {
            File pathFile = android.os.Environment.getExternalStorageDirectory();

            try {
                android.os.StatFs statfs = new android.os.StatFs(pathFile.getPath());
                long nTotalBlocks = statfs.getBlockCount();
                long nBlocSize = statfs.getBlockSize();

                long nAvailaBlock = statfs.getAvailableBlocks();

                long nFreeBlock = statfs.getFreeBlocks();

                SDCardInfo info = new SDCardInfo();
                info.total = nTotalBlocks * nBlocSize;
                info.free = nAvailaBlock * nBlocSize;

                return info;
            } catch (IllegalArgumentException e) {
                // Log.e(LOG_TAG, e.toString());
            }
        }

        return null;
    }

    public static void showNotification(Context context, Intent intent,
            String title, String body, int drawableId) {
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(drawableId, body,
                System.currentTimeMillis());
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notification.defaults = Notification.DEFAULT_SOUND;
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_ONE_SHOT);
        notification.contentIntent=contentIntent;
        manager.notify(drawableId, notification);
    }

    public static String formatDateString(Context context, long time) {
        DateFormat dateFormat = android.text.format.DateFormat
                .getDateFormat(context);
        DateFormat timeFormat = android.text.format.DateFormat
                .getTimeFormat(context);
        Date date = new Date(time);
        return dateFormat.format(date) + " " + timeFormat.format(date);
    }


    private static String[] DOCUMENT_EXTS = new String[] {
            "epub", "txt","pdf", /*"djvu", "djv", "xps", "oxps", "cbz", "cbr", /*"fb2", "fb2.zip", "chm" , "umd"*/
    };

    private static ArrayList<String> mBookPathList=new ArrayList<>() ;


    private static boolean includeExtensions(File file,String[] extensions){
        if(file==null|| extensions==null||extensions.length<=0) return false;
        String path=file.getPath();
        int index=path.lastIndexOf(".");
        if(index>=0&&(index+1<path.length())){
            String fileExt=path.substring(index+1);
            for(String ext:extensions){
                if(ext.toLowerCase().equals(fileExt.toLowerCase())){
                    return true;
                }
            }
        }
        return false;

    }


    private static ArrayList<String> searchFiles(String Path, String[] extensions, boolean IsIterative, ArrayList<String> resultList) {
        File[] files = new File(Path).listFiles();

        if(files == null)
            return resultList;
        for (int i = 0; i < files.length; i++)
        {
            File f = files[i];
            if (f.isFile())
            {
                if (includeExtensions(f,extensions))
                    resultList.add(f.getPath());
            }
            else {
                if (IsIterative) {
                    if (f.isDirectory() && f.getPath().indexOf("/.") == -1)
                        resultList = searchFiles(f.getPath(), extensions, IsIterative, resultList);
                }
                else
                    continue;;
            }
        }
        return resultList;
    }



    public static void searchFiles(Context context) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (mBookPathList){
                    final String[]  Dirs={"/mnt/sdcard/ebook","/mnt/internal_sd","/mnt/internal_sd/ebook","/mnt/external_sd","/mnt/external_sd/ebook"};

                    ArrayList<File> DirList2BeImported = new ArrayList<File>();
                    for(String Dir:Dirs){
                        DirList2BeImported.add(new File(Dir));
                    }

                    final ArrayList<File> DirList = DirList2BeImported;

                    try {
                        mBookPathList.clear();
                        LocalBook.deleteAllRecord(context);
                        for (File file : DirList) {
                            mBookPathList=searchFiles(file.getPath(),DOCUMENT_EXTS,true,mBookPathList);

                        }
                        if(!mBookPathList.isEmpty()){
                            for(String path:mBookPathList) {
                                File child=new File(path);
                                String absolutePath = child.getAbsolutePath();
                                if (FileUtil.isNormalFile(absolutePath) && FileUtil.shouldShowFile(absolutePath)) {
                                    FileInfo lFileInfo = FileUtil.GetFileInfo(child, null, false);
                                    if (lFileInfo != null && !lFileInfo.IsDir) {
                                        LocalBook book = LocalBook.getLocalBook(context, lFileInfo.filePath);
                                        if (book == null) {
                                            try{
                                                LocalBook.importLocalBook(context, lFileInfo);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }

                                        }
                                    }
                                }
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();
    }



    public static File findFileByName(String name){
        synchronized (mBookPathList){
            if(mBookPathList==null||mBookPathList.isEmpty()) return null;
            for(String path:mBookPathList){
                File file=new File(path);
                String fileName=file.getName();
                if(fileName.contains(name)){
                    return file;
                }
            }
        }
        return null;
    }

    public static ArrayList<String> getBookPathList(){
        return mBookPathList;
    }

    public static void openFile(Context context ,File file,int bookID){
        final Uri data = Uri.fromFile(file);
        CodecType codecType = CodecType.getByUri(data.toString());

        if (codecType.getContextClass().getSimpleName().equals(EpubContext.class.getSimpleName())) {
            if(codecType.compareTo(CodecType.TXT)==0){
                HwTxtPlayActivity.loadTxtFile(context, file.getPath());
            }else {
                Uri uri = Uri.parse(file.getPath());
                Intent intent = new Intent(context, PdfActivity.class);
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(uri);
                context.startActivity(intent);

            }

        } else {

            Uri uri = Uri.parse(file.getPath());
            Intent intent = new Intent(context, PdfActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(uri);
            context.startActivity(intent);
        }
        String name=file.getName();
        int index=name.lastIndexOf(".");
        if(index>=0){
            name=name.substring(0,index);
        }

        SharePrefUtil.getInstance().setLastBookName(name);
        SharePrefUtil.getInstance().setLastBookId(bookID);

    }

    public static String getSavePath(String path, int chapter) {
        File saveDir=new File(Environment.getExternalStorageDirectory().getPath()+File.separator+"chapterBynote");
        String fileName=encode(path+chapter)+".note";
        if(!saveDir.exists()) saveDir.mkdirs();
        return saveDir.getPath()+File.separator+fileName;
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
}
