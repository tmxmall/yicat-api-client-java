package com.yicat.client.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PagingQueryResults<E> implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<E> list = new ArrayList();
    private int totalRecords = 0;
    private int pageSize;
    private int currentPage;
    private int totalPage;

    public PagingQueryResults() {
        this.pageSize = 0;
        this.currentPage = 0;
        this.totalPage = 0;
    }

    public PagingQueryResults(int pageSize, int currentPage) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalPage = 0;
    }

    public List<E> getList() {
        return this.list;
    }

    public int getTotalRecords() {
        return this.totalRecords;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setList(final List<E> list) {
        this.list = list;
    }

    public void setTotalRecords(final int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(final int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalPage(final int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PagingQueryResults)) {
            return false;
        } else {
            PagingQueryResults<?> other = (PagingQueryResults)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$list = this.getList();
                Object other$list = other.getList();
                if (this$list == null) {
                    if (other$list != null) {
                        return false;
                    }
                } else if (!this$list.equals(other$list)) {
                    return false;
                }

                if (this.getTotalRecords() != other.getTotalRecords()) {
                    return false;
                } else if (this.getPageSize() != other.getPageSize()) {
                    return false;
                } else if (this.getCurrentPage() != other.getCurrentPage()) {
                    return false;
                } else if (this.getTotalPage() != other.getTotalPage()) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PagingQueryResults;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $list = this.getList();
        result = result * 59 + ($list == null ? 43 : $list.hashCode());
        result = result * 59 + this.getTotalRecords();
        result = result * 59 + this.getPageSize();
        result = result * 59 + this.getCurrentPage();
        result = result * 59 + this.getTotalPage();
        return result;
    }

    @Override
    public String toString() {
        return "PagingQueryResults(list=" + this.getList() + ", totalRecords=" + this.getTotalRecords() + ", pageSize=" + this.getPageSize() + ", currentPage=" + this.getCurrentPage() + ", totalPage=" + this.getTotalPage() + ")";
    }
}
