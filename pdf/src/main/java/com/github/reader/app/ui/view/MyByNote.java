package com.github.reader.app.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.by.api.hw.ByHwProxy;
import com.by.hw.drawcomponent.ByNote;
import com.github.reader.utils.FileUtil;

import java.io.File;

public class MyByNote extends ByNote {
    private static final String TAG = "MyByNote";
    private String mPath="";
    private int mPageIndex=0;

    public MyByNote(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyByNote(Context context) {
        super(context);
    }

    public MyByNote(Context context, String path, int pageIndex) {
        super(context);
        mPath=path;
        mPageIndex=pageIndex;
    }

    public void setMyByNote(String path, int pageIndex) {
        mPath=path;
        mPageIndex=pageIndex;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if(motionEvent.getToolType(0)==MotionEvent.TOOL_TYPE_FINGER){
            return false;
        }
        return super.onTouchEvent(motionEvent);

    }


    public void myLoadNoteDataFromeFile() {
        post(new Runnable() {
            @Override
            public void run() {
                clearAll();
                ByHwProxy.clearAll();
                Log.d(TAG, "pageIndex=" + mPageIndex + "--myLoadNoteDataFromeFile=" + FileUtil.getSavePath(mPath, mPageIndex));
                loadNoteDataFromeFile(FileUtil.getSavePath(mPath, mPageIndex));
                setPenTopLineErase(true);
            }
        });
    }


    public void mySaveNoteAsFile() {

        Log.d(TAG,"pageIndex="+ mPageIndex +"--SavePath="+FileUtil.getSavePath(mPath, mPageIndex));
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
