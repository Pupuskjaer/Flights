package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightFilterBuilderTest {

    private List<Flight> flights;
    private FlightFilterBuilder flightFilterBuilder;

    @BeforeEach
    public void setup(){
        flights = new ArrayList<>();
        // Populate flights with test data
        // Example: flights.add(new Flight(...));
        flightFilterBuilder = new FlightFilterBuilder(flights);
    }

    @Test
    public void testAddFilter() {
        Filter durationFilter = flight -> {
            LocalDateTime dep = flight.getSegments().get(0).getDepartureDate();
            LocalDateTime arr = flight.getSegments().get(flight.getSegments().size() - 1).getArrivalDate();
            return Duration.between(dep, arr).toMinutes() > 60; // Example condition
        };

        flightFilterBuilder.addFilter(durationFilter);
        List<Flight> filteredFlights = flightFilterBuilder.build();

        // Assert that the filtered flights meet the expected criteria
        for (Flight flight : filteredFlights) {
            LocalDateTime dep = flight.getSegments().get(0).getDepartureDate();
            LocalDateTime arr = flight.getSegments().get(flight.getSegments().size() - 1).getArrivalDate();
            assertTrue(Duration.between(dep, arr).toMinutes() > 60);
        }
    }

    @Test
    public void testBuildFilters() {
        Filter filter1 = flight -> {
            LocalDateTime dep = flight.getSegments().get(0).getDepartureDate();
            return dep.isAfter(LocalDateTime.now()); // Future flights only
        };

        Filter filter2 = flight -> {
            LocalDateTime arr = flight.getSegments().get(flight.getSegments().size() - 1).getArrivalDate();
            return arr.isBefore(LocalDateTime.now().plusDays(1)); // Arriving within a day
        };

        flightFilterBuilder.addFilter(filter1);
        flightFilterBuilder.addFilter(filter2);

        List<Flight> filteredFlights = flightFilterBuilder.build();

        // Assert that all filtered flights meet both criteria
        for (Flight flight : filteredFlights) {
            LocalDateTime dep = flight.getSegments().get(0).getDepartureDate();
            LocalDateTime arr = flight.getSegments().get(flight.getSegments().size() - 1).getArrivalDate();
            assertTrue(dep.isAfter(LocalDateTime.now()));
            assertTrue(arr.isBefore(LocalDateTime.now().plusDays(1)));
        }
    }
}
