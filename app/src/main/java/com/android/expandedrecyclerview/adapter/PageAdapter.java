package com.android.expandedrecyclerview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.android.expandedrecyclerview.BlankFragment;

import java.util.List;

/**
 * Created by lxm on 17/1/17.
 */

public class PageAdapter extends FragmentPagerAdapter{
    private List<BlankFragment> fragments;
    private String[] titles = {"小说","音乐","电影","电视剧","杂志","综艺"};
    public PageAdapter(FragmentManager fm,List<BlankFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
