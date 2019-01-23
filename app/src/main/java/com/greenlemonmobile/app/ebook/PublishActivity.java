package com.greenlemonmobile.app.ebook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.by.hw.drawcomponent.ByNote;
import com.common.http.FileEntity;
import com.common.http.MultipartRequest;
import com.common.http.NetReqUtils;

import java.io.File;
import java.util.HashMap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.common.http.NetReqUtils.ACTION_SAVE_FEEL;
import static com.common.http.NetReqUtils.BASE_URL;

public class PublishActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener {


    private static final String TAG = "PublishActivity";
    @BindView(R.id.tv_return)
    TextView mTvReturn;
    @BindView(R.id.by_note)
    ByNote mByNote;
    @BindView(R.id.iv_publish)
    ImageView mIvPublish;
    private int mBookId;
    private int mChapterId;
    private String mBookFilePath;
    private String mNoteFilePath = "/mnt/sdcard/666888777.png";


    private void initData() {
        Intent intent = getIntent();
        mBookId = intent.getIntExtra("BookId", 68);
        mChapterId = intent.getIntExtra("ChapterId", 1);
        ;
        mBookFilePath = intent.getStringExtra("BookFilePath");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_publish);
        ButterKnife.bind(this);
        initData();
    }


    private void publish() {
        HashMap<String, Object> extraParams = new HashMap<>();
        extraParams.put("bid", mBookId);
        extraParams.put("chapter", mChapterId);
        extraParams.put("chapter", mChapterId);
        extraParams.put("type","add");

        extraParams.put("name", "volley_single_file_name");
        extraParams.put("value", "volley_single_file_value");

        FileEntity fileEntity = new FileEntity();
        fileEntity.mName = "666888777";
        fileEntity.mFileName = "666888777.png";
        fileEntity.mFile = new File(mNoteFilePath);


        MultipartRequest multipartRequest = new MultipartRequest(BASE_URL + ACTION_SAVE_FEEL, extraParams, fileEntity, this, this);
        NetReqUtils.AddRequest(this, this, multipartRequest);
    }


    @Override
    public void onResponse(String response) {
        Log.d(TAG,"response="+response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "上传数据异常!", Toast.LENGTH_SHORT).show();
        error.printStackTrace();
    }

    @OnClick({R.id.tv_return, R.id.iv_publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_return:
                finish();
                break;
            case R.id.iv_publish:
                publish();
                break;
        }
    }
}
