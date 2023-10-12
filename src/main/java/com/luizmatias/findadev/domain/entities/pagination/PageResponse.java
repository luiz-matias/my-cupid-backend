package com.luizmatias.findadev.domain.entities.pagination;

import java.util.List;

public class PageResponse<T> {

    int page;
    long count;
    long totalPages;
    long totalCount;
    List<T> content;

    public PageResponse(int page, long count, long totalPages, long totalCount, List<T> content) {
        this.page = page;
        this.count = count;
        this.totalPages = totalPages;
        this.totalCount = totalCount;
        this.content = content;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
