package com.entity;


import java.io.Serializable;

/**
 * Created by lenovo on 2015/10/14.
 */
public class BaseEntity   implements Serializable{
    private   Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
