package com.learning.util.paginated;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Простая реализация PaginatedList
 * с поддержкой постраничной разбивки таблицы.
 */
public class SimplePaginatedList implements Serializable {
    private static final long serialVersionUID = 1L;

    private List list;
    private int pageNumber;
    private int objectsPerPage;
    private int fullListSize;
    private String sortCriterion;
    private String sortDirection;
    private String searchId;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("list size", CollectionUtils.size(getList()))
                .append("pageNumber", pageNumber)
                .append("objectsPerPage", objectsPerPage)
                .append("fullListSize", fullListSize)
                .append("sortCriterion", sortCriterion)
                .append("sortDirection", sortDirection)
                .append("searchId", searchId)
                .toString();
    }

    /**
     * Returns the size of the full list
     *
     * @return the size of the full list
     */

    public int getFullListSize() {
        return fullListSize;
    }

    void setFullListSize(int fullListSize) {
        this.fullListSize = fullListSize;
    }

    /**
     * Returns the current partial list
     *
     * @return the current partial list
     */
    public List getList() {
        return list;
    }

    void setList(List list) {
        this.list = list;
    }

    /**
     * Returns the number of objects per page. Unless this page is the last one the partial list should thus have a size
     * equal to the result of this method
     *
     * @return the number of objects per page
     */
    public int getObjectsPerPage() {
        return objectsPerPage;
    }

    void setObjectsPerPage(int objectsPerPage) {
        this.objectsPerPage = objectsPerPage;
    }

    /**
     * Returns the page number of the partial list (starts from 1)
     *
     * @return the page number of the partial list
     */
    public int getPageNumber() {
        return pageNumber;
    }

    void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Returns an ID for the search used to get the list. It may be null. Such an ID can be necessary if the full list
     * is cached, in a way or another (in the session, in the business tier, or anywhere else), to be able to retrieve
     * the full list from the cache
     *
     * @return the search ID
     */
    public String getSearchId() {
        return searchId;
    }

    void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    /**
     * Returns the sort criterion used to externally sort the full list
     *
     * @return the sort criterion used to externally sort the full list
     */
    public String getSortCriterion() {
        return sortCriterion;
    }

    void setSortCriterion(String sortCriterion) {
        this.sortCriterion = sortCriterion;
    }

    /**
     * Returns the sort direction used to externally sort the full list
     *
     * @return the sort direction used to externally sort the full list
     */
    public String getSortDirection() {
        return sortDirection;
    }

    void setSortDirection(boolean ascending) {
        this.sortDirection = ascending ? SimplePaginatedForm.ASC : SimplePaginatedForm.DESC;
    }

    void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }
}
