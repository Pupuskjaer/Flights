package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
@FunctionalInterface
public interface FlightFilter {
    boolean test(Flight flight);


   /* // represents the list of normal flights with given duration
    List<Flight> findFlightsWithDuration(LocalDateTime duration);

    // represents the list of normal flights with more than two segments
    List<Flight> findFlightsWithMultiSegments(LocalDateTime departure);

    //  represents the list of flights that departs before it arrives*/
}
