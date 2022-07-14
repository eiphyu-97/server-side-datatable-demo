package com.demo.test.paginator;

import com.demo.test.entity.datatable.PaginationCriteria;
import com.demo.test.entity.datatable.TablePage;

/**
 * The main component, used to generate a {@link TablePage}} according to
 * {@link PaginationCriteria}.
 *
 * @author David Castelletti
 */
public interface TablePaginator {
    TablePage getPage(PaginationCriteria paginationCriteria);
}
