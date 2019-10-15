package com.belenot.eatfood.repository.support;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class OffsetPageable implements Pageable {

    private long offset;
    private int size, page;
    private Sort sort;

    public OffsetPageable() {
        offset = 0;
        size = 0;
        page = 1;
        sort = Sort.unsorted();
    }

    public OffsetPageable(long offset, int size, int page) {
        this.offset = offset;
        this.size = size;
        this.page = page;
        sort = Sort.unsorted();
    }

    public OffsetPageable(long offset, int size, int page, Sort sort) {
        this(offset, size, page);
        this.sort = sort;
    }

    @Override
    @Deprecated(since = "Not implemented")
    public Pageable first() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getOffset() {
        return (page-1)*size + offset;
    }

    @Override
    public int getPageNumber() {
        return 1;
    }

    @Override
    public int getPageSize() {
        return size;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    @Deprecated(since = "Not implemented")
    public boolean hasPrevious() {
        return false;
    }

    @Override
    @Deprecated(since = "Not implemented")
    public Pageable next() {
        return null;
    }

    @Override
    @Deprecated(since = "Not implemented")
    public Pageable previousOrFirst() {        
        return null;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

}