package com.greenlemonmobile.app.ebook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.greenlemonmobile.app.ebook.adapter.BookinfosGridAdapter;
import com.greenlemonmobile.app.ebook.entity.LocalBook;
import com.greenlemonmobile.app.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BookSelfFragment extends Fragment implements AdapterView.OnItemClickListener {

    @BindView(R.id.gv_book_self_content)
    GridView mGvBookSelfContent;
    private Unbinder mUnbinder;
    private BookinfosGridAdapter mAdapter;
    private ArrayList<LocalBook> mLocalBooks=new ArrayList<>();
    private LocalBook.OrderColumn mOrderColumn = LocalBook.OrderColumn.BY_LAST_ACCESS_TIME;
    private LocalBook.OrderType mOrderType = LocalBook.OrderType.DESC;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalBook.getLocalBookList(getContext(), mOrderColumn, mOrderType, mLocalBooks);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_book_self, container, false);
        mUnbinder=ButterKnife.bind(this,root);
        bindData();

        return root;
    }

    private void bindData() {
        BookinfosGridAdapter adapter;
        adapter = new BookinfosGridAdapter(getContext(), mLocalBooks);
        adapter.setDeleteMode(false);
        mAdapter = adapter;
        mGvBookSelfContent.setAdapter(mAdapter);
        mGvBookSelfContent.setOnItemClickListener(this);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mLocalBooks != null && mLocalBooks.size() > position) {
            LocalBook book = mLocalBooks.get(position);
            FileUtil.openFile(getContext(),new File(book.file),-1);

        }
    }
}
