package com.greenlemonmobile.app.ebook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.http.data.Feellist;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeelDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_return)
    TextView mTvReturn;
    @BindView(R.id.iv_feel_img)
    ImageView mIvFeelImg;
    private Feellist.DataBean mFeeldata;

    private void initData() {
        Intent intent = getIntent();
        mFeeldata= (Feellist.DataBean) intent.getSerializableExtra("feeldata");
        if(mFeeldata!=null){
            ImageLoader.getInstance().displayImage(mFeeldata.getPath()+mFeeldata.getName(),mIvFeelImg);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_feel_detail_layout);
        ButterKnife.bind(this);
        initData();



    }

    @OnClick(R.id.tv_return)
    public void onViewClicked() {
        finish();
    }


}
