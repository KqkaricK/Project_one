package com.learning.util.paginated;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Базовый класс для форм с поддержкой разбивкой по страницам.
 *
 * @see SimplePaginatedList
 * @see <a href="http://www.displaytag.org">Display tag library reference</a>
 */
public class SimplePaginatedForm implements Serializable {
    protected static final long serialVersionUID = 1L;

    public static final String ASC = "asc";
    public static final String DESC = "desc";

    protected static int defaultPageSize = 30;
    protected static int maxPageSize = 1000;

    private String sort;
    private String dir = ASC;
    private int page = 1;
    private int pageSize = defaultPageSize;
    private int rowCount;


    public SimplePaginatedForm() {
    }

    public SimplePaginatedForm(String defaultSort) {
        sort = defaultSort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("sort", sort)
                .append("dir", dir)
                .append("page", page)
                .append("pageSize", pageSize)
                .append("defaultPageSize", defaultPageSize)
                .append("rowCount", rowCount)
                .toString();
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public boolean isAsc() {
        return ASC.equalsIgnoreCase(dir);
    }

    public boolean isDesc() {
        return DESC.equalsIgnoreCase(dir);
    }

    public void setDesc() {
        dir = DESC;
    }

    public void setAsc() {
        dir = ASC;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        this.validPage();
    }

    public void validPage() {
        if (rowCount == 0) return;

        float fpage = (float) rowCount / (float) pageSize;
        if (Math.ceil(fpage) < this.page) this.page = 1;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortDir() {
        if (StringUtils.isNotBlank(sort))
            return sort + ":" + dir;
        return null;
    }

    public void setSortDir(String sortDir) {
        if (StringUtils.isNotBlank(sortDir)) {
            this.sort = StringUtils.substringBeforeLast(sortDir, ":");
            this.dir = StringUtils.substringAfterLast(sortDir, ":");
        } else {
            this.sort = null;
            this.dir = ASC;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getFirstResult() {
        return (page - 1) * pageSize;
    }

    public static int getDefaultPageSize() {
        return defaultPageSize;
    }

    public static void setDefaultPageSize(int defaultPageSize) {
        SimplePaginatedForm.defaultPageSize = defaultPageSize;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
        this.validPage();
    }

    public void fixPageNumber(int total) {
        if (getPage() < 1) {
            setPage(1);
        }
        if (getPageSize() > 0 && getFirstResult() > total) {
            int page = (int) Math.floor(total / getPageSize()) + 1;
            if (total % getPageSize() == 0)
                page -= 1;
            setPage(page);
        }
    }

    public void fixPageSize() {
        if (pageSize < 0 || pageSize > maxPageSize)
            pageSize = maxPageSize;
    }

    public List getPageList(){
        int displayCount = 5;
        int halfDisplayCount = (int) Math.ceil( (float) displayCount/2 );
        List pages = new ArrayList( displayCount );

        if ( this.page <= halfDisplayCount) {
            for (int i = 1; i<=displayCount; i++){
                if (i <= getLastPage() )
                    pages.add(Integer.valueOf(i));
            }
            return pages;
        }

        for ( int i = this.page - halfDisplayCount + 1; i < this.page + halfDisplayCount; i++){
            if (i <= getLastPage() )
                pages.add(Integer.valueOf(i));
        }
        return pages;
    }

    public int getLastPage(){
        return (int) Math.ceil( (float) rowCount/pageSize );
    }
}
