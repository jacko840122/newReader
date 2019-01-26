package com.greenlemonmobile.app.ebook;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.by.api.hw.ByHwProxy;
import com.by.hw.drawcomponent.ByNote;
import com.by.hw.util.CommonUtil;
import com.common.http.FileEntity;
import com.common.http.MultipartRequest;
import com.common.http.NetReqUtils;
import com.common.kuaxue.utils.FileUtil;

import org.json.JSONObject;

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
    private String mNoteFilePath;
    private String mChapterName;
    private boolean mIsModified;


    private void initData() {
        Intent intent = getIntent();
        mBookId = intent.getIntExtra("BookId", -1);
        mChapterId = intent.getIntExtra("ChapterId", 0);
        mChapterName = intent.getStringExtra("ChapterName");
        mBookFilePath = intent.getStringExtra("BookFilePath");
        mIsModified=intent.getBooleanExtra("IsModified",false);
        mNoteFilePath=FileUtil.getSaveNotePath(mBookFilePath,mChapterId);
        if(mChapterName!=null){
            mTvReturn.setText(mChapterName);
        }else {
            mTvReturn.setText("读后感");
        }
        if(mIsModified){
            mIvPublish.setImageResource(R.drawable.bt_modify);
        }else {
            mIvPublish.setImageResource(R.drawable.bt_publish);
        }
        mByNote.setPenTopLineErase(true);
        if(mIsModified&&!TextUtils.isEmpty(mNoteFilePath)){
            mByNote.loadNoteDataFromeFile(mNoteFilePath);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_publish);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            CommonUtil.drawEnable();
            ByHwProxy.drawUnlock();
        }catch (Throwable e){
            e.printStackTrace();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        try {
            CommonUtil.drawDisable();
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void publish() {
        File notefile = new File(mNoteFilePath);
        File pngfile = new File(mNoteFilePath+".png");
        if(notefile.exists()){
            notefile.delete();
        }
        if(pngfile.exists()){
            pngfile.delete();
        }
//        Bitmap bitmap=mByNote.getNoteAndBgBitmap();
//        FileUtil.saveBitMap(bitmap,pngfile.getPath());
        mByNote.saveNoteAsFile(mNoteFilePath);
        mByNote.saveViewAsPng(pngfile.getPath());
        HashMap<String, Object> extraParams = new HashMap<>();
        extraParams.put("bid", mBookId);
        extraParams.put("chapter", mChapterId);
        if(mIsModified){
            extraParams.put("type","edit");
        }else {
            extraParams.put("type","add");
        }

        extraParams.put("uid",7);


        FileEntity fileEntity = new FileEntity();
        fileEntity.mMime="image/*";
        fileEntity.mFile =pngfile ;
        fileEntity.mFileName =pngfile.getName();


        MultipartRequest multipartRequest = new MultipartRequest(BASE_URL + ACTION_SAVE_FEEL, extraParams, fileEntity, this, this);
        NetReqUtils.AddRequest(this, this, multipartRequest);
    }


    @Override
    public void onResponse(String response) {
        Log.d(TAG,"response="+response);
        try{
            JSONObject jsonObject=new JSONObject(response);
            if(jsonObject.getBoolean("success")==true){
                Toast.makeText(this, "上传数据成功!", Toast.LENGTH_SHORT).show();
                return;
            }

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "上传数据异常!", Toast.LENGTH_SHORT).show();
        }

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
