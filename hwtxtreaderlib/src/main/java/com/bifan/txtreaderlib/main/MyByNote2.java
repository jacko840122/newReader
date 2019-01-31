package com.bifan.txtreaderlib.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.bifan.txtreaderlib.utils.FileUtil;
import com.by.api.hw.ByHwProxy;
import com.by.hw.drawcomponent.ByNote;
import com.common.Utils.SharePrefUtil;

import java.io.File;

public class MyByNote2 extends ByNote {

    private static final String TAG = "MyByNote2";
    private String mPath="";
    private int mPageIndex=-1;
    private int mPenSize=0;
    private boolean mIsPen=true;

    public MyByNote2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyByNote2(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
            if(SharePrefUtil.getInstance().getIsPen()!=mIsPen){
                mIsPen=SharePrefUtil.getInstance().getIsPen();
                if(mIsPen){
                    penMode();
                }else {
                    ballPenMode();
                }


            }
            if(SharePrefUtil.getInstance().getPenSize()!=mPenSize){
                mPenSize=SharePrefUtil.getInstance().getPenSize();
                setDrawLineWidth(mPenSize);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if(motionEvent.getToolType(0)==MotionEvent.TOOL_TYPE_FINGER){
            return false;
        }

        return super.onTouchEvent(motionEvent);

    }

    public void myLoadNoteDataFromeFile(final String path, final int pageIndex) {
        mPath=path;
        mPageIndex=pageIndex;
        if(getWidth()>0){
            clearAll();
            ByHwProxy.clearAll();
            Log.d(TAG, "pageIndex=" + pageIndex + "--myLoadNoteDataFromeFile=" + FileUtil.getSavePath(path, pageIndex));
            loadNoteDataFromeFile(FileUtil.getSavePath(path, pageIndex));
            setPenTopLineErase(true);
        }else {
            post(new Runnable() {
                @Override
                public void run() {
                    clearAll();
                    ByHwProxy.clearAll();
                    Log.d(TAG, "pageIndex=" + pageIndex + "--myLoadNoteDataFromeFile=" + FileUtil.getSavePath(path, pageIndex));
                    loadNoteDataFromeFile(FileUtil.getSavePath(path, pageIndex));
                    setPenTopLineErase(true);
                }
            });
        }

    }

    public  void myReloadNoteDataFromeFile(){
        myLoadNoteDataFromeFile(mPath,mPageIndex);
    }


    public void mySaveNoteAsFile(String path, int pageIndex) {

        Log.d(TAG,"pageIndex="+ pageIndex +"--SavePath="+FileUtil.getSavePath(path, pageIndex));
        File file=new File(FileUtil.getSavePath(path, pageIndex));
        File pngFile=new File(FileUtil.getmThumbnailDir(mPath)+File.separator+mPageIndex+".png");
        if(file.exists()) file.delete();
        if(pngFile.exists()) pngFile.delete();
        if(getStrokePathList()==null||getStrokePathList().isEmpty()) return;
        saveNoteAsFile(file.getPath());

//        Bitmap bitmap=getBimap();
//        FileUtil.saveBitMap(bitmap,pngFile.getPath());
        saveViewAsPng(pngFile.getPath());
        //clearAll();
        //ByHwProxy.clearAll();
    }
}
