package eus.ehu.shareTrip.businessLogic;

import eus.ehu.shareTrip.configuration.Config;
import eus.ehu.shareTrip.dataAccess.DataAccess;
import eus.ehu.shareTrip.domain.Ride;
import eus.ehu.shareTrip.domain.RideRequest;
import eus.ehu.shareTrip.domain.User;
import eus.ehu.shareTrip.exceptions.RideAlreadyExistException;
import eus.ehu.shareTrip.exceptions.RideMustBeLaterThanTodayException;

import java.util.*;


/**
 * Implements the business logic as a web service.
 */
public class BlFacadeImplementation implements BlFacade {

	DataAccess dbManager;
	Config config = Config.getInstance();
	private User currentUser;

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
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}

	@Override
	public User getCurrentUser() {
		return this.currentUser;
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
	public void signUp(String email, String name, String password, String role, String imagePath) {
		dbManager.signUp(email, name, password, role, imagePath);
	}

	@Override
	public User signIn(String email, String password) {
		return dbManager.signIn(email, password);
}
	@Override
	public boolean existsUser(String text){
		return dbManager.existsUser(text);
	}

	@Override
	public boolean requestRide(int rideId, int numSeats, String date){
		Ride ride = dbManager.getRideById(rideId);
		if (ride != null) {
			String reservationCode = UUID.randomUUID().toString().substring(0, 8);
			RideRequest rideRequest = new RideRequest(rideId, ride, numSeats, reservationCode, date, currentUser);
           	dbManager.requestRide(rideRequest);
			return true;
		}
		return false;
	}
	@Override
	public void logout() {
		// Clear the current user
		currentUser = null;
	}

	@Override
	public RideRequest getRideRequestByReservationCode(String reservationCode) {
		return dbManager.getRideRequestByReservationCode(reservationCode);
	}

	@Override
	public void acceptRequest(String requestCode){
		dbManager.changeStatus(requestCode, "ACCEPTED");
	}
	@Override
	public void rejectRequest(String requestCode){
		//update the ride giving back the seats that havent been accepted
		RideRequest rideRequest = dbManager.getRideRequestByReservationCode(requestCode);
		Ride ride = rideRequest.getRide();
		ride.setNumPlaces(ride.getNumPlaces() + rideRequest.getNumSeats());
		dbManager.changeStatus(requestCode, "REJECTED");	}

	@Override
	public List<RideRequest> getRideRequestsForDriver(int driverId) {
		return dbManager.getRideRequestsForDriver(driverId);
	}

	@Override
	public List<RideRequest> getRideRequestsForTraveler(int travelerId){
		return dbManager.getRideRequestsForTraveler(travelerId);
	}

	@Override
	public void deleteRideRequest(String requestId){
		dbManager.deleteRideRequest(requestId);
	}

	@Override
	public String getImagePath(Long id){
		return dbManager.getImagePath(id);
	}

	@Override
	public void setImagePath(Long id, String imagePath){
		dbManager.setImagePath(id, imagePath);
	}

	@Override
	public void updateImagePath(Long id, String imagePath){
		dbManager.updateImagePath(id, imagePath);
	}


}
