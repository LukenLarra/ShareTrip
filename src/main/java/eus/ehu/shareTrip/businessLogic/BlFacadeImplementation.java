package eus.ehu.shareTrip.businessLogic;

import eus.ehu.shareTrip.configuration.Config;
import eus.ehu.shareTrip.dataAccess.DataAccess;
import eus.ehu.shareTrip.domain.Driver;
import eus.ehu.shareTrip.domain.Ride;
import eus.ehu.shareTrip.domain.Traveler;
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

	public BlFacadeImplementation()  {
		System.out.println("Creating BlFacadeImplementation instance");
		boolean initialize = config.getDataBaseOpenMode().equals("initialize");
		dbManager = new DataAccess(initialize);
		if (initialize)
			dbManager.initializeDB();

	}

	public Ride createRide(String from, String to, Date date, int nPlaces, float price, String driverEmail ) throws RideMustBeLaterThanTodayException, RideAlreadyExistException {
		Ride ride=dbManager.createRide(from, to, date, nPlaces, price, driverEmail);
		return ride;
	}

	@Override
	public List<Ride> getRides(String origin, String destination, Date date) {
		List<Ride>  events = dbManager.getRides(origin, destination, date);
		return events;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date){
		List<Date>  dates=dbManager.getThisMonthDatesWithRides(from, to, date);
		return dates;
	}


	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 *
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */

	public Vector<Date> getEventsMonth(Date date) {
		Vector<Date>  dates = dbManager.getEventsMonth(date);
		return dates;
	}

	@Override
	public void setCurrentDriver(Driver driver) {
		this.currentDriver = driver;
	}

	@Override
	public Driver getCurrentDriver() {
		return this.currentDriver;
	}

public List<String> getDepartCities(){
		List<String> departLocations=dbManager.getDepartCities();
		return departLocations;

	}
	/**
	 * {@inheritDoc}
	 */
public List<String> getDestinationCities(String from){
		List<String> targetCities=dbManager.getArrivalCities(from);
		return targetCities;
	}

	@Override
	public List<Date> getDatesWithRides(String value, String value1) {
		List<Date> dates = dbManager.getDatesWithRides(value, value1);
		return dates;
	}

	@Override
	public void signUpDriver(String email, String name, String password) {
		Driver driver = new Driver(email, name, password);
		dbManager.signUpDriver(driver);
	}
	@Override
	public void signUpTraveler(String email, String name, String password) {
		Traveler traveler = new Traveler(email, name, password);
		dbManager.signUpTraveler(traveler);
	}

	@Override
	public boolean signIn(String email, String password) {
		return dbManager.signIn(email, password);
	}




	public boolean existsDriver(String text){
		return dbManager.existsDriver(text);
	}

	public boolean existsTraveler(String text){
		return dbManager.existsTraveler(text);
	}





}
