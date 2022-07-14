package com.demo.test.paginator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.test.entity.datatable.PaginationCriteria;
import com.demo.test.entity.datatable.TablePage;
import com.demo.test.exception.TableDataException;
import com.demo.test.service.TableDataService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SimplePaginator implements TablePaginator {
	
	
    private TableDataService dataService;

    @Autowired
    public SimplePaginator(TableDataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public TablePage getPage(PaginationCriteria paginationCriteria) {
        TablePage page = new TablePage();
        try {
            page = generatePage(paginationCriteria);
        } catch (TableDataException tde) {
            log.error("Error generating table page.", tde);
            page.setError("Error generating table page.");
        }
        return page;
    }

    protected TablePage generatePage(PaginationCriteria paginationCriteria) throws TableDataException {
        TablePage page = new TablePage();
        System.out.println("Draw : "+paginationCriteria.getDraw());
        paginationCriteria.getOrder().forEach(o -> System.out.println("column : "+o.getColumn()+", dir : "+o.getDir()));
       
        page.setDraw(paginationCriteria.getDraw());
        log.debug("Draw set...");
       
        System.out.println("Total count : "+dataService.countTotalEntries());
        page.setRecordsTotal(dataService.countTotalEntries());
        log.debug("RecordsTotal set...");

        page.setRecordsFiltered(dataService.countFilteredEntries(paginationCriteria));
        log.debug("RecordsFiltered set...");

        page.setData(dataService.getPageEntries(paginationCriteria));
        log.debug("Data set...");

        return page;
    }
}
