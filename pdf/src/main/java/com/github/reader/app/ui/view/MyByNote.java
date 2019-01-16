package com.github.reader.app.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

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
        return super.onTouchEvent(motionEvent);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        myLoadNoteDataFromeFile();
    }

    private void myLoadNoteDataFromeFile(){
        if(getWidth()>0){
            clearAll();
            //ByHwProxy.clearAll();
            Log.d(TAG, "mPageIndex=" + mPageIndex + "--myLoadNoteDataFromeFile=" + FileUtil.getSavePath(mPath, mPageIndex));
            loadNoteDataFromeFile(FileUtil.getSavePath(mPath, mPageIndex));
            setPenTopLineErase(true);
        }else {
            post(new Runnable() {
                @Override
                public void run() {
                    clearAll();
                    //ByHwProxy.clearAll();
                    Log.d(TAG, "mPageIndex=" + mPageIndex + "--myLoadNoteDataFromeFile=" + FileUtil.getSavePath(mPath, mPageIndex));
                    loadNoteDataFromeFile(FileUtil.getSavePath(mPath, mPageIndex));
                    setPenTopLineErase(true);
                }
            });
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG,"mPageIndex="+ mPageIndex +"--SavePath="+FileUtil.getSavePath(mPath, mPageIndex));
        File file=new File(FileUtil.getSavePath(mPath, mPageIndex));
        if(file.exists()) file.delete();
        saveNoteAsFile(file.getPath());
        //clearAll();
        //ByHwProxy.clearAll();
    }



}
