package openClosedPrinciples.core;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class FlightServiceTest {

	private static final String PARIS = "Paris";
	private static final String NICE = "Nice";
	private static final LocalDate dateToTest = LocalDate.of(2017, 12, 24);
	
	FlightService service ;
	@Before
	public void setUp() {
		ArrayList<Flight> list = new ArrayList<>();
		list.add(new Flight("Belfort"));
		list.add(new Flight(NICE));
		list.add(new Flight(100, dateToTest, LocalTime.of(7, 45),NICE, PARIS));
		list.add(new Flight(150, dateToTest, LocalTime.of(9, 30), NICE, PARIS));
		list.add(new Flight(150, dateToTest, LocalTime.of(18, 30), PARIS, NICE));
		service = new FlightService(list);
	}


	@Test
	public void testGetFlightsOnADivenDate() {
		List<Flight> flights = service.getFlights(LocalDate.now());
		assertEquals(2, flights.size());
		flights = service.getFlights(dateToTest);
		assertEquals(3, flights.size());
	}

	@Test
	public void testGetFlights() {
		List<Flight> flights = service.getFlights(LocalDate.now(),NICE,PARIS);
		assertEquals(1, flights.size());
		flights = service.getFlights(dateToTest,NICE,PARIS);
		assertEquals(2, flights.size());
	}

}
