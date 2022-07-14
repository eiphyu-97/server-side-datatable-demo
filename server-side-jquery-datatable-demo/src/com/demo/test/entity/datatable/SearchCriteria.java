package com.demo.test.entity.datatable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {

    /**
     * Search value. To be applied to all columns which have searchable as
     * true.
     */
    private String value;


}