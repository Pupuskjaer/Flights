package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
/**
 * Интерфейс, принимающий фильтры(лямбды, фильтрующие список полетов)*/
@FunctionalInterface
public interface Filter {
    boolean test(Flight flight);

}
