package eus.ehu.shareTrip.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;

@Entity
@DiscriminatorValue("DRIVER")
public class Driver extends User implements Serializable {

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST, mappedBy="driver", orphanRemoval=true)
	private List<Ride> rides=new Vector<Ride>();


	public Driver(String email, String name, String password, String profileImage) {
		super(email, name, password, profileImage);
	}

	public Driver() {
		super();
	}


	public String getEmail() {
		return super.getEmail();
	}

	public void setEmail(String email) {
		super.setEmail(email);
	}

	public String getName() {
		return super.getName();
	}

	public void setName(String name) {
		super.setName(name);
	}

	public String getPassword() {
		return super.getPassword();
	}

	public void setPassword(String password) {
		super.setPassword(password);
	}
	
	public String toString(){
		return super.toString();
	}
	
	/**
	 * This method creates a new ride for the driver
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public Ride addRide(String from, String to, Date date, int nPlaces, float price)  {
        Ride ride=new Ride(from,to,date,nPlaces,price, this);
        rides.add(ride);
        return ride;
	}

	/**
	 * This method checks if the ride already exists for that driver
	 * 
	 * @param from the origin location 
	 * @param to the destination location 
	 * @param date the date of the ride 
	 * @return true if the ride exists and false in other case
	 */
	public boolean doesRideExists(String from, String to, Date date)  {	
		for (Ride r:rides)
			if ( (java.util.Objects.equals(r.getFromLocation(),from)) && (java.util.Objects.equals(r.getToLocation(),to)) && (java.util.Objects.equals(r.getDate(),date)) )
			 return true;
		
		return false;
	}
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		if (!super.getEmail().equals(other.getEmail()))
			return false;
		return true;
	}

	public Ride removeRide(String from, String to, Date date) {
		boolean found=false;
		int index=0;
		Ride r=null;
		while (!found && index<=rides.size()) {
			r=rides.get(++index);
			if ( (java.util.Objects.equals(r.getFromLocation(),from)) && (java.util.Objects.equals(r.getToLocation(),to)) && (java.util.Objects.equals(r.getDate(),date)) )
				found=true;
		}
		if (found) {
			rides.remove(index);
			return r;
		} else return null;
	}

	public List<Ride> getRides() {
		return rides;
	}
}
