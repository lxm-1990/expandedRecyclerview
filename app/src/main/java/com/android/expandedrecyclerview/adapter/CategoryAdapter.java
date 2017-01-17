package com.android.expandedrecyclerview.adapter;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.expandedrecyclerview.R;
import com.android.expandedrecyclerview.db.Album;
import com.android.expandedrecyclerview.db.Resources;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import java.util.List;

/**
 * Created by lxm on 17/1/17.
 */

public class CategoryAdapter extends AbstractExpandableItemAdapter<CategoryAdapter.MyGroupViewHolder,CategoryAdapter.MyChildViewHolder>{

    private List<Pair<Album,List<Resources>>> mdata;

    public CategoryAdapter(List<Pair<Album, List<Resources>>> mdata) {
        this.mdata = mdata;
        setHasStableIds(true);
    }

    class MyGroupViewHolder extends AbstractExpandableItemViewHolder{

        TextView textView;
        public MyGroupViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.album_name);
        }
    }

    class MyChildViewHolder extends AbstractExpandableItemViewHolder{
        TextView textView;
        public MyChildViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.resource_name);
        }
    }

    @Override
    public int getGroupCount() {
        return mdata.size();
    }

    @Override
    public int getChildCount(int groupPosition) {
        return mdata.get(groupPosition).second.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mdata.get(groupPosition).first.getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mdata.get(groupPosition).second.get(childPosition).getId();
    }

    @Override
    public MyGroupViewHolder onCreateGroupViewHolder(ViewGroup parent,int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.group_item,parent,false);
        return new MyGroupViewHolder(view);
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup parent,int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.child_item,parent,false);
        return new MyChildViewHolder(view);
    }

    @Override
    public void onBindGroupViewHolder(MyGroupViewHolder holder, int groupPosition,int viewType) {
        holder.textView.setText(mdata.get(groupPosition).first.getName());
    }

    @Override
    public void onBindChildViewHolder(MyChildViewHolder holder, int groupPosition, int childPosition,int viewType) {
        holder.textView.setText(mdata.get(groupPosition).second.get(childPosition).getName());
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(MyGroupViewHolder holder, int groupPosition, int x, int y, boolean expand) {
        return true;
    }
}
