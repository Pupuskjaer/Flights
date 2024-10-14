package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterBuilder {
    static List<Filter> filterList = new ArrayList<>();

    public static List<Flight> filterFlights(List<Flight> flights, Filter filter) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (filter.test(flight)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    public FilterBuilder newFilter(Filter filter) {
        filterList.add(filter);
        return this;
    }

    public Filter buildFilters(){
        return flight -> filterList.stream().allMatch(filter -> filter.test(flight));
    }


/*

    public static Filter filterFlightsByDuration(List<Flight> flights, LocalDateTime dep, LocalDateTime arr) {
        Duration duration = Duration.between(dep.toLocalTime(), arr.toLocalTime());
        return flight -> durEqualsDepAndArrTime(flight,duration);
    }

    private static boolean durEqualsDepAndArrTime(Flight flight, Duration duration) {
        LocalDateTime arr = flight.getSegments().get(flight.getSegments().size() - 1).getArrivalDate();
        LocalDateTime dep = flight.getSegments().get(0).getArrivalDate();
        return Duration.between(dep.toLocalTime(), arr.toLocalTime()).toMinutes() == duration.toMinutes();
    }
*/

}
