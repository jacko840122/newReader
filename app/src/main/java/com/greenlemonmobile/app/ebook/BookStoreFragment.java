package com.greenlemonmobile.app.ebook;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.common.http.NetReqUtils;
import com.common.http.data.Categorymenu;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.android.volley.Request.Method.POST;
import static com.common.http.NetReqUtils.ACTION_CATEGORY;

public class BookStoreFragment extends Fragment implements Response.ErrorListener {

    @BindView(R.id.lv_tab)
    ListView mLvTab;
    @BindView(R.id.vp_subtype_content)
    ViewPager mVpContent;
    private Unbinder mUnbinder;
    private Response.Listener<Categorymenu> mCatListener = new Response.Listener<Categorymenu>() {
        @Override
        public void onResponse(Categorymenu response) {
            mCategorymenu = response;
            if (mCategorymenu == null || mCategorymenu.getData() == null
                    || mCategorymenu.getData().isEmpty()) {
                Toast.makeText(getContext(), "获取分类列表有异常!", Toast.LENGTH_SHORT).show();
            } else {
                mDataBeanList = mCategorymenu.getData();
                bindDatas();
            }
        }
    };
    private Categorymenu mCategorymenu;
    private List<Categorymenu.DataBean> mDataBeanList;
    private ArrayList<Fragment> mFragmentlist;
    private TabListAdapter mTablistAdapter;


    public void bindDatas() {
        if (mDataBeanList == null || mDataBeanList.isEmpty()) return;
        mTablistAdapter = new TabListAdapter(getContext(),mDataBeanList);
        mLvTab.setAdapter(mTablistAdapter);
        mLvTab.setDividerHeight(0);
        // listview点击事件
        mLvTab.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.e("tag", Integer.toString(position));
                // TODO Auto-generated method stub
                mTablistAdapter.setSelectedPosition(position);
                mTablistAdapter.notifyDataSetInvalidated();
                mVpContent.setCurrentItem(position);
            }
        });

        mFragmentlist = new ArrayList<Fragment>();
        for (Categorymenu.DataBean dataBean : mDataBeanList) {
            Fragment fragment = new BookStoreSubtypeFragment();
            Bundle args =new Bundle();
            args.putInt("sort_id", Integer.parseInt(dataBean.getId()));
            fragment.setArguments(args);
            mFragmentlist.add(fragment);
        }
        mVpContent.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragmentlist.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentlist == null ? 0 : mFragmentlist.size();
            }
        });
        mVpContent.setOnPageChangeListener(new MyOnPageChangeListener());
        mTablistAdapter.setSelectedPosition(0);
        mVpContent.setCurrentItem(0);
    }


    private void getCategoryMenu() {
        NetReqUtils.addGsonRequest(getContext(), POST, this, null, null, ACTION_CATEGORY,
                Categorymenu.class, mCatListener, this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = LayoutInflater.from(container.getContext()).inflate(R.layout.main_book_store_fragment, container, false);
        mUnbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getCategoryMenu();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (getContext() != null) {
            Toast.makeText(getContext(), "获取数据有异常!", Toast.LENGTH_SHORT).show();
        }
        error.printStackTrace();

    }

    protected class TabListAdapter extends BaseAdapter {
        private final List<Categorymenu.DataBean> mDataList;
        private int mSelectedPosition = -1;// 选中的位置
        private Context mContext;

        public TabListAdapter(Context context,List<Categorymenu.DataBean> dataBeanList) {
            mContext=context;
            mDataList = dataBeanList;

        }

        @Override
        public int getCount() {
            return mDataList == null ? 0 : mDataList.size();
        }

        @Override
        public Object getItem(int i) {
            return mDataList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.left_tab_item, viewGroup, false);
                ViewHolder holder = new ViewHolder(view);
                view.setTag(holder);
            }
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            viewHolder.mTvTabText.setText(mDataList.get(i).getTypename());
            if(mSelectedPosition==i){
                viewHolder.mTvTabText.setTextColor(mContext.getColor(R.color.tab_selected_text_color));
                viewHolder.mTvTabText.setBackgroundColor(mContext.getColor(R.color.tab_sel_background));
            }else {
                viewHolder.mTvTabText.setTextColor(mContext.getColor(R.color.tab_unsel_textcolor));
                viewHolder.mTvTabText.setBackgroundColor(mContext.getColor(R.color.tab_unsel_background));
            }
            return view;
        }

        public void setSelectedPosition(int position) {
            mSelectedPosition = position;
            notifyDataSetInvalidated();
        }


        class ViewHolder {
            @BindView(R.id.tv_tab_text)
            TextView mTvTabText;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            mTablistAdapter.setSelectedPosition(position);
        }

        @Override
        public void onPageSelected(int position) {


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
