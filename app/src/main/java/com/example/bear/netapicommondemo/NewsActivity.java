package com.example.bear.netapicommondemo;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.bear.netapicommondemo.adapter.FragmentAdapter;
import com.example.bear.netapicommondemo.fragment.NewsListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends AppCompatActivity {
    @BindView(R.id.dl_news_main)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nv_news_navigation)
    NavigationView mNavigationView;
    @BindView(R.id.news_viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_act);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        if (mNavigationView !=null) {

            mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    menuItem.setChecked(true);
                    String title = menuItem.getTitle().toString();
                    Toast.makeText(NewsActivity.this,title,Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawers();
                    return true;
                }
            });
        }
        initViewPager();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.END);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("推荐");
        titles.add("关注");
        titles.add("热点");
        titles.add("电视剧");
        titles.add("小视频");
        titles.add("电影");
        titles.add("VIP");
        titles.add("动漫");
        titles.add("亲子教育");
        titles.add("综艺");
        titles.add("纪录片");
        titles.add("体育");
        titles.add("游戏");
        titles.add("直播");
//        for (int i = 0; i < titles.size(); i++) {
//            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
//        }
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(new NewsListFragment());
        }
        FragmentAdapter fragmentAdapter = new FragmentAdapter(
                getSupportFragmentManager(),fragments,titles);
        mViewPager.setAdapter(fragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
//        mTabLayout.setTabsFromPagerAdapter(fragmentAdapter);this is no in need
    }
}
