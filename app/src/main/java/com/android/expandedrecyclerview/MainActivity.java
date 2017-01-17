package com.android.expandedrecyclerview;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<BlankFragment> fragments = new ArrayList<>();
        for (int i = 0; i < 6 ; ++i) {
            fragments.add(BlankFragment.newInstance(i));
        }
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        pagerAdapter = new PageAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(pagerAdapter);

        //将tablayout和pageview建立联系
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
