package eus.ehu.shareTrip.businessLogic;

import eus.ehu.shareTrip.configuration.Config;
import eus.ehu.shareTrip.dataAccess.DataAccess;
import eus.ehu.shareTrip.domain.Driver;
import eus.ehu.shareTrip.domain.Ride;
import eus.ehu.shareTrip.domain.Traveler;
import eus.ehu.shareTrip.domain.User;
import eus.ehu.shareTrip.exceptions.RideAlreadyExistException;
import eus.ehu.shareTrip.exceptions.RideMustBeLaterThanTodayException;

import java.util.Date;
import java.util.List;
import java.util.Vector;


/**
 * Implements the business logic as a web service.
 */
public class BlFacadeImplementation implements BlFacade {

	DataAccess dbManager;
	Config config = Config.getInstance();
	private Driver currentDriver;
	private Traveler currentTraveler;

	public BlFacadeImplementation() {
		System.out.println("Creating BlFacadeImplementation instance");
		boolean initialize = config.getDataBaseOpenMode().equals("initialize");
		dbManager = new DataAccess(initialize);
		if (initialize)
			dbManager.initializeDB();

	}

	public Ride createRide(String from, String to, Date date, int nPlaces, float price, String driverEmail) throws RideMustBeLaterThanTodayException, RideAlreadyExistException {
		Ride ride = dbManager.createRide(from, to, date, nPlaces, price, driverEmail);
		return ride;
	}

	@Override
	public List<Ride> getRides(String origin, String destination, Date date) {
		List<Ride> events = dbManager.getRides(origin, destination, date);
		return events;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date) {
		List<Date> dates = dbManager.getThisMonthDatesWithRides(from, to, date);
		return dates;
	}


	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 *
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */

	public Vector<Date> getEventsMonth(Date date) {
		Vector<Date> dates = dbManager.getEventsMonth(date);
		return dates;
	}

	@Override
	public void setCurrentDriver(Driver driver) {
		this.currentDriver = driver;
	}

	@Override
	public void setCurrentTraveler(Traveler traveler) {
		this.currentTraveler = traveler;
	}

	@Override
	public Driver getCurrentDriver() {
		return this.currentDriver;
	}

	@Override
	public Traveler getCurrentTraveler() {
		return this.currentTraveler;
	}

	public List<String> getDepartCities() {
		List<String> departLocations = dbManager.getDepartCities();
		return departLocations;

	}

	/**
	 * {@inheritDoc}
	 */
	public List<String> getDestinationCities(String from) {
		List<String> targetCities = dbManager.getArrivalCities(from);
		return targetCities;
	}

	@Override
	public List<Date> getDatesWithRides(String value, String value1) {
		List<Date> dates = dbManager.getDatesWithRides(value, value1);
		return dates;
	}

	@Override
	public void signUp(String email, String name, String password, String role) {
		dbManager.signUp(email, name, password, role);
	}

	@Override
	public User signIn(String email, String password) {
		return dbManager.signIn(email, password);
}

	public boolean existsUser(String text){
		return dbManager.existsUser(text);
	}





}
