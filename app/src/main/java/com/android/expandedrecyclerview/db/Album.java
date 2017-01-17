package com.android.expandedrecyclerview.db;

import org.litepal.crud.DataSupport;

/**
 * Created by lxm on 17/1/17.
 */

public class Album extends DataSupport{
    private int id;
    private int categoryId;
    private String name;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
