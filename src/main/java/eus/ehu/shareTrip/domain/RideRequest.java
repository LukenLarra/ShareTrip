package eus.ehu.shareTrip.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
 // test

@Entity
public class RideRequest implements Serializable {
    @Id
    private Integer requestId;

    @ManyToOne
    private Ride ride;
    @ManyToOne
    private User traveler;


    private int numSeats;
    private String reservationCode;
    private String date;
    private String status; // {PENDING, ACCEPTED, REJECTED}


    public RideRequest() {
        super();
    }

    public RideRequest(int rideId, Ride ride, int numSeats, String reservationCode, String date, User traveler) {
        super();
        this.requestId = rideId;
        this.ride = ride;
        this.numSeats = numSeats;
        this.reservationCode = reservationCode;
        this.date = date;
        this.status = "PENDING";
        this.traveler = traveler;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getTraveler() { return traveler; }

    public void setTraveler(User traveler) { this.traveler = traveler; }

    @Override
    public String toString() {
        return "Request ID: " + requestId + ", Date: " + date + ", Number of Seats: " + numSeats + ", Reservation Code: " + reservationCode + ", Status: " + status;
    }

    public LocalDate getDate() {
        return LocalDate.parse(date);
    }
}
