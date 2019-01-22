package com.common.Ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.common.http.data.Pzlist;
import com.example.commonlibrary.R;

import java.util.List;

public class CommentsList extends PopupWindow {

    private  List<Pzlist.DataBean> mCommentList;
    private  int mCurrentChapter;
    private Context mContext;

    private int mViewHeight;
    private ViewGroup mRootView;
    private LayoutInflater mInflator;
    private TextView mTvComment;

    public CommentsList(Context context, int viewHeight, List<Pzlist.DataBean> pzlist, int currentChapter) {
        super(context);
        mContext = context;
        mViewHeight = viewHeight;
        mCommentList=pzlist;
        mCurrentChapter=currentChapter;
        initRootView();
    }

    private String getCurChapterComment(List<Pzlist.DataBean> pzlist,int currentChapter ){
        StringBuilder stringBuilder=new StringBuilder();
        if(pzlist!=null&&!pzlist.isEmpty()){
            for (Pzlist.DataBean dataBean:pzlist){
                try {
                    if(currentChapter<0||Integer.valueOf(dataBean.getChapter())==currentChapter){
                        stringBuilder.append("<big><b><tt>"+dataBean.getPizhu()+"</tt></b></big><br/>");
                        stringBuilder.append("<small>"+dataBean.getText()+"</small><br/>");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }

            }
        }
        return stringBuilder.toString();
    }

    public  void setCommentData(List<Pzlist.DataBean> pzlist){
        mCommentList=pzlist;
        setCurChapter(mCurrentChapter);
    }

    public void setCurChapter(int currentChapter){
        mTvComment.setVisibility(View.INVISIBLE);
        String htmlStr=getCurChapterComment(mCommentList,currentChapter+1);

        if(TextUtils.isEmpty(htmlStr)){
            mTvComment.setText("无");
        }else {
            mTvComment.setText(Html.fromHtml(htmlStr));
        }
        mTvComment.setVisibility(View.VISIBLE);
    }



    protected void initRootView() {
        WindowManager m = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mInflator = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        m.getDefaultDisplay().getMetrics(metrics);

        int ViewHeight = mViewHeight;
        int ViewWidth = metrics.widthPixels/2;

        mRootView= (ViewGroup) mInflator.inflate(R.layout.comments_list,null);
        setContentView(mRootView);
        mTvComment=mRootView.findViewById(R.id.tv_comment);
        String htmlStr=getCurChapterComment(mCommentList,-1);

        if(TextUtils.isEmpty(htmlStr)){
            mTvComment.setText("无");
        }else {
            mTvComment.setText(Html.fromHtml(htmlStr));
        }
        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        setWidth(ViewWidth);
        setHeight(ViewHeight);
        setFocusable(true);
        setOutsideTouchable(true);
    }



    public void onDestroy() {
        mContext = null;
        mRootView = null;
    }
}
