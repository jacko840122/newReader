package com.github.reader.app.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.by.api.hw.ByHwProxy;
import com.by.hw.drawcomponent.ByNote;
import com.common.Utils.SharePrefUtil;
import com.github.reader.utils.FileUtil;

import java.io.File;

public class MyByNote extends ByNote {
    private static final String TAG = "MyByNote";
    private int mPageIndex;
    private String mPath;
    private boolean mIsPen=true;
    private int mPenSize=0;

    public MyByNote(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyByNote(Context context) {
        super(context);
    }

    public  MyByNote(Context context,String path,int pageIndex) {
        super(context);
        mPath=path;
        mPageIndex=pageIndex;
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
        if(motionEvent.getToolType(0)==MotionEvent.TOOL_TYPE_STYLUS
                &&motionEvent.getButtonState()==32){
            return false;
        }
        boolean ret= super.onTouchEvent(motionEvent);
        if(motionEvent.getToolType(0)==MotionEvent.TOOL_TYPE_ERASER){
            ByHwProxy.clearAll();
        }

        return ret;

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        myLoadNoteDataFromeFile();
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        super.onLayout(b, i, i1, i2, i3);
    }

    @Override
    protected void onVisibilityChanged(View view, int visibility) {
        super.onVisibilityChanged(view, visibility);
        if(View.VISIBLE!=visibility){
            mySaveNoteAsFile();
        }else {
            myLoadNoteDataFromeFile();
        }
    }

    private void myLoadNoteDataFromeFile(){
        if(getWidth()>0){
            clearAll();
            ByHwProxy.clearAll();
            Log.d(TAG, "mPageIndex=" + mPageIndex + "--myLoadNoteDataFromeFile=" + FileUtil.getSavePath(mPath, mPageIndex));
            loadNoteDataFromeFile(FileUtil.getSavePath(mPath, mPageIndex));
            setPenTopLineErase(true);
        }else {
            post(new Runnable() {
                @Override
                public void run() {
                    clearAll();
                    ByHwProxy.clearAll();
                    Log.d(TAG, "mPageIndex=" + mPageIndex + "--myLoadNoteDataFromeFile=" + FileUtil.getSavePath(mPath, mPageIndex));
                    loadNoteDataFromeFile(FileUtil.getSavePath(mPath, mPageIndex));
                    setPenTopLineErase(true);
                }
            });
        }
    }

    private void mySaveNoteAsFile(){
        Log.d(TAG,"pageIndex="+ mPageIndex +"--SavePath="+FileUtil.getSavePath(mPath, mPageIndex));
        File file=new File(FileUtil.getSavePath(mPath, mPageIndex));
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

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mySaveNoteAsFile();
    }



}
