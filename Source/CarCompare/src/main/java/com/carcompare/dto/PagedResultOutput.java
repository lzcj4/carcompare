package com.carcompare.dto;

import java.util.List;

/**
 * 带分页的输出参数
 * @param <T>
 */
public abstract class PagedResultOutput<T> {
    private long totalCount;
    private List<T> items;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
