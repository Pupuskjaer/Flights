import com.gridnine.testing.filter.FlightFilterBuilder;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;

import javax.xml.datatype.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LocalDateTime dep = LocalDateTime.now().plusHours(3);
        LocalDateTime arr = LocalDateTime.now().plusHours(6);
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println(flights);
        List<Flight> some = FlightFilterBuilder.filterFlights(flights, flight -> flight.getSegments().size() >= 2);
        System.out.println(some);
        List<Flight> byDuration = FlightFilterBuilder.filterFlightsByDuration(flights,  dep, arr);
        System.out.println(byDuration);
    }
}
