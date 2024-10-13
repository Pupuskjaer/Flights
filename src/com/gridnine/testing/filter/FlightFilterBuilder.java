package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class FlightFilterBuilder {

    public static List<Flight> filterFlights(List<Flight> flights, FlightFilter filter) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (filter.test(flight)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    public static List<Flight> filterFlightsByDuration(List<Flight> flights, LocalDateTime dep,LocalDateTime arr) {
        Duration duration = Duration.between(dep.toLocalTime(), arr.toLocalTime());
        return flights.stream().filter(flight -> durEqualsDepAndArrTime(flight,duration)).toList();
    }

    private static boolean durEqualsDepAndArrTime(Flight flight, Duration duration) {
        LocalDateTime arr = flight.getSegments().get(flight.getSegments().size() - 1).getArrivalDate();
        LocalDateTime dep = flight.getSegments().get(0).getArrivalDate();
        return Duration.between(dep.toLocalTime(), arr.toLocalTime()).toMinutes() == duration.toMinutes();
    }
}
