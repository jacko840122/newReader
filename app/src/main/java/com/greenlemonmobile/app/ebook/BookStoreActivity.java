package com.greenlemonmobile.app.ebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.greenlemonmobile.app.ebook.books.views.FlipAnimationMesh;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookStoreActivity extends AppCompatActivity {

    @BindView(R.id.tv_return)
    ImageView mTvReturn;
    @BindView(R.id.tv_serch)
    TextView mTvSerch;
    @BindView(R.id.tab_content)
    TabLayout mTabContent;
    @BindView(R.id.vp_main_book_store_content)
    ViewPager mViewPager;
    private ArrayList<Fragment> mFragments=new ArrayList<>();
    private ArrayList<TabLayout.Tab> mTabs=new ArrayList<>();
    private FragmentPagerAdapter mPagerAdapter;

    private void  bindData(){

        Fragment fragment1=new BookSelfFragment();

        Fragment fragment2=new BookStoreFragment();
        mFragments.add(fragment1);
        mFragments.add(fragment2);

        mTabs.add(mTabContent.newTab().setText("书 架"));
        mTabs.add(mTabContent.newTab().setText("书 店"));

        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {


            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTabs.get(position).getText();
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }
        };
        mViewPager.setAdapter(mPagerAdapter);
        mTabContent.setupWithViewPager(mViewPager, true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_book_store);
        ButterKnife.bind(this);
        bindData();
    }

    @OnClick({R.id.tv_return, R.id.tv_serch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_return:
                finish();
                break;
            case R.id.tv_serch:
                Intent intent=new Intent(this,BookSearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
