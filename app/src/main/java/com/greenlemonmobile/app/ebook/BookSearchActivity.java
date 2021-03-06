package com.greenlemonmobile.app.ebook;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.common.http.NetReqUtils;
import com.common.http.data.Books_info;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.android.volley.Request.Method.POST;
import static com.common.http.NetReqUtils.ACTION_BOOKS_LIST;

public class BookSearchActivity extends AppCompatActivity implements Response.ErrorListener {

    @BindView(R.id.tv_return)
    ImageView mTvReturn;
    @BindView(R.id.et_serch)
    EditText mEtSerch;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.tv_tip)
    TextView mTvTip;
    @BindView(R.id.rv_search_content)
    RecyclerView mRvSearchContent;
    private Response.Listener<Books_info> mBooksListener = new Response.Listener<Books_info>() {
        @Override
        public void onResponse(Books_info response) {
            mBooks_info = response;
            if (mBooks_info == null || mBooks_info.getData() == null ||
                    mBooks_info.getData().isEmpty()) {
                mTvTip.setText("没有搜索到图书");
                mTvTip.setVisibility(View.VISIBLE);
                mRvSearchContent.setVisibility(View.INVISIBLE);
            } else {
                mBooks_list = mBooks_info.getData();
                mTvTip.setVisibility(View.INVISIBLE);
                mRvSearchContent.setVisibility(View.VISIBLE);
                mSearchBooksAdapter.setBooksData(mBooks_list);
            }
        }
    };
    private Books_info mBooks_info;
    private List<Books_info.DataBean> mBooks_list;
    private SearchBooksAdapter mSearchBooksAdapter;

    private void searchBooks(String keyword) {
        HashMap<String, Object> extraParams = new HashMap<>();
        extraParams.put("start_option", 0);
        extraParams.put("limitnum", 1000);
        extraParams.put("keyword", keyword);
        NetReqUtils.addGsonRequest(this, POST, this, null, extraParams, ACTION_BOOKS_LIST,
                Books_info.class, mBooksListener, this);

        mTvTip.setText("正在搜索中...");
        mTvTip.setVisibility(View.VISIBLE);
        mRvSearchContent.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_book_search);
        ButterKnife.bind(this);
        mSearchBooksAdapter = new SearchBooksAdapter();
        mRvSearchContent.setLayoutManager(new GridLayoutManager(this,4,RecyclerView.VERTICAL,false));
        mRvSearchContent.addItemDecoration(new RecyclerGridLayoutItemDecoration(4,20,true));
        mRvSearchContent.setAdapter(mSearchBooksAdapter);
    }


    @OnClick({R.id.tv_return, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_return:
                finish();
                break;
            case R.id.iv_search:
                String keyword = mEtSerch.getText().toString();
                if (TextUtils.isEmpty(keyword)) {
                    Toast.makeText(this, "请输入搜索内容", Toast.LENGTH_LONG).show();
                } else {
                    searchBooks(keyword);
                }
                break;
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "获取数据有异常!", Toast.LENGTH_SHORT).show();
        error.printStackTrace();
        mTvTip.setVisibility(View.INVISIBLE);
    }

    protected class SearchBooksAdapter extends RecyclerView.Adapter implements View.OnClickListener {


        private List<Books_info.DataBean> mBooksList;

        public void setBooksData(List<Books_info.DataBean> books_list) {
            mBooksList = books_list;
            notifyDataSetChanged();
        }

        public SearchBooksAdapter() {
            mBooksList = null;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_book_item, parent, false);
            return new ViewHolder(root);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            SearchBooksAdapter.ViewHolder viewHolder= (SearchBooksAdapter.ViewHolder)holder;
            Books_info.DataBean dataBean=mBooksList.get(position);
            viewHolder.mTitle.setText(dataBean.getB_name());
            ImageLoader.getInstance().displayImage(NetReqUtils.BASE_URL+dataBean.getB_cover(),viewHolder.mIcon);
            viewHolder.mSearchBookItem.setTag(dataBean);
            viewHolder.mSearchBookItem.setOnClickListener(this);
        }

        @Override
        public int getItemCount() {
            return mBooksList == null ? 0 : mBooksList.size();
        }

        @Override
        public void onClick(View view) {
            Books_info.DataBean book_info = (Books_info.DataBean) view.getTag();
            if(book_info!=null){
                Intent intent=new Intent(BookSearchActivity.this,BookActivity.class);
                intent.putExtra("book_info",book_info);
                startActivity(intent);
            }
        }


        protected class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.icon)
            ImageView mIcon;
            @BindView(R.id.title)
            TextView mTitle;
            @BindView(R.id.search_book_item)
            LinearLayout mSearchBookItem;

            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);

            }
        }
    }
}
