package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

@FunctionalInterface
public interface Filter {
    boolean test(Flight flight);

}
