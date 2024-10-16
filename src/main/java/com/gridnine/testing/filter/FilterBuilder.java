package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * Класс прденазначен для построения цепочки фильтров, которые добавляются в лист фильтров
 * */
public class FilterBuilder {
    static List<Filter> filterList = new ArrayList<>();



    /**
     * Метод билдера, который добавляет лямбду фильтрации в список, тем самым формируя цепочку фильтров
     * @param filter лямбда фильтрации
     *
     */
    public FilterBuilder newFilter(Filter filter) {
        filterList.add(filter);
        return this;
    }

    /**
     * Формирует и возвращает комбинацию всех фильтров списка
     */
    public Filter buildFilters(){
        return flight -> filterList.stream().allMatch(filter -> filter.test(flight));
    }







}
