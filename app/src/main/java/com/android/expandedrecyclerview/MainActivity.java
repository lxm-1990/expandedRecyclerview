package com.android.expandedrecyclerview;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.expandedrecyclerview.adapter.PageAdapter;
import com.android.expandedrecyclerview.db.Album;
import com.android.expandedrecyclerview.db.Resources;

import org.litepal.crud.DataSupport;

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

        DataSupport.deleteAll(Album.class);
        DataSupport.deleteAll(Resources.class);
        //往数据库存储一些数据
        createAlbum(0,"恐怖小说");
        createAlbum(0,"都市小说");
        createAlbum(0,"言情小说");

        createAlbum(1,"经典音乐");
        createAlbum(1,"快歌");
        createAlbum(1,"慢歌");

        createAlbum(2,"恐怖");
        createAlbum(2,"喜剧");
        createAlbum(2,"剧情");

        createAlbum(3,"电视剧");
        createAlbum(3,"网剧");

        createAlbum(4,"恐怖杂志");
        createAlbum(4,"心灵鸡汤");

        createAlbum(5,"真人秀");
        createAlbum(5,"唱歌比赛");


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

    private void createAlbum(int categoryId,String name) {
        Album album = new Album();
        album.setCategoryId(categoryId);
        album.setName(name);
        album.save();
        for (int i = 0 ; i < 10 ;++i) {
            createResources(album.getId(),name+String.valueOf(i));
        }
    }
    private void createResources(int albumId,String name){
        Resources resources = new Resources();
        resources.setAlbumId(albumId);
        resources.setName(name);
        resources.save();
    }

}
