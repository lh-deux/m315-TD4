package openClosedPrinciples.core;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CarRentalTest {

	Car myCar ;
	CarRental carRental;
	
	@BeforeEach
	public void setUp() {
		myCar = new Car("1111 AB 06",50);
	}


	@Test
	public void testCarRentalCreation() {
		carRental = new CarRental(myCar, LocalDate.of(2018, 8, 30), 3);
		assertEquals( myCar.getDayPrice()*3, carRental.getPrice());
	}

	@Test
	public void testCarRentalAvailability() {
		carRental = new CarRental(myCar, LocalDate.of(2018, 8, 30), 3);
		LocalDate[] dates = DateTools.getDays(LocalDate.of(2018, 8, 30), 3);
		assertEquals(3, dates.length );
		dates = DateTools.getDays(LocalDate.of(2018, 8, 30), 1);
		assertTrue(carRental.includeADate(dates),"date de début OK");
		
		dates = DateTools.getDays(LocalDate.of(2018, 8, 29), 1);
		assertFalse(carRental.includeADate(dates),"date à un jour avant");
		
		dates = DateTools.getDays(LocalDate.of(2018, 9, 2), 1);
		assertFalse(carRental.includeADate(dates),"date à un jour après");
		
		dates = DateTools.getDays(LocalDate.of(2018, 8, 25), 10);
		assertTrue(carRental.includeADate(dates),"date incluse après");
		
		dates = DateTools.getDays(LocalDate.of(2018, 9, 1), 1);
		assertTrue(carRental.includeADate(dates),"date incluse sur la fin");
		dates = DateTools.getDays(LocalDate.of(2018, 8, 31), 10);
		assertTrue(carRental.includeADate(dates),"date incluse sur la fin");
	}

	@Test
	public void testGetPrice() {
		carRental = new CarRental(myCar, LocalDate.of(2017, 8, 31), 3);
		assertEquals(50,myCar.getDayPrice());
		assertEquals(50.0*3,carRental.getPrice());
	}

}
