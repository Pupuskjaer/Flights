import com.gridnine.testing.filter.FilterBuilder;
import com.gridnine.testing.filter.FlightFilterBuilder;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;

import java.time.LocalDateTime;
import java.util.List;

import static com.gridnine.testing.filter.FlightFilterBuilder.*;

public class Main {
    public static void main(String[] args) {
        LocalDateTime dep = LocalDateTime.now();
        LocalDateTime arr = LocalDateTime.now().plusHours(2);
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println(flights);
        List<Flight> some = filterFlights(flights, flight -> flight.getSegments().size() >= 2);
        System.out.println(some);
        List<Flight> newList = new FlightFilterBuilder(flights).
                addFilter(flight -> flight.getSegments().size()>=2).
                build();
        System.out.println(newList);
        List<Flight> byDuration = new FlightFilterBuilder(flights).
                addFilter(filterFlightsByDuration( dep, arr)).
                addFilter(flight -> flight.getSegments().size() <= 5).build();
        System.out.println(byDuration);
    }
}
