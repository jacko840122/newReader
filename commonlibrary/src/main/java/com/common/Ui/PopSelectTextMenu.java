package com.common.Ui;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.commonlibrary.R;

public class PopSelectTextMenu implements View.OnClickListener {
    private PopupWindow mPopupWindow = null;
    private View rootView = null;
    private Activity activityTarget = null;
    private PopSelectTextMenuListener listener = null;
    private TextView btnCopy;
    private TextView btnDict;

    public PopSelectTextMenu(Activity activity) {
        this.activityTarget = activity;
        rootView = LayoutInflater.from(activity).inflate(R.layout.selection_panel, null);
        btnCopy = rootView.findViewById(R.id.selection_panel_copy);
        btnCopy.setOnClickListener(this);
        btnDict = rootView.findViewById(R.id.selection_panel_translate);
        btnDict.setOnClickListener(this);

        mPopupWindow = new PopupWindow(rootView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000001));
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //WindowManager.LayoutParams lp = activityTarget.getWindow().getAttributes();
                //lp.alpha = 1f;
                //activityTarget.getWindow().setAttributes(lp);
                if (listener != null) {
                    listener.onDismiss();
                }
            }
        });


    }

    public void setPopSelectTextMenuListener(PopSelectTextMenuListener l) {
        this.listener = l;
    }

    public void show(boolean isPen, int penSize, View parent) {
        btnCopy.setSelected(isPen);
        btnDict.setSelected(!isPen);
        //WindowManager.LayoutParams lp = this.activityTarget.getWindow().getAttributes();
        //lp.alpha = 0.5f;
        mPopupWindow.update();
        mPopupWindow.showAtLocation(parent, Gravity.CENTER,0,0);
    }

    public void show(View anchor, float xoff, float yoff) {
        //WindowManager.LayoutParams lp = this.activityTarget.getWindow().getAttributes();
        //lp.alpha = 0.5f;
        mPopupWindow.showAtLocation(anchor,Gravity.LEFT|Gravity.TOP, (int)xoff, (int)yoff);
    }

    @Override
    public void onClick(View v) {
        //mPopupWindow.dismiss();
        if (listener != null) {
            listener.onPopSelectTextMenuItemClick(v);
        }
        mPopupWindow.dismiss();

    }



    public interface PopSelectTextMenuListener {
        void onPopSelectTextMenuItemClick(View view);
        void onDismiss();
    }
}