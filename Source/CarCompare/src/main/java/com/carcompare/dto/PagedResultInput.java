package com.carcompare.dto;

/**
 * 带分页的输入参数
 */
public abstract class PagedResultInput {
    //页码
    private int pageIndex;
    //每页记录数
    private int pageSize;

    public PagedResultInput(){
        this.pageIndex = 1;
        this.pageSize = 10;
    }

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
