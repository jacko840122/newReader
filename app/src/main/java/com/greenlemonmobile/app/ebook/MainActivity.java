package com.greenlemonmobile.app.ebook;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.common.Utils.SharePrefUtil;
import com.common.http.NetReqUtils;
import com.common.http.data.Books_info;
import com.common.http.data.Categorymenu;
import com.common.http.data.Feellist;
import com.common.kuaxue.utils.FileUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
import static com.common.http.NetReqUtils.ACTION_BOOKSKEY_INFO;
import static com.common.http.NetReqUtils.ACTION_BOOKS_INFO;
import static com.common.http.NetReqUtils.ACTION_CATEGORY;
import static com.common.http.NetReqUtils.ACTION_GET_FEEL_LIST;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener {


    private static final String TAG = "MainActivity";
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

    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.rv_book_container)
    RelativeLayout mTvBookContainer;
    @BindView(R.id.rv_review)
    RecyclerView mRvReview;
    @BindView(R.id.search_bar2)
    LinearLayout searchBar2;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_brief)
    TextView tvBrief;
    private SortAdapter mSortAdapter;
    private Response.Listener<Categorymenu> mCatListener = new Response.Listener<Categorymenu>() {
        @Override
        public void onResponse(Categorymenu response) {
            mCategorymenu = response;
        }
    };
    private Response.Listener<Books_info> mBookListener = new Response.Listener<Books_info>() {
        @Override
        public void onResponse(Books_info response) {
            mBooks_info = response;
            bindBookInfo();
            if (mBook_info != null) {
                HashMap<String, Object> extraParams = new HashMap<>();
                extraParams.put("bid", mBook_info.getId());
                //extraParams.put("bid",68);
                NetReqUtils.addGsonRequest(MainActivity.this, POST, TAG, null, extraParams, ACTION_GET_FEEL_LIST,
                        Feellist.class, mFeelsListener, MainActivity.this);
            }

        }
    };
    private List<Feellist.DataBean> mFeellist;
    private FeelAdapter mFeelAdapter;

    private void bindBookInfo() {
        if (mBooks_info != null && mBooks_info.getData() != null
                && !mBooks_info.getData().isEmpty()) {
            Books_info.DataBean boo_info = mBooks_info.getData().get(0);
            mBook_info = boo_info;
            String cover = boo_info.getB_cover();
            String author = boo_info.getB_author();
            String introduction = boo_info.getB_introduction();
            String name = boo_info.getB_name();
            String Readnum = boo_info.getReadnum();
            if (TextUtils.isEmpty(cover) || cover.length() < 5) {
                mIvCover.setImageResource(R.drawable.default_cover);
                mTvTitle.setVisibility(View.VISIBLE);
                mTvTitle.setText(name);
            } else {
                ImageLoader.getInstance().displayImage(NetReqUtils.BASE_URL + cover, mIvCover);
                mTvTitle.setVisibility(View.INVISIBLE);
            }

            if (!TextUtils.isEmpty(author)) {
                mTvBookAuthor.setText(author);
            }

            if (!TextUtils.isEmpty(introduction)) {
                mTvContent.setText(introduction);
            }
            if (!TextUtils.isEmpty(name)) {
                mTvBookName.setText(name);
            }

            if (!TextUtils.isEmpty(Readnum)) {
                mTvPersonCount.setText(Readnum + "人在读");
            }
            mTvBookContainer.setVisibility(View.VISIBLE);
        } else {
            //mTvBookContainer.setVisibility(View.INVISIBLE);
        }
    }

    private Categorymenu mCategorymenu;
    private Books_info mBooks_info;
    private Books_info.DataBean mBook_info;
    private Response.Listener<Feellist> mFeelsListener = new Response.Listener<Feellist>() {
        @Override
        public void onResponse(Feellist response) {
            if (response != null && response.getData() != null && !response.getData().isEmpty()) {
                mFeellist = response.getData();
                Collections.sort(mFeellist,new Comparator<Feellist.DataBean>() {
                    @Override
                    public int compare(Feellist.DataBean o1, Feellist.DataBean o2) {
                        int ret=-1;
                        try{
                            ret=Integer.valueOf(o2.getTime())-Integer.valueOf(o1.getTime());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        return ret;

                    }
                });
                bindFeelData();
            }
        }
    };

    private void bindFeelData() {
        mRvReview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mFeelAdapter = new FeelAdapter(mFeellist);
        mRvReview.setItemAnimator(new DefaultItemAnimator());
        mRvReview.setAdapter(mFeelAdapter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ButterKnife.bind(this);
        initViews();
//        getNetData();
        checkPermissions();

    }

    private void getNetData() {
        NetReqUtils.addGsonRequest(this, POST, TAG, null, null, ACTION_CATEGORY,
                Categorymenu.class, mCatListener, this);

        HashMap<String, Object> extraParams = new HashMap<>();
        int booksid = SharePrefUtil.getInstance().getLastBookId();
        String name = SharePrefUtil.getInstance().getLastBookName();
        if (booksid > 0) {
            extraParams.put("booksid", booksid);
            NetReqUtils.addGsonRequest(this, POST, TAG, null, extraParams, ACTION_BOOKS_INFO,
                    Books_info.class, mBookListener, this);

        } else {
            extraParams.put("booksname", name);
            NetReqUtils.addGsonRequest(this, POST, TAG, null, extraParams, ACTION_BOOKSKEY_INFO,
                    Books_info.class, mBookListener, this);
        }

    }


    private void initViews() {
        mRcSort.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        List<SortItem> list = new ArrayList<SortItem>();
        list.add(new SortItem(R.drawable.wenxun, "文学经典"));
        list.add(new SortItem(R.drawable.xiuyang, "人生修养"));
        list.add(new SortItem(R.drawable.minren, "名人传记"));
        list.add(new SortItem(R.drawable.kexue, "科学技术"));
        mSortAdapter = new SortAdapter(list);
        mRcSort.setItemAnimator(new DefaultItemAnimator());
        mRcSort.setAdapter(mSortAdapter);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(MainActivity.this, "获取数据有异常!", Toast.LENGTH_SHORT).show();
        error.printStackTrace();
    }

    class SortItem {
        int mResId;
        String mName;

        SortItem(int resId, String name) {
            mResId = resId;
            mName = name;
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
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
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
            SortItem sortItem = (SortItem) view.getTag();
            Intent intent = new Intent(MainActivity.this, LibraryActivity.class);
            intent.putExtra("Subdir", sortItem.mName);
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

    public class FeelAdapter extends RecyclerView.Adapter<FeelAdapter.ViewHolder> implements View.OnClickListener {
        private List<Feellist.DataBean> list;

        public FeelAdapter(List<Feellist.DataBean> list) {
            this.list = list;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feel_item_sort, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String url = list.get(position).getPath() + list.get(position).getName();
            if (!TextUtils.isEmpty(url)) {
                ImageLoader.getInstance().displayImage(url, holder.mIcon);
            }

            holder.mTvFeelInfo.setText(mBook_info.getB_name() + "第" + list.get(position).getChapter() + "章");
            holder.mIcon.setTag(list.get(position));
            holder.mIcon.setOnClickListener(this);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, FeelListActivity.class);
            intent.putExtra("BookInfo", mBook_info);
            intent.putExtra("FilePath", FileUtil.findFileByName(mBook_info.getB_name()).getPath());
            intent.putExtra("feellist", (Serializable) list);
            startActivity(intent);
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView mIcon;
            TextView mTvFeelInfo;

            ViewHolder(View itemView) {
                super(itemView);
                mIcon = itemView.findViewById(R.id.sort_icon);
                mTvFeelInfo = itemView.findViewById(R.id.tv_feel_info);
            }
        }
    }


    private void checkPermissions() {
        String[] perssions = {
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET
        };
        new RxPermissions(this).request(perssions)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean granted) {
                        if (granted) { // 在android 6.0之前会默认返回true
                            // 已经获取权限
                            FileUtil.searchFiles(MainActivity.this);
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
        getNetData();
    }

    @OnClick({R.id.search_btn, R.id.icon_btn, R.id.rv_book_container})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.search_btn:
                intent = new Intent(this, BookStoreActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_btn:
                break;

            case R.id.rv_book_container:
                if (mBook_info == null) return;
                File file = FileUtil.findFileByName(mBook_info.getB_name());
                if (file != null && file.exists()) {
                    FileUtil.openFile(this, file, Integer.valueOf(mBook_info.getId()));
                } else {
                    Toast.makeText(this, "图书没有下载", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
