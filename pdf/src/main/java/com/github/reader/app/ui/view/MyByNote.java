package com.github.reader.app.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.by.api.hw.ByHwProxy;
import com.by.hw.drawcomponent.ByNote;
import com.github.reader.utils.FileUtil;

import java.io.File;

public class MyByNote extends ByNote {
    private static final String TAG = "MyByNote";
    private int mPageIndex;
    private String mPath;

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
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if(motionEvent.getToolType(0)==MotionEvent.TOOL_TYPE_FINGER){
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
        Log.d(TAG,"mPageIndex="+ mPageIndex +"--SavePath="+FileUtil.getSavePath(mPath, mPageIndex));
        File file=new File(FileUtil.getSavePath(mPath, mPageIndex));
        if(file.exists()) file.delete();
        saveNoteAsFile(file.getPath());
        //clearAll();
        //ByHwProxy.clearAll();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mySaveNoteAsFile();
    }



}
