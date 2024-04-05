package eus.ehu.shareTrip.domain;

import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.dataAccess.DataAccess;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;


@Entity
public class RideRequest implements Serializable {
    @Id
    @GeneratedValue
    private Integer requestId;

    @ManyToOne
    private Ride ride;

    private int numSeats;
    private String reservationCode;
    private String date;


    public RideRequest() {
        super();
    }

    public RideRequest(Ride ride, int numSeats, String reservationCode, String date) {
        super();
        this.ride = ride;
        this.numSeats = numSeats;
        this.reservationCode = reservationCode;
        this.date = date;
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

}
