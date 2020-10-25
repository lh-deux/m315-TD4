package td4.cars;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import td4.Service;
import td4.core.Description;
import td4.core.Product;
import td4.core.Service4PI;
import td4.flights.FlightService;
import td4.util.DateTools;
import td4.util.NotPossibleCarRentalException;


/**
 *
 * This class allows the management of a set of car rentals
 * 
 * 
 * @author Mireille Blay-Fornarino
 *
 * 
 */
public class CarRentalService extends FlightService implements Service {

	//Set of cars for rent
	private List<Car> cars;

	//To create a car rental service,  you need to have cars.
	public CarRentalService(List<Car> cars) {
		super(new ArrayList<>());
		this.cars = cars;
	}

	
	/**
	 * @param d : first day
	 * @param nbDays : number of days
	 * @return the available cars  from the first day during {@code nbDays} days
	 */
	public List<Car> getAvailableCars(LocalDate d, int nbDays) {
		ArrayList<Car> availableCars = new ArrayList<>();
		LocalDate[] dates = DateTools.getDays(d, nbDays);
		for (Car c : cars) {
			if (isAvailable(c, dates)) {
				availableCars.add(c);
			}
		}
		return availableCars;
	}
	

	private boolean isAvailable(Car c, LocalDate[] dates) {
		for (CarRental carRental : payingItemList) {
			if (c.equals(carRental.getCar()) &&
				(carRental.includeADate(dates)) ) {
				return false;	
			}	
		}
		return true;
	}

	
	/**
	 * It books the car rental and returns the created {@code CarRental} 
	 * @param c : {@code Car} for rent
	 * @param fromDate : {@code LocalDate} first day for rental
	 * @param numberOfDays 
	 * @return the rental of {@code Car} {@code c} from the first day  {@code  fromDate} during  {@code numberOfDays}
	 * @throws NotPossibleCarRentalException
	 */
	public CarRental book(Car c, LocalDate fromDate, int numberOfDays) throws NotPossibleCarRentalException  {
		CarRental carRental = null;
		if (cars == null || !(cars.contains(c)) )
			throw new NotPossibleCarRentalException("Not known car");
		LocalDate[] dates = DateTools.getDays(fromDate, numberOfDays);
		if (isAvailable(c, dates)) {
			carRental = new CarRental(c, fromDate, numberOfDays);
			payingItemList.add(carRental);
		}
		return carRental;
	}


	public CarRental find(Description desc) {
		//on récupère les voitures déjà disponibles
		List<Car> carDispos = getAvailableCars(desc.getDateDepart(),desc.getDuree());
		List<CarRental> locationsDispos = new ArrayList<>();
		if(carDispos.size() != 0){
			for(Car c : carDispos){
				for(CarRental cr: payingItemList){
					if(cr.getCar().equals(c) && !locationsDispos.contains(cr)){
						locationsDispos.add(cr);
						carDispos.remove(c);
					}
				}
				try{
					CarRental b = book(c,desc.getDateDepart(),desc.getDuree());
					locationsDispos.add(b);
				}
				catch(NotPossibleCarRentalException e){
					System.err.println(e.getMessage());
				}
			}
		}
		locationsDispos.sort(Comparator.comparing(Product::getPrice));
		return locationsDispos.get(0);
	}
}
