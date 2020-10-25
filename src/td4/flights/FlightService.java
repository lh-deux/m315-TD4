package td4.flights;

import td4.Service;
import td4.core.Description;
import td4.core.Product;
import td4.core.Service4PI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class allows the management of a set of flights
 * 
 * @author Mireille Blay-Fornarino
 *
 * 
 */

public class FlightService extends Service4PI<Flight> implements Service {



	public FlightService(List<Flight> flights) {
		super(flights);
	}

	/**
	 * @param aDate : {@code LocalDate}
	 * @return the list of flights available on a given date {@code LocalDate}
	 */
	public List<Flight> getFlights(LocalDate aDate) {
		Stream<Flight> parallelStream = payingItemList.parallelStream();
		Stream<Flight> results = parallelStream.filter(f -> (f.getDepartDate().equals(aDate)));
		return results.collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * @param d    : : {@code LocalDate}
	 * @param from : departure airport name
	 * @param to   : arrival airport name
	 * @return the list of flights available on a given date {@code LocalDate} from
	 *         a place to another place
	 */
	public List<Flight> getFlights(LocalDate d, String from, String to) {
		Stream<Flight> parallelStream = payingItemList.parallelStream();
		Stream<Flight> results = parallelStream.filter(f -> f.match(d, from, to));
		return results.collect(Collectors.toCollection(ArrayList::new));
	}

	public Flight find(Description desc){
		List<Flight> fl = null;
		fl = getFlights(desc.getDateDepart(),desc.getDepart(),desc.getArrivee());
		if(fl.size() != 0 ){
			fl.sort(Comparator.comparing(Product::getPrice));
			return fl.get(0);
		}
		return null;
	}

}
