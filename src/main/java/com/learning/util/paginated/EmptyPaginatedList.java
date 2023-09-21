package com.learning.util.paginated;


import java.util.Collections;
import java.util.List;

/**
 * Created by aalexeev on 19.04.16.
 */
public class EmptyPaginatedList extends SimplePaginatedList{

    public List getList() {
        return Collections.emptyList();
    }

    public int getPageNumber() {
        return 1;
    }

    public int getObjectsPerPage() {
        return SimplePaginatedForm.getDefaultPageSize();
    }

    public int getFullListSize() {
        return 0;
    }

    public String getSortCriterion() {
        return null;
    }

    public String getSortDirection() {
        return null;
    }

    public String getSearchId() {
        return null;
    }
}
