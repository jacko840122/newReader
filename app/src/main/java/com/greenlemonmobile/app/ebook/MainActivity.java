package com.greenlemonmobile.app.ebook;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.common.http.NetReqUtils;
import com.common.http.data.Books_info;
import com.common.http.data.Categorymenu;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
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
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.android.volley.Request.Method.POST;
import static com.common.http.NetReqUtils.ACTION_BOOKS_INFO;
import static com.common.http.NetReqUtils.ACTION_CATEGORY;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener {


    private static final String TAG ="MainActivity" ;
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
    private SortAdapter mSortAdapter;
    private Response.Listener<Categorymenu> mCatListener=new Response.Listener<Categorymenu>() {
        @Override
        public void onResponse(Categorymenu response) {

        }
    };
    private Response.Listener<Books_info> mBookListener=new Response.Listener<Books_info>() {
        @Override
        public void onResponse(Books_info response) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ButterKnife.bind(this);
        initViews();
        checkPermissions();
    }

    private void getNetData(){
        NetReqUtils.addGsonRequest(this,POST,TAG,null,null,ACTION_CATEGORY,
                Categorymenu.class,mCatListener,this);

        NetReqUtils.addGsonRequest(this,POST,TAG,null,null,ACTION_BOOKS_INFO,
                Books_info.class,mBookListener,this);
    }


    private void initViews(){
        mRcSort.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        List<SortItem> list=new ArrayList<SortItem>();
        list.add(new SortItem(R.drawable.wenxun,"文学"));
        list.add(new SortItem(R.drawable.xiuyang,"人生修养"));
        list.add(new SortItem(R.drawable.minren,"名人传记"));
        list.add(new SortItem(R.drawable.kexue,"科学技术"));
        mSortAdapter = new SortAdapter(list);
        mRcSort.setItemAnimator(new DefaultItemAnimator());
        mRcSort.setAdapter(mSortAdapter);

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    class SortItem{
        int mResId;
        String mName;

        SortItem(int resId,String name){
            mResId=resId;
            mName=name;
        }
    }





    public class SortAdapter extends RecyclerView.Adapter<SortAdapter.ViewHolder> implements View.OnClickListener {
        private List<SortItem> list;

        public SortAdapter(List<SortItem> list) {
            this.list = list;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sort, parent, false);
            SortAdapter.ViewHolder viewHolder = new SortAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(SortAdapter.ViewHolder holder, int position) {
            holder.mIcon.setImageResource(list.get(position).mResId);
            holder.mIcon.setTag(list.get(position));
            holder.mIcon.setOnClickListener(this);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public void onClick(View view) {
            SortItem sortItem= (SortItem) view.getTag();
            Intent intent = new Intent(MainActivity.this, LibraryActivity.class);
            intent.putExtra("Subdir",sortItem.mName);
            startActivity(intent);

        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView mIcon;
            ViewHolder(View itemView) {
                super(itemView);
                mIcon = itemView.findViewById(R.id.sort_icon);
            }
        }
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
