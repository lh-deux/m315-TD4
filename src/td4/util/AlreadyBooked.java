package td4.util;

/**
 * 
 * This exception is raised when something is already rented and attempts are made to over-rent it.
 * @author Mireille Blay-Fornarino
 *
 */
public class AlreadyBooked extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyBooked(String message) {
		super(message);
	}

}
