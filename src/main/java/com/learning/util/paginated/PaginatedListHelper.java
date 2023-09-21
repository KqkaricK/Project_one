package com.learning.util.paginated;

import java.util.List;

/**
 * Утилиты для облегчения работы с PaginatedList из display tag library.
 */
public class PaginatedListHelper {

    private PaginatedListHelper() {
        throw new IllegalStateException();
    }

    public static SimplePaginatedList getPaginatedList(final List list, final int total, SimplePaginatedForm form) {
        if (form == null || list == null)
            return getEmptyList();

        form.setRowCount(total);

        SimplePaginatedList res = new SimplePaginatedList();

        res.setList(list);
        res.setFullListSize(total);

        if (total <= 0 || list.size() != total) {
            res.setObjectsPerPage(form.getPageSize());
            res.setPageNumber(form.getPage());
        } else {
            res.setObjectsPerPage(total);
            res.setPageNumber(1);
        }

        res.setSortCriterion(form.getSort());
        res.setSortDirection(form.isAsc());

        return res;
    }

    public static SimplePaginatedList getPaginatedList(final List list, final Number total, final SimplePaginatedForm form) {
        return getPaginatedList(list, total != null ? total.intValue() : 0, form);
    }

    public static SimplePaginatedList getPaginatedList(final List list, final SimplePaginatedForm form) {
        if (list == null)
            return getEmptyList();

        return getPaginatedList(list, list.size(), form);
    }

    public static SimplePaginatedList createNewWithList(final SimplePaginatedList fromPaginatedList, final List newList) {
        if (fromPaginatedList == null)
            return getEmptyList();

        SimplePaginatedList result = new SimplePaginatedList();

        result.setFullListSize(fromPaginatedList.getFullListSize());
        result.setList(newList);
        result.setObjectsPerPage(fromPaginatedList.getObjectsPerPage());
        result.setPageNumber(fromPaginatedList.getPageNumber());
        result.setSearchId(fromPaginatedList.getSearchId());
        result.setSortCriterion(fromPaginatedList.getSortCriterion());
        result.setSortDirection(fromPaginatedList.getSortDirection());

        return result;
    }

    static final EmptyPaginatedList EMPTY_PAGINATED_LIST = new EmptyPaginatedList();

    public static SimplePaginatedList getEmptyList() {
        return EMPTY_PAGINATED_LIST;
    }
}
