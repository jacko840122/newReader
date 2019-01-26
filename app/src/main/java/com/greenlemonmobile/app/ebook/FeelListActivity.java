package com.greenlemonmobile.app.ebook;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.http.data.Books_info;
import com.common.http.data.Feellist;
import com.common.kuaxue.utils.FileUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeelListActivity extends AppCompatActivity {

    @BindView(R.id.tv_return)
    TextView tvReturn;
    @BindView(R.id.rv_feel_list)
    RecyclerView mRvFeelList;

    private List<Feellist.DataBean> mFeellist;

    private FeelAdapter mFeelAdapter;
    private String mFilePath;
    private Books_info.DataBean mBook_info;


    private void bindFeelData() {
        Intent intent=getIntent();
        mFeellist= (List<Feellist.DataBean>) intent.getSerializableExtra("feellist");
        mBook_info= (Books_info.DataBean) intent.getSerializableExtra("BookInfo");
        mFilePath=intent.getStringExtra("FilePath");
        mRvFeelList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mFeelAdapter = new FeelAdapter(mFeellist);
        mRvFeelList.setItemAnimator(new DefaultItemAnimator());
        mRvFeelList.setAdapter(mFeelAdapter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_feel_list_layout);
        ButterKnife.bind(this);
        bindFeelData();
    }

    @OnClick(R.id.tv_return)
    public void onViewClicked() {
        finish();
    }


    public class FeelAdapter extends RecyclerView.Adapter<FeelAdapter.ViewHolder> implements View.OnClickListener {
        private List<Feellist.DataBean> list;

        public FeelAdapter(List<Feellist.DataBean> list) {
            this.list = list;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feel_item_sort2, parent, false);
            FeelAdapter.ViewHolder viewHolder = new FeelAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(FeelAdapter.ViewHolder holder, int position) {
            Feellist.DataBean dataBean=list.get(position);
            String url=dataBean.getPath()+dataBean.getName();
            if(!TextUtils.isEmpty(url)){
                ImageLoader.getInstance().displayImage(url,holder.mIcon);
            }
            if(FileUtil.saveNoteExist(mFilePath, Integer.parseInt(dataBean.getChapter()))){
                holder.mIvModify.setVisibility(View.VISIBLE);
                holder.mIvModify.setTag(Integer.parseInt(dataBean.getChapter()));
                holder.mIvModify.setOnClickListener(this);
            }else {
                holder.mIvModify.setVisibility(View.INVISIBLE);
            }

            holder.mIcon.setTag(list.get(position));
            holder.mIcon.setOnClickListener(this);

        }

        @Override
        public int getItemCount() {
            return list==null?0:list.size();
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.sort_icon:
                    Feellist.DataBean dataBean= (Feellist.DataBean) view.getTag();
                    if(dataBean!=null){
                        Intent intent=new Intent(FeelListActivity.this,FeelDetailActivity.class);
                        intent.putExtra("feeldata", dataBean);
                        startActivity(intent);

                    }
                    break;

                case R.id.iv_modify:
                    int chapter= (int) view.getTag();
                    openPublish(chapter);
                    break;
            }

        }

        private void openPublish(int chapter){
            try{
                Intent intent=new Intent("com.greenlemonmobile.app.ebook.Publish");
                intent.putExtra("BookId", Integer.valueOf(mBook_info.getId()));
                intent.putExtra("ChapterId", chapter);
                intent.putExtra("BookFilePath",mFilePath);
                intent.putExtra("IsModified",true);
                startActivity(intent);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageButton mIvModify;
            ImageView mIcon;
            TextView mTvFeelInfo;
            ViewHolder(View itemView) {
                super(itemView);
                mIcon = itemView.findViewById(R.id.sort_icon);
                mTvFeelInfo= itemView.findViewById(R.id.tv_feel_info);
                mIvModify= itemView.findViewById(R.id.iv_modify);
            }
        }
    }
}
