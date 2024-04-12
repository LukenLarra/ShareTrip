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

    private int numSeats;
    private String reservationCode;
    private String date;
    private String status; // {PENDING, ACCEPTED, REJECTED}


    public RideRequest() {
        super();
    }

    public RideRequest(int rideId, Ride ride, int numSeats, String reservationCode, String date) {
        super();
        this.requestId = rideId;
        this.ride = ride;
        this.numSeats = numSeats;
        this.reservationCode = reservationCode;
        this.date = date;
        this.status = "PENDING";
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

    @Override
    public String toString() {
        return "Request ID: " + requestId + ", Date: " + date + ", Number of Seats: " + numSeats + ", Reservation Code: " + reservationCode + ", Status: " + status;
    }

    public LocalDate getDate() {
        return LocalDate.parse(date);
    }
}
