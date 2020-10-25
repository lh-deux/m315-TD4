package td4.cars;

import td4.core.Product;

import java.time.LocalDate;

/**
 * 
 * The Car Rental class represents the rental of a car, for a given duration and start date.
 * 
 * @author Mireille Blay-Fornarino
 *
 * 6
 */
/**
 * @author blay
 *
 */
public class CarRental implements Product {

	private Car car;
	private double dayPrice;
	private int duration;
	private LocalDate beginDate;
	
	private CarRental(Car car, double dayPrice, int duration, LocalDate beginDate) {
		super();
		this.car = car;
		this.dayPrice = dayPrice;
		this.duration = duration;
		this.beginDate = beginDate;
	}
	
	/**
	 * the price of the rental should not change even if the price of the car changes.
	 * @param c : the car to rent
	 * @param beginDate : the date from which the car is rented out
	 * @param duration : rental duration in days
	 */
	public CarRental(Car c, LocalDate beginDate, int duration) {
		this(c,c.getDayPrice(),duration,beginDate);
	}

	public String getCarNumber() {
		return car.getNumberPlate();
	}
	
	public Car getCar() {
		return car;
	}
	
	/**
	 * When we change the car assigned to a rental, the price does not change.
	 * @param aCar
	 */
	public void setCar(Car aCar) {
		this.car = aCar;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public LocalDate getBeginDate() {
		return beginDate;
	}
	
	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}
	
	public double getPrice() {
		return PRODUCT_MARGIN*dayPrice*duration;
	}

	/**
	 * 
	 * @param dates : an array of dates
	 * @return true when one of the dates in the parameter is included in the duration of the car rental
	 */
	public boolean includeADate(LocalDate[] dates) {
		for (LocalDate d : dates) {
			if (d.equals(beginDate)) 
				return true;
			if ( (d.isAfter(beginDate)) && 
					(d.isBefore(beginDate.plusDays(duration) ) ) )
				return true;
		}
		return false;
	}
	

}
