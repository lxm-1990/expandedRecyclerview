package com.android.expandedrecyclerview.db;

import org.litepal.crud.DataSupport;

/**
 * Created by lxm on 17/1/17.
 */

public class Resources extends DataSupport{
    private int id;
    private int albumId;
    private String name;

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
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
