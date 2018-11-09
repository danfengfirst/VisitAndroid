package com.visitandroid;

/**
 * Created by Danfeng on 2018/11/8.
 */

public class Bean {
    int id;
    String name;
    String[] sub;

    public Bean(int id, String name, String[] sub) {
        this.id = id;
        this.name = name;
        this.sub = sub;
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

    public String[] getSub() {
        return sub;
    }

    public void setSub(String[] sub) {
        this.sub = sub;
    }
}
