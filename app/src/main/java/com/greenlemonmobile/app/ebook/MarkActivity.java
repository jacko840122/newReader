package com.greenlemonmobile.app.ebook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.kuaxue.utils.FileUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MarkActivity extends AppCompatActivity {


    @BindView(R.id.tv_return)
    TextView mTvReturn;
    @BindView(R.id.rv_thumbnails)
    RecyclerView mRvThumbnails;
    private String mBookFilePath;
    private String mThumbnailDir;
    private ThumbnailsAdapter mThumbnailsAdapter;

    private void initViews() {
        Intent intent = getIntent();
        mBookFilePath = intent.getStringExtra("BookFilePath");
        if (!TextUtils.isEmpty(mBookFilePath)) {
            mThumbnailDir = FileUtil.getmThumbnailDir(mBookFilePath);
            mThumbnailList=getThumbnailList(mThumbnailDir);
            mThumbnailsAdapter=new ThumbnailsAdapter(mThumbnailList);
            mRvThumbnails.setLayoutManager(new GridLayoutManager(this,4,RecyclerView.VERTICAL,false));
            mRvThumbnails.addItemDecoration(new RecyclerGridLayoutItemDecoration(4,20,true));
            mRvThumbnails.setAdapter(mThumbnailsAdapter);
        }
    }

    private File[] mThumbnailList;

    private File[] getThumbnailList(String thumbnailDir){

        File dir=new File(thumbnailDir);
        return dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if(file.isFile()){
                    return file.getAbsolutePath().toLowerCase().endsWith("png");
                }
                return false;
            }
        });

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_mark_activity);
        ButterKnife.bind(this);
        initViews();
    }

    @OnClick({R.id.tv_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_return:
                finish();
                break;
        }
    }

    protected class ThumbnailsAdapter extends RecyclerView.Adapter implements View.OnClickListener {


        private File[] mThumbnailList;


        public ThumbnailsAdapter(File[] thumbnailList) {
            mThumbnailList = thumbnailList;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.thumbnail_item, parent, false);
            return new ViewHolder(root);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ThumbnailsAdapter.ViewHolder viewHolder= (ThumbnailsAdapter.ViewHolder)holder;
            File file=mThumbnailList[position];
            ImageLoader.getInstance().displayImage(Uri.fromFile(file).toString(),viewHolder.mIcon);
            int index= Integer.parseInt(file.getName().toLowerCase().replace(".png",""));
            viewHolder.mTitle.setText("第"+(index+1)+"页");
            viewHolder.mThumbnailItem.setTag(mThumbnailList[position]);
            viewHolder.mThumbnailItem.setOnClickListener(this);
        }

        @Override
        public int getItemCount() {
            return mThumbnailList == null ? 0 : mThumbnailList.length;
        }

        @Override
        public void onClick(View view) {
            File file= (File) view.getTag();

            int resultCode=-1;

            try {
                resultCode=Integer.valueOf(file.getName().toLowerCase().replace(".png",""));
                setResult(resultCode);
                finish();
            }catch (Exception e){
                e.printStackTrace();
            }

        }


        protected class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.icon)
            ImageView mIcon;
            @BindView(R.id.title)
            TextView mTitle;
            @BindView(R.id.thumbnail_item)
            LinearLayout mThumbnailItem;

            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);

            }
        }
    }
}
