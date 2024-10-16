package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * */
public class FlightFilterBuilder{
    List<Flight> flightList;
    FilterBuilder filterBuilder;

    public FlightFilterBuilder(List<Flight> flightList) {
        this.flightList = flightList;
        this.filterBuilder = new FilterBuilder();
    }
    /**
     * Метод для мгновенной выдачи отфильтрованного списка(без необходимости создания объекта {@link FlightFilterBuilder})
     * @param flights список полетов
     * @param filter лямбда фильтрации
     * @return отфильтрованный список полетов
     */
    public static List<Flight> filterFlights(List<Flight> flights, Filter filter) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (filter.test(flight)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    public FlightFilterBuilder addFilter(Filter filter) {
        filterBuilder.newFilter(filter);
        return this;
    }

    public List<Flight> build() {
        Filter combinedFilters = filterBuilder.buildFilters();
        return flightList.stream().filter(combinedFilters::test).toList();
    }

    public static Filter filterFlightsByDuration(LocalDateTime dep, LocalDateTime arr) {
        Duration duration = Duration.between(dep.toLocalTime(), arr.toLocalTime());
        return flight -> durEqualsDepAndArrTime(flight,duration);
    }

    private static boolean durEqualsDepAndArrTime(Flight flight, Duration duration) {
        LocalDateTime arr = flight.getSegments().get(flight.getSegments().size() - 1).getArrivalDate();
        LocalDateTime dep = flight.getSegments().get(0).getDepartureDate();
        return Duration.between(dep.toLocalTime(), arr.toLocalTime()).toMinutes() == duration.toMinutes();
    }
}
