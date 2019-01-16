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

    public MyByNote(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyByNote(Context context) {
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


    public void mySaveNoteAsFile(String path, int pageIndex) {

        Log.d(TAG,"pageIndex="+ pageIndex +"--SavePath="+FileUtil.getSavePath(path, pageIndex));
        File file=new File(FileUtil.getSavePath(path, pageIndex));
        if(file.exists()) file.delete();
        saveNoteAsFile(file.getPath());
        //clearAll();
        //ByHwProxy.clearAll();
    }
}
