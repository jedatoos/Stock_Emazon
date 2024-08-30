package com.example.demo.domain.model;

import java.util.List;

public class PageResult<T> {
    private List<T> items;
    private Long totalCount;
    private int pageCount;
    private int currentPageIndex;
    private boolean isAscendingOrder;
    private boolean empty;

    public PageResult(boolean isAscendingOrder, int currentPageIndex, int pageCount, Long totalCount, List<T> items) {
        this.isAscendingOrder = isAscendingOrder;
        this.currentPageIndex = currentPageIndex;
        this.pageCount = pageCount;
        this.totalCount = totalCount;
        this.items = items;
        this.empty = items.isEmpty();
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public void setCurrentPageIndex(int currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }

    public boolean isAscendingOrder() {
        return isAscendingOrder;
    }

    public void setAscendingOrder(boolean ascendingOrder) {
        isAscendingOrder = ascendingOrder;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
