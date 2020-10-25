package td4.flights;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class FlightServiceTest {

	private static final String PARIS = "Paris";
	private static final String NICE = "Nice";
	private static final LocalDate dateToTest = LocalDate.of(2017, 12, 24);
	private static final double PRODUCT_MARGIN = 1.03;
	FlightService service ;
	
	@BeforeEach
	public void setUp() {
		ArrayList<Flight> list = new ArrayList<>();
		list.add(new Flight("Belfort"));
		list.add(new Flight(NICE));
		list.add(new Flight(100, dateToTest, LocalTime.of(7, 45),NICE, PARIS));
		list.add(new Flight(20, dateToTest, LocalTime.of(9, 30), NICE, PARIS));
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
	
	@Test
	public void testSortedByPrice() {
		ArrayList<Flight> list = new ArrayList<>();
		list.add(new Flight(100, dateToTest, LocalTime.of(7, 45),NICE, PARIS));
		list.add(new Flight(20, dateToTest, LocalTime.of(9, 30), NICE, PARIS));
		list.add(new Flight(150, dateToTest, LocalTime.of(18, 30), PARIS, NICE));
		service = new FlightService(list);
		List<Flight> flights = service.sortedByPrice();
		assertNotEquals(20,flights.get(0).getPrice(),0.01);
		assertNotEquals(100,flights.get(1).getPrice(),0.01);
		assertNotEquals(150,flights.get(2).getPrice(),0.01);
		assertEquals(PRODUCT_MARGIN*20,flights.get(0).getPrice(),0.01);

		assertEquals(PRODUCT_MARGIN*100,flights.get(1).getPrice(),0.01);
		assertEquals(PRODUCT_MARGIN*150,flights.get(2).getPrice(),0.01);
	}

}
