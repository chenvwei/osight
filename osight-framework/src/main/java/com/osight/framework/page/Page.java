/*
 * Created on 2012-12-26
 */
package com.osight.framework.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenw
 * @version $Id$
 */
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public transient static final int DEFAULT_COUNT = 10;

    List<T> objects;
    int start;
    int totalCount = 0;
    int pageCount = 0;
    boolean hasNext;

    public Page(List<T> list, int start, int totalCount, int pageCount, boolean hasNext) {
        objects = new ArrayList<T>(list);
        this.start = start;
        this.totalCount = totalCount;
        this.pageCount = pageCount;
        this.hasNext = hasNext;
    }

    public List<T> getList() {
        return objects;
    }

    public int getCurPage() {
        if (0 == pageCount)
            return 0;
        return start / pageCount + 1;
    }

    public int getTotalPage() {
        if (pageCount != 0)
            if (totalCount % pageCount == 0)
                return totalCount / pageCount;
            else
                return totalCount / pageCount + 1;
        else
            return 0;
    }

    public int getPageCount() {
        return pageCount;
    }

    public boolean isNextPageAvailable() {
        return hasNext;
    }

    public boolean isPreviousPageAvailable() {
        return start > 0;
    }

    public int getStartOfNextPage() {
        return start + pageCount;
    }

    public int getStartOfPreviousPage() {
        return Math.max(start - pageCount, 0);
    }

    public int getStartOfLastPage() {
        return pageCount * (getTotalPage() - 1);
    }

    public int getSize() {
        return objects.size();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
