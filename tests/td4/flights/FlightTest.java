package td4.flights;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import td4.flights.Flight;

public class FlightTest {

	
	Flight f1 ;
	private static final double PRODUCT_MARGIN = 1.03;
	@BeforeEach
	public void setUp() {
		f1 = new Flight(100, LocalDate.of(2017,11,11), LocalTime.of(7, 45),"Nice","Paris");
	}


	@Test
	public void testGetPrice() {
		assertNotEquals(100,f1.getPrice(),0);
		assertEquals(PRODUCT_MARGIN*100,f1.getPrice(),0);
		f1.setPrice(-1);
		assertTrue(f1.getPrice()>=10);
		assertTrue(f1.getPrice()<=1000);
	}

}
