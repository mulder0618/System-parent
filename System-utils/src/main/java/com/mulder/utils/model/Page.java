package com.mulder.utils.model;

/**
 * Created by mulder on 15/5/14.
 */
public class Page {

    private int pageIndex;          //当前页码
    private int pageSize;           //每页分页数

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
