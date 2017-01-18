package com.android.expandedrecyclerview;

import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
    public DrawerLayout drawerLayout;

    private Spinner spinner;
    private EditText inputCategory;
    private EditText inputName;
    private Button btn_add;

    List<BlankFragment> fragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 6 ; ++i) {
            fragments.add(BlankFragment.newInstance(i));
        }
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        pagerAdapter = new PageAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(pagerAdapter);

        //将tablayout和pageview建立联系
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.add);

        spinner = (Spinner) findViewById(R.id.spinner);
        inputCategory = (EditText) findViewById(R.id.input_category);
        inputName = (EditText) findViewById(R.id.input_name);
        btn_add = (Button) findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputCategory.getText().length() == 0) {
                    Toast.makeText(MainActivity.this,"请输入分类",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (inputName.getText().length() == 0) {
                    Toast.makeText(MainActivity.this,"请输入名称",Toast.LENGTH_SHORT).show();
                    return;
                }
                int id = spinner.getSelectedItemPosition();
                createData(id,inputCategory.getText().toString(),inputName.getText().toString());
                drawerLayout.closeDrawers();
            }
        });

    }
    private void createData(int categoryId,String album_name,String name) {
        List<Album> albumList = DataSupport.where("name=?",album_name).find(Album.class);
        int albumId;
        if(albumList.size() == 0) {
            Album album = new Album();
            album.setCategoryId(categoryId);
            album.setName(name);
            album.save();
            albumId = album.getId();
        } else {
            albumId = albumList.get(0).getId();
        }
        Resources resources = new Resources();
        resources.setAlbumId(albumId);
        resources.setName(name);
        resources.save();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (!drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.openDrawer(GravityCompat.START);
                } else {
                    drawerLayout.closeDrawers();
                }
                break;
        }
        return true;
    }
}
