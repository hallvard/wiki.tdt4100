package kont2019.bike;

import java.util.ArrayList;
import java.util.List;

/*
 * @startuml
 * class Bike
 * Bike --> Location: location
 * Bike --> Person: renter
 * @enduml
 */
public class Bike {

	private GeoLocation location;
	private Person renter;

	public GeoLocation getLocation() {
		return location;
	}

	void setLocation(final GeoLocation location) {
		this.location = location;
	}

	public Person getRenter() {
		return renter;
	}

	void setRenter(final Person renter) {
		this.renter = renter;
	}

	// for computing rental price

	private final List<RentalInfo> rentals = new ArrayList<RentalInfo>();

	void addRentalInfo(final RentalInfo rentalInfo) {
		rentals.add(rentalInfo);
	}

	List<RentalInfo> getRentalInfos() {
		return new ArrayList<RentalInfo>(rentals);
	}

	void clearRentalInfos() {
		rentals.clear();
	}
}
