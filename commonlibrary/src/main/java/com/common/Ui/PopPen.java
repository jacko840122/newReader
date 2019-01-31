package com.common.Ui;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.SeekBar;

import com.example.commonlibrary.R;


/**
 * @author qianciling
 * Time 2019/1/21 11:43
 * Describe:progress[0-100]
 */
public class PopPen implements View.OnClickListener {
    private PopupWindow mPopupWindow = null;
    private View rootView = null;
    private Activity activityTarget = null;
    private PopPenListener listener = null;
    private ImageButton btnPen;
    private ImageButton btnBallpen;
    private SeekBar penSeek = null;

    public PopPen(Activity activity) {
        this.activityTarget = activity;
        rootView = LayoutInflater.from(activity).inflate(R.layout.layout_pen_menu, null);
        btnPen = rootView.findViewById(R.id.menu_pen);
        btnPen.setOnClickListener(this);
        btnBallpen = rootView.findViewById(R.id.menu_ballpen);
        btnBallpen.setOnClickListener(this);
        penSeek= rootView.findViewById(R.id.pen_size);
        penSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (listener != null) {
                    listener.onPenSizeChanged(seekBar, progress, fromUser);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (listener != null) {
                    listener.onPenSizeStartTouch(seekBar);
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mPopupWindow.dismiss();
                if (listener != null) {
                    listener.onPenSizeStopTouch(seekBar);
                }
            }
        });

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

    public void setPopPenListener(PopPenListener l) {
        this.listener = l;
    }

    public void show(boolean isPen, int penSize, View parent) {
        btnPen.setSelected(isPen);
        btnBallpen.setSelected(!isPen);
        penSeek.setProgress(penSize);
        //WindowManager.LayoutParams lp = this.activityTarget.getWindow().getAttributes();
        //lp.alpha = 0.5f;
        mPopupWindow.update();
        mPopupWindow.showAtLocation(parent, Gravity.CENTER,0,0);
    }

    public void show(View anchor, int xoff, int yoff) {
        //WindowManager.LayoutParams lp = this.activityTarget.getWindow().getAttributes();
        //lp.alpha = 0.5f;
        mPopupWindow.showAtLocation(anchor,Gravity.CENTER, xoff, yoff);
    }

    @Override
    public void onClick(View v) {
        //mPopupWindow.dismiss();
        if(v.getId()==R.id.menu_pen){
            btnPen.setSelected(true);
            btnBallpen.setSelected(false);
        }else if(v.getId()==R.id.menu_ballpen){
            btnBallpen.setSelected(true);
            btnPen.setSelected(false);
        }
        if (listener != null) {
            listener.onPenClick(v);
        }
    }

    public interface PopPenListener {
        void onPenClick(View view);

        void onPenSizeChanged(SeekBar seekBar, int progress, boolean fromUser);

        void onPenSizeStartTouch(SeekBar seekBar);

        void onPenSizeStopTouch(SeekBar seekBar);
    }
}
