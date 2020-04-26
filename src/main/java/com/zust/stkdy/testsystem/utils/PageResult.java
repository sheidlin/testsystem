package com.zust.stkdy.testsystem.utils;

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable {
    private int totalPage;
    private int totalCount;
    private int currentPage;
    private int pageSize;
    private List<?> list;

    public PageResult() {
    }

    public PageResult(int totalPage, int totalCount, int currentPage, int pageSize) {
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", list=" + list +
                '}';
    }
}
