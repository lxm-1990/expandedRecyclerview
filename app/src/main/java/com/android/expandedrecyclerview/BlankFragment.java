package com.android.expandedrecyclerview;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.expandedrecyclerview.adapter.CategoryAdapter;
import com.android.expandedrecyclerview.db.Album;
import com.android.expandedrecyclerview.db.Resources;
import com.h6ah4i.android.widget.advrecyclerview.decoration.ItemShadowDecorator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.SimpleListDividerDecorator;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class BlankFragment extends Fragment implements RecyclerViewExpandableItemManager.OnGroupCollapseListener
        ,RecyclerViewExpandableItemManager.OnGroupExpandListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String SAVED_STATE_EXPANDABLE_ITEM_MANAGER = "RecyclerViewExpandableItemManager";
    private static final String CATEGORY = "category";
    private int category;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CategoryAdapter myItemAdapter;
    private RecyclerView.Adapter mWrappedAdapter;
    private RecyclerViewExpandableItemManager mRecyclerViewExpandableItemManager;
    private List<Pair<Album,List<Resources>>> mdata = new ArrayList<>();

    public BlankFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(int category) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putInt(CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getInt(CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //设置LayoutManager
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        //设置数据
        mRecyclerViewExpandableItemManager = new RecyclerViewExpandableItemManager(null);
        mRecyclerViewExpandableItemManager.setOnGroupCollapseListener(this);
        mRecyclerViewExpandableItemManager.setOnGroupExpandListener(this);
        myItemAdapter = new CategoryAdapter(mdata);
        mWrappedAdapter = mRecyclerViewExpandableItemManager.createWrappedAdapter(myItemAdapter);
        mRecyclerViewExpandableItemManager.attachRecyclerView(recyclerView);
        recyclerView.setAdapter(mWrappedAdapter);

        //设置分割线
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            recyclerView.addItemDecoration(new ItemShadowDecorator((NinePatchDrawable) ContextCompat.getDrawable(getContext(), R.drawable.material_shadow_z1)));
        }
        recyclerView.addItemDecoration(new SimpleListDividerDecorator(ContextCompat.getDrawable(getContext(), R.drawable.list_divider_h), true));
        refreshData();
    }

    @Override
    public void onDestroyView() {

        if (mRecyclerViewExpandableItemManager != null) {
            mRecyclerViewExpandableItemManager.release();
            mRecyclerViewExpandableItemManager = null;
        }

        if (recyclerView != null) {
            recyclerView.setAdapter(null);
            recyclerView = null;
        }
        if (mWrappedAdapter != null) {
            WrapperAdapterUtils.releaseAll(mWrappedAdapter);
            mWrappedAdapter = null;
        }
        if(myItemAdapter != null) {
            WrapperAdapterUtils.releaseAll(myItemAdapter);
            myItemAdapter = null;
        }
        mLayoutManager = null;
        super.onDestroyView();
    }
    @Override
    public void onGroupCollapse(int groupPosition, boolean fromUser) {
    }

    @Override
    public void onGroupExpand(int groupPosition, boolean fromUser) {
    }

    //从数据库取出数据
    void refreshData(){
        mdata.clear();
        List<Album> albumList = DataSupport.where("categoryId=?",String.valueOf(category)).find(Album.class);
        for (int i = 0; i < albumList.size(); ++i ){
            Album album = albumList.get(i);
            List<Resources> resourcesList = DataSupport.where("albumId = ?",String.valueOf(album.getId())).find(Resources.class);
            mdata.add(new Pair<Album, List<Resources>>(album,resourcesList));
        }
        myItemAdapter.notifyDataSetChanged();
    }
}
