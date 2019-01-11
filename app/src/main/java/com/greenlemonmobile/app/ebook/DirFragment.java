package com.greenlemonmobile.app.ebook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.http.data.Books_info;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DirFragment extends Fragment {

    @BindView(R.id.rv_dir)
    RecyclerView mRvDir;
    private List<Books_info.DataBean.CatalogBean> mCatalog;
    private DirAdapter mDirAdapter;
    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCatalog = (List<Books_info.DataBean.CatalogBean>) getArguments().getSerializable("Catalog");
    }

    protected class DirAdapter extends RecyclerView.Adapter {
        private List<Books_info.DataBean.CatalogBean> mCatalogList;

        public DirAdapter(List<Books_info.DataBean.CatalogBean> catalog) {
            mCatalogList = catalog;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dir_item, parent, false);
            return new DirViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((DirViewHolder)holder).mTvDirItem.setText( mCatalogList.get(position).getCname());
        }


        @Override
        public int getItemCount() {
            return mCatalogList == null ? 0 : mCatalogList.size();
        }



        class DirViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_dir_item)
            TextView mTvDirItem;
            public DirViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }

    }

    private void bindData() {
        mDirAdapter = new DirAdapter(mCatalog);
        mRvDir.setItemAnimator(new DefaultItemAnimator());
        mRvDir.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mRvDir.setAdapter(mDirAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_dir_layout, container, false);
        mUnbinder = ButterKnife.bind(this, root);
        bindData();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
