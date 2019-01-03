package com.greenlemonmobile.app.ebook;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.search_btn)
    ImageView mSearchBtn;
    @BindView(R.id.icon_btn)
    ImageView mIconBtn;
    @BindView(R.id.rc_sort)
    RecyclerView mRcSort;
    @BindView(R.id.iv_cover)
    ImageView mIvCover;
    @BindView(R.id.tv_book_name)
    TextView mTvBookName;
    @BindView(R.id.tv_book_author)
    TextView mTvBookAuthor;
    @BindView(R.id.tv_person_count)
    TextView mTvPersonCount;
    @BindView(R.id.tv_read_time)
    TextView mTvReadTime;
    @BindView(R.id.tv_create_count)
    TextView mTvCreateCount;
    @BindView(R.id.tv_brief)
    TextView mTvBrief;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.rv_book_container)
    RelativeLayout mTvBookContainer;
    @BindView(R.id.rv_review)
    RecyclerView mRvReview;
    @BindView(R.id.search_bar2)
    LinearLayout searchBar2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ButterKnife.bind(this);
        checkPermissions();
    }


    private void checkPermissions() {
        new RxPermissions(this).request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean granted) {
                        if (granted) { // 在android 6.0之前会默认返回true
                            // 已经获取权限
                        } else {
                            // 未获取权限
                            Toast.makeText(MainActivity.this, "您没有授权该权限，请在设置中打开授权", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick({R.id.search_btn, R.id.icon_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_btn:
                break;
            case R.id.icon_btn:
                break;
        }
    }
}
