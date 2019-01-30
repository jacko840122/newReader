package com.common.Ui;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.commonlibrary.R;

public class PopMore implements View.OnClickListener {

    private PopupWindow mPopupWindow = null;
    private View rootView = null;
    private Activity activityTarget = null;

    private TextView mTvBright;
    private TextView mTvPen;
    private TextView mTvSearch;

    public void setListener(ItemOnclickedListener mListener) {
        this.mListener = mListener;
    }

    private ItemOnclickedListener mListener;

    @Override
    public void onClick(View v) {
        mPopupWindow.dismiss();
        if (mListener != null) {
            mListener.onItemClick(v);
        }
    }

    public PopMore (Activity activity) {
        activityTarget = activity;
        rootView = LayoutInflater.from(activity).inflate(R.layout.layout_more_menu, null);
        mTvBright = rootView.findViewById(R.id.menu_pen);
        mTvBright.setOnClickListener(this);
        mTvPen = rootView.findViewById(R.id.menu_ballpen);
        mTvPen.setOnClickListener(this);

        mTvSearch = rootView.findViewById(R.id.menu_ballpen);
        mTvSearch.setOnClickListener(this);

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
            }
        });
    }


    public void showAsDropDown(View anchor, int xoff, int yoff) {
        //WindowManager.LayoutParams lp = this.activityTarget.getWindow().getAttributes();
        //lp.alpha = 0.5f;
        mPopupWindow.showAsDropDown(anchor, xoff, yoff);
    }

    public interface ItemOnclickedListener {

        void onItemClick(View v);
    }
}
