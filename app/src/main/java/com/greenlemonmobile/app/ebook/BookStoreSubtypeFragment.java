package com.greenlemonmobile.app.ebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.android.volley.Request.Method.POST;
import static com.common.http.NetReqUtils.ACTION_BOOKS_LIST;

public class BookStoreSubtypeFragment extends Fragment implements Response.ErrorListener {

    @BindView(R.id.rv_subtype_books)
    RecyclerView mRvBooksContent;
    private int mSortId = -1;
    private Books_info mBooks_info;
    private List<Books_info.DataBean> mBooks_list;
    private BooksStoreSubtypeAdapter mBooksStoreSubtypeAdapter;
    private Unbinder mUnbinder;
    private Response.Listener<Books_info> mBooksListener = new Response.Listener<Books_info>() {
        @Override
        public void onResponse(Books_info response) {
            mBooks_info = response;
            if (mBooks_info == null || mBooks_info.getData() == null ||
                    mBooks_info.getData().isEmpty()) {

            } else {
                mBooks_list = mBooks_info.getData();
                mBooksStoreSubtypeAdapter.setBooksData(mBooks_list);
            }
        }
    };


    private void getSortBooks() {
        if (mSortId > 0) {
            HashMap<String, Object> extraParams = new HashMap<>();
            extraParams.put("start_option", 0);
            extraParams.put("limitnum", 1000);
            extraParams.put("typeid", mSortId);
            NetReqUtils.addGsonRequest(getContext(), POST, this, null, extraParams, ACTION_BOOKS_LIST,
                    Books_info.class, mBooksListener, this);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSortId = getArguments().getInt("sort_id");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = LayoutInflater.from(container.getContext()).inflate(R.layout.main_book_store_subtype, container, false);
        mUnbinder = ButterKnife.bind(this, root);

        mBooksStoreSubtypeAdapter = new BooksStoreSubtypeAdapter();
        mRvBooksContent.setLayoutManager(new GridLayoutManager(getContext(), 3, RecyclerView.VERTICAL, false));
        mRvBooksContent.addItemDecoration(new RecyclerGridLayoutItemDecoration(3,50,true));
        mRvBooksContent.setAdapter(mBooksStoreSubtypeAdapter);
        getSortBooks();
        return root;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (getContext() != null) {
            Toast.makeText(getContext(), "获取数据有异常!", Toast.LENGTH_SHORT).show();
        }
        error.printStackTrace();
    }

    protected class BooksStoreSubtypeAdapter extends RecyclerView.Adapter implements View.OnClickListener {


        private List<Books_info.DataBean> mBooksList;

        public void setBooksData(List<Books_info.DataBean> books_list) {
            mBooksList = books_list;
            notifyDataSetChanged();
        }

        public BooksStoreSubtypeAdapter() {
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
            ViewHolder viewHolder = (ViewHolder) holder;
            Books_info.DataBean dataBean = mBooksList.get(position);
            viewHolder.mTitle.setText(dataBean.getB_name());
            ImageLoader.getInstance().displayImage(NetReqUtils.BASE_URL + dataBean.getB_cover(), viewHolder.mIcon);
            viewHolder.mSubtypeBookItem.setTag(dataBean);
            viewHolder.mSubtypeBookItem.setOnClickListener(this);
        }

        @Override
        public int getItemCount() {
            return mBooksList == null ? 0 : mBooksList.size();
        }

        @Override
        public void onClick(View view) {
            Books_info.DataBean book_info = (Books_info.DataBean) view.getTag();
            if(book_info!=null){
                Intent intent=new Intent(getContext(),BookActivity.class);
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
            LinearLayout mSubtypeBookItem;

            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);

            }
        }
    }
}
