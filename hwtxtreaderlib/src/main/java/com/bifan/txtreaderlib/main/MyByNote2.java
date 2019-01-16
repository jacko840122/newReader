package com.bifan.txtreaderlib.main;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.bifan.txtreaderlib.utils.FileUtil;
import com.by.api.hw.ByHwProxy;
import com.by.hw.drawcomponent.ByNote;

import java.io.File;

public class MyByNote2 extends ByNote {

    private static final String TAG = "MyByNote2";

    public MyByNote2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyByNote2(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if(motionEvent.getToolType(0)==MotionEvent.TOOL_TYPE_FINGER){
            return false;
        }
        return super.onTouchEvent(motionEvent);

    }

    public void myLoadNoteDataFromeFile(final String path, final int pageIndex) {
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


    public void mySaveNoteAsFile(String path, int pageIndex) {

        Log.d(TAG,"pageIndex="+ pageIndex +"--SavePath="+FileUtil.getSavePath(path, pageIndex));
        File file=new File(FileUtil.getSavePath(path, pageIndex));
        if(file.exists()) file.delete();
        saveNoteAsFile(file.getPath());
        //clearAll();
        //ByHwProxy.clearAll();
    }
}
