package eus.ehu.shareTrip.dataAccess;

import eus.ehu.shareTrip.configuration.Config;
import eus.ehu.shareTrip.configuration.UtilDate;
import eus.ehu.shareTrip.domain.*;
import eus.ehu.shareTrip.exceptions.RideAlreadyExistException;
import eus.ehu.shareTrip.exceptions.RideMustBeLaterThanTodayException;
import jakarta.persistence.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.util.*;


/**
 * Implements the Data Access utility to the objectDb database
 */
public class DataAccess {

  protected EntityManager db;
  protected EntityManagerFactory emf;

  public DataAccess() {

    this.open();

  }

  public DataAccess(boolean initializeMode) {

    this.open(initializeMode);

  }

  public void open() {
    open(false);
  }


  public void open(boolean initializeMode) {

    Config config = Config.getInstance();

    System.out.println("Opening DataAccess instance => isDatabaseLocal: " +
            config.isDataAccessLocal() + " getDatabBaseOpenMode: " + config.getDataBaseOpenMode());

    String fileName = config.getDatabaseName();
    if (initializeMode) {
      fileName = fileName + ";drop";
      System.out.println("Deleting the DataBase");
    }

    if (config.isDataAccessLocal()) {
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
              .configure() // configures settings from hibernate.cfg.xml
              .build();
      try {
        emf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
      } catch (Exception e) {
        // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
        // so destroy it manually.
        StandardServiceRegistryBuilder.destroy(registry);
      }

      db = emf.createEntityManager();
      System.out.println("DataBase opened");
    }
  }



  public void reset() {
    db.getTransaction().begin();
    db.createQuery("DELETE FROM Ride ").executeUpdate();
    db.createQuery("DELETE FROM Driver ").executeUpdate();
    db.getTransaction().commit();
  }

  public void initializeDB() {

    this.reset();

    db.getTransaction().begin();

    try {

      Calendar today = Calendar.getInstance();

      int month = today.get(Calendar.MONTH);
      int year = today.get(Calendar.YEAR);
      if (month == 12) {
        month = 1;
        year += 1;
      }


      //Create drivers
      Driver driver1 = new Driver("driver1@gmail.com", "Aitor Fernandez", "1234", new File("src/main/resources/images/foto2.jpg").getAbsolutePath());
      Driver driver2 = new Driver("driver2@gmail.com", "Ane Gaztañaga", "4321", new File("src/main/resources/images/foto1.jpg").getAbsolutePath());
      Driver driver3 = new Driver("driver3@gmail.com", "Test driver", "0000", new File("src/main/resources/images/defaultProfile.jpg").getAbsolutePath());

      Traveler traveler1 = new Traveler("traveler1@gmail.com", "Juan Perez", "9999",new File("src/main/resources/images/foto3.jpg").getAbsolutePath());
      Traveler traveler2 = new Traveler("traveler2@gmail.com", "Gorka Astigarraga", "9876", new File("src/main/resources/images/foto4.jpg").getAbsolutePath());


      //Create rides
      driver1.addRide("Donostia", "Bilbo", UtilDate.newDate(year, month, 15), 4, 7);
      driver1.addRide("Donostia", "Bilbo", UtilDate.newDate(year, month + 1, 15), 4, 7);

      driver1.addRide("Donostia", "Gasteiz", UtilDate.newDate(year, month, 6), 4, 8);
      driver1.addRide("Bilbo", "Donostia", UtilDate.newDate(year, month, 25), 4, 4);

      driver1.addRide("Donostia", "Iruña", UtilDate.newDate(year, month, 7), 4, 8);

      driver2.addRide("Donostia", "Bilbo", UtilDate.newDate(year, month, 15), 3, 3);
      driver2.addRide("Bilbo", "Donostia", UtilDate.newDate(year, month, 25), 2, 5);
      driver2.addRide("Eibar", "Gasteiz", UtilDate.newDate(year, month, 6), 2, 5);

      driver3.addRide("Bilbo", "Donostia", UtilDate.newDate(year, month, 14), 1, 3);


      db.persist(driver1);
      db.persist(driver2);
      db.persist(driver3);
      db.persist(traveler1);
      db.persist(traveler2);


      db.getTransaction().commit();
      System.out.println("Db initialized");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }



  /**
   * This method retrieves from the database the dates in a month for which there are events
   *
   * @param date of the month for which days with events want to be retrieved
   * @return collection of dates
   */
  public Vector<Date> getEventsMonth(Date date) {
    System.out.println(">> DataAccess: getEventsMonth");
    Vector<Date> res = new Vector<Date>();

    Date firstDayMonthDate = UtilDate.firstDayMonth(date);
    Date lastDayMonthDate = UtilDate.lastDayMonth(date);


    TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ride.date FROM Ride ride "
            + "WHERE ride.date BETWEEN ?1 and ?2", Date.class);
    query.setParameter(1, firstDayMonthDate);
    query.setParameter(2, lastDayMonthDate);
    List<Date> dates = query.getResultList();
    for (Date d : dates) {
      System.out.println(d.toString());
      res.add(d);
    }
    return res;
  }


  public Ride createRide(String from, String to, Date date, int nPlaces, float price, String driverEmail) throws RideAlreadyExistException, RideMustBeLaterThanTodayException {
    System.out.println(">> DataAccess: createRide=> from= " + from + " to= " + to + " driver=" + driverEmail + " date " + date);
    try {
      if (new Date().compareTo(date) > 0) {
        throw new RideMustBeLaterThanTodayException(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorRideMustBeLaterThanToday"));
      }
      db.getTransaction().begin();

      TypedQuery<Driver> driverQuery = db.createQuery("SELECT d FROM Driver d WHERE d.email = :email", Driver.class);
      driverQuery.setParameter("email", driverEmail);
      Driver driver = driverQuery.getSingleResult();
      
      if (driver.doesRideExists(from, to, date)) {
        db.getTransaction().commit();
        throw new RideAlreadyExistException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.RideAlreadyExist"));
      }
      Ride ride = driver.addRide(from, to, date, nPlaces, price);
      //next instruction can be obviated
      db.persist(driver);
      db.getTransaction().commit();

      return ride;
    } catch (NullPointerException e) {
      // TODO Auto-generated catch block
      db.getTransaction().commit();
      return null;
    }


  }

  public List<Ride> getRides(String origin, String destination, Date date) {
    System.out.println(">> DataAccess: getRides origin/dest/date");
    Vector<Ride> res = new Vector<>();

    TypedQuery<Ride> query = db.createQuery("SELECT ride FROM Ride ride "
            + "WHERE ride.date=?1 ", Ride.class);
    query.setParameter(1, date);


    return query.getResultList();
  }


  /**
   * This method returns all the cities where rides depart
   * @return collection of cities
   */
  public List<String> getDepartCities(){
    TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.fromLocation FROM Ride r ORDER BY r.fromLocation", String.class);
    List<String> cities = query.getResultList();
    return cities;

  }
  /**
   * This method returns all the arrival destinations, from all rides that depart from a given city
   *
   * @param from the departure location of a ride
   * @return all the arrival destinations
   */
  public List<String> getArrivalCities(String from){
    TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.toLocation FROM Ride r WHERE r.fromLocation=?1 ORDER BY r.toLocation",String.class);
    query.setParameter(1, from);
    List<String> arrivingCities = query.getResultList();
    return arrivingCities;

  }

  /**
   * This method retrieves from the database the dates a month for which there are events
   * @param from the origin location of a ride
   * @param to the destination location of a ride
   * @param date of the month for which days with rides want to be retrieved
   * @return collection of rides
   */
  public List<Date> getThisMonthDatesWithRides(String from, String to, Date date) {
    System.out.println(">> DataAccess: getEventsMonth");
    List<Date> res = new ArrayList<>();

    Date firstDayMonthDate= UtilDate.firstDayMonth(date);
    Date lastDayMonthDate= UtilDate.lastDayMonth(date);


    TypedQuery<Date> query = db.createQuery("SELECT DISTINCT r.date FROM Ride r WHERE r.fromLocation=?1 AND r.toLocation=?2 AND r.date BETWEEN ?3 and ?4",Date.class);

    query.setParameter(1, from);
    query.setParameter(2, to);
    query.setParameter(3, firstDayMonthDate);
    query.setParameter(4, lastDayMonthDate);
    List<Date> dates = query.getResultList();
    for (Date d:dates){
      res.add(d);
    }
    return res;
  }

  public List<Date> getDatesWithRides(String from, String to) {
    System.out.println(">> DataAccess: getEventsFromTo");
    List<Date> res = new ArrayList<>();

    TypedQuery<Date> query = db.createQuery("SELECT DISTINCT r.date FROM Ride r WHERE r.fromLocation=?1 AND r.toLocation=?2",Date.class);

    query.setParameter(1, from);
    query.setParameter(2, to);
    List<Date> dates = query.getResultList();
    for (Date d:dates){
      res.add(d);
    }
    return res;
  }
  private void generateTestingData() {
    // create domain entities and persist them
  }

  public boolean existsUser(String email) {
    TypedQuery<User> userQuery = db.createQuery("SELECT u FROM User u " +
            "WHERE u.email = :email", User.class);
    userQuery.setParameter("email", email);
    return !userQuery.getResultList().isEmpty();
  }

  public User signIn(String email, String password) {
    TypedQuery<User> userQuery = db.createQuery("SELECT u FROM User u " +
            "WHERE u.email = :email AND " +
            " u.password = :password", User.class);
    userQuery.setParameter("email", email);
    userQuery.setParameter("password", password);
    return userQuery.getSingleResult();
  }

  public void signUp(String email, String name, String password, String role, String imagePath) {
    db.getTransaction().begin();
    if (role.equals("Driver")){
      db.persist(new Driver(email, name, password, imagePath));
    } else {
      db.persist(new Traveler(email, name, password, imagePath));
    }
    db.getTransaction().commit();

  }

  public Ride getRideById(int rideId) {
    TypedQuery<Ride> rideQuery = db.createQuery("SELECT r FROM Ride r WHERE r.id = :id", Ride.class);
    rideQuery.setParameter("id", rideId);
    return rideQuery.getSingleResult();
  }

  public void requestRide(RideRequest rideRequest){
    //if the ride request already exists, cancel the previous one and create a new one
    db.getTransaction().begin();
    TypedQuery<RideRequest> query = db.createQuery("SELECT rr FROM RideRequest rr WHERE rr.traveler.id = :travelerId AND rr.ride.id = :rideId", RideRequest.class);
    query.setParameter("travelerId", rideRequest.getTraveler().getId());
    query.setParameter("rideId", rideRequest.getRide().getRideNumber());
    List<RideRequest> rideRequests = query.getResultList();
    for (RideRequest rr : rideRequests) {
      db.remove(rr);
      //update the ride giving back the seats that havent been accepted
        Ride ride = rr.getRide();
        ride.setNumPlaces(ride.getNumPlaces() + rr.getNumSeats());
    }
    db.persist(rideRequest);
    db.getTransaction().commit();
  }
  public RideRequest getRideRequestByReservationCode(String reservationCode) {
    TypedQuery<RideRequest> query = db.createQuery("SELECT rr FROM RideRequest rr WHERE rr.reservationCode = :reservationCode", RideRequest.class);
    query.setParameter("reservationCode", reservationCode);
    try {
      return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public void deleteRideRequest(String requestCode) {
    db.getTransaction().begin();
    TypedQuery<RideRequest> query = db.createQuery("SELECT rr FROM RideRequest rr WHERE rr.reservationCode = :reservationCode", RideRequest.class);
    query.setParameter("reservationCode", requestCode);
    RideRequest rideRequest = query.getSingleResult();
    db.remove(rideRequest);
    db.getTransaction().commit();
  }

  public List<RideRequest> getRideRequestsForDriver(int driverId) {
    TypedQuery<RideRequest> rideQuery = db.createQuery("SELECT rr FROM RideRequest rr WHERE rr.ride.driver.id = :driverId", RideRequest.class);
    rideQuery.setParameter("driverId", driverId);
    return rideQuery.getResultList();
  }

  //change status of ride request
    public void changeStatus(String requestCode, String status) {
        db.getTransaction().begin();
        TypedQuery<RideRequest> query = db.createQuery("SELECT rr FROM RideRequest rr WHERE rr.reservationCode = :reservationCode", RideRequest.class);
        query.setParameter("reservationCode", requestCode);
        RideRequest rideRequest = query.getSingleResult();
        rideRequest.setStatus(status);
        db.getTransaction().commit();
    }

    public List<RideRequest> getRideRequestsForTraveler(int travelerId) {
        TypedQuery<RideRequest> rideQuery = db.createQuery("SELECT rr FROM RideRequest rr WHERE rr.traveler.id = :travelerId", RideRequest.class);
        rideQuery.setParameter("travelerId", travelerId);
        return rideQuery.getResultList();
    }

    public String getImagePath(Long id) {
        TypedQuery<User> userQuery = db.createQuery("SELECT u FROM User u " +
                "WHERE u.id = :id", User.class);
        userQuery.setParameter("id", id);
        return userQuery.getSingleResult().getProfileImage();
    }

    public void updateImagePath(Long id, String imagePath) {
        db.getTransaction().begin();
        TypedQuery<User> userQuery = db.createQuery("SELECT u FROM User u " +
                "WHERE u.id = :id", User.class);
        userQuery.setParameter("id", id);
        User user = userQuery.getSingleResult();
        user.setProfileImage(imagePath);
        db.getTransaction().commit();
    }

  public void setImagePath(Long id, String imagePath) {
    db.getTransaction().begin();
    TypedQuery<User> userQuery = db.createQuery("SELECT u FROM User u " +
            "WHERE u.id = :id", User.class);
    userQuery.setParameter("id", id);
    User user = userQuery.getSingleResult();
    user.setProfileImage(imagePath);
    db.getTransaction().commit();
  }
}
