package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.util.List;

public class FlightFilterBuilder{
    List<Flight> flightList;
    FilterBuilder filterBuilder;

    public FlightFilterBuilder(List<Flight> flightList) {
        this.flightList = flightList;
        this.filterBuilder = new FilterBuilder();
    }

    public FlightFilterBuilder addFilter(Filter filter) {
        filterBuilder.newFilter(filter);
        return this;
    }

    public List<Flight> build() {
        Filter combinedFilters = filterBuilder.buildFilters();
        return flightList.stream().filter(combinedFilters::test).toList();
    }
}
