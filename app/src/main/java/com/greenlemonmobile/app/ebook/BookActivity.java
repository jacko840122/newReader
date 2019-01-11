package com.greenlemonmobile.app.ebook;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.common.http.data.Books_info;
import com.google.android.material.tabs.TabLayout;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookActivity extends AppCompatActivity implements Response.ErrorListener {

    @BindView(R.id.tv_return)
    TextView mTvReturn;
    @BindView(R.id.ll_title)
    LinearLayout mLlTitle;
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
    RelativeLayout mRvBookContainer;

    @BindView(R.id.tv_add_book)
    TextView mTvAddBook;
    @BindView(R.id.tv_read_book)
    TextView mTvReadBook;
    @BindView(R.id.ll_bottom_bar)
    LinearLayout mLlBottomBar;
    @BindView(R.id.tv_brief)
    TextView tvBrief;
    @BindView(R.id.vp_tab_content)
    ViewPager mViewPager;
    @BindView(R.id.tab_content)
    TabLayout mTabContent;
    private Books_info mBooks_info;
    private Books_info.DataBean mBook_info;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private FragmentPagerAdapter mPagerAdapter;


    private void initFrament() {

        if(mBook_info==null) return;

        Bundle bundle1=new Bundle();
        bundle1.putString("book_id",mBook_info.getId());
        Fragment fragment1=new FeelsFragment();
        fragment1.setArguments(bundle1);

        Bundle bundle2=new Bundle();
        bundle1.putSerializable("Catalog", (Serializable) mBook_info.getCatalog());
        Fragment fragment2=new DirFragment();
        fragment2.setArguments(bundle2);
        mFragments.add(fragment1);
        mFragments.add(fragment2);
        mTabContent.addTab(mTabContent.newTab());
        mTabContent.addTab(mTabContent.newTab());
        mTabContent.getTabAt(0).setText("读 后 感");
        mTabContent.getTabAt(1).setText("目 录");

        mTabContent.setupWithViewPager(mViewPager, false);
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }
        };
        mViewPager.setAdapter(mPagerAdapter);

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_main_layout);
        ButterKnife.bind(this);
        mBooks_info = (Books_info) getIntent().getSerializableExtra("book_info");
        bindBookInfo();
        initFrament();
    }

    private void bindBookInfo() {
        if (mBooks_info != null && mBooks_info.getData() != null
                && !mBooks_info.getData().isEmpty()) {
            Books_info.DataBean book_info = mBooks_info.getData().get(0);
            mBook_info=book_info;
            String cover = book_info.getB_cover();
            String author = book_info.getB_author();
            String introduction = book_info.getB_introduction();
            String name = book_info.getB_name();
            String Readnum = book_info.getReadnum();
            if (TextUtils.isEmpty(cover) || cover.length() < 5) {
                mIvCover.setImageResource(R.drawable.default_cover);
            } else {
                ImageLoader.getInstance().displayImage(cover, mIvCover);
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
            mRvBookContainer.setVisibility(View.VISIBLE);
        } else {
            //mTvBookContainer.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @OnClick({R.id.tv_return, R.id.tv_add_book, R.id.tv_read_book})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_return:
                finish();
                break;
            case R.id.tv_add_book:
                Toast.makeText(this, "已经加入书架", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_read_book:
                break;
        }
    }
}
