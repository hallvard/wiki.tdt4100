package kont2019.bike;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/*
 * @startuml
 * class Bike
 *
 * class Location {
 * 	double latitude
 * 	double longitude
 * }
 * Bike --> Location: location
 *
 * class BikeRental
 * BikeRental *-- "*" Location: places
 * BikeRental *-- "*" Bike: allBikes
 *
 * class Person
 * class RentalInfo {
 * 	LocalDateTime startTime
 * 	LocalDateTime endTime
 * }
 * Bike --> Person: renter
 * Bike *--> "*" RentalInfo: rentals
 *
 * interface PricePolicy
 * class DefaultPricePolicy
 * DefaultPricePolicy -up-|> PricePolicy
 * BikeRental --> PricePolicy: pricePolicy
 * Person -left-> PricePolicy: pricePolicy
 *
 * @enduml
 */
public class BikeRental {

	private final Collection<GeoLocation> places = new ArrayList<GeoLocation>();
	private final Collection<Bike> allBikes = new ArrayList<Bike>();

	void addPlace(final GeoLocation location) {
		places.add(location);
	}

	void addBike(final Bike bike, final GeoLocation location) {
		if (location != null) {
			bike.setLocation(location);
		}
		allBikes.add(bike);
	}

	/**
	 * Counts the number of available bikes within a certain distance of a provided location.
	 * @param location
	 * @param distance
	 * @return the number of available bikes within a certain distance of a provided location
	 */
	private int countAvailableBikesNearby(final GeoLocation location, final double distance) {
		int count = 0;
		for (final Bike bike : allBikes) {
			if (bike.getRenter() == null && bike.getLocation().distance(location) <= distance) {
				count++;
			}
		}
		return count;
		//		return allBikes.stream().filter(bike -> bike.getRenter() == null && bike.getLocation().distance(location) <= distance).count();
	}

	/**
	 * Finds the closest station (location) within the provided (maximum) distance of the provided bike
	 * @param bike
	 * @param minDistance
	 * @return the closest station (location) within the provided (maximum) distance of the provided bike
	 */
	private GeoLocation getStationNearby(final Bike bike, final double maxDistance) {
		GeoLocation closestPlace = null;
		double minDistance = maxDistance;
		for (final GeoLocation place : places) {
			final double distance = bike.getLocation().distance(place);
			if (distance < minDistance) {
				closestPlace = place;
				minDistance = distance;
			}
		}
		return closestPlace;
	}

	/**
	 * @return the bikes that currently are rented
	 */
	private Collection<Bike> getRentedBikes() {
		final Collection<Bike> result = new ArrayList<Bike>();
		for (final Bike bike : allBikes) {
			if (bike.getRenter() != null) {
				result.add(bike);
			}
		}
		return result;
		//	return allBikes.stream().filter(bike -> bike.getRenter() != null).collect(Collectors.toList());
	}

	/**
	 * @return the bikes that are close to a station (within 30m), but still are rented
	 */
	private Collection<Bike> getUnreturnedBikes() {
		return getRentedBikes().stream().filter(bike -> getStationNearby(bike, 30.0) != null).collect(Collectors.toList());
	}

	/**
	 * Called when a person starts renting a bike by taking it from a station.
	 * Checks the arguments before registering all necessary info of the rental.
	 * @param person
	 * @param now the start time of the rental
	 * @param returnTime the expected return time
	 * @throws (some subclass of) RuntimeException if the now isn't before returnTime
	 * @throws (some subclass of) RuntimeException if the bike isn't available for rental
	 */
	public void rentBike(final Person person, final Bike bike, final LocalDateTime now, final LocalDateTime returnTime) {
		checkNowIsBeforeReturnTime(now, returnTime);
		if (bike.getRenter() != null) {
			throw new IllegalArgumentException(bike + " is currently being rented");
		}
		bike.setRenter(person);
		bike.addRentalInfo(new RentalInfo(now, returnTime));
	}

	private void checkNowIsBeforeReturnTime(final LocalDateTime now, final LocalDateTime returnTime) {
		if (now.compareTo(returnTime) >= 0) {
			throw new IllegalArgumentException("The start time, " + now + " is the same as or after the return time, " + returnTime);
		}
	}

	/**
	 * Called when a person extends an ongoing bike rental.
	 * Checks the arguments before registering all necessary info of the rental extension.
	 * @param person
	 * @param bike
	 * @param now the time the extension starts
	 * @param returnTime the (new) expected return time
	 * @throws (some subclass of) RuntimeException if the now isn't before returnTime
	 * @throws (some subclass of) RuntimeException if the bike isn't currently being rented
	 * @throws (some subclass of) RuntimeException if person isn't currently renting the bike
	 */
	public void extendRental(final Person person, final Bike bike, final LocalDateTime now, final LocalDateTime returnTime) {
		checkNowIsBeforeReturnTime(now, returnTime);
		if (bike.getRenter() != person) {
			throw new IllegalArgumentException(bike + " isn't currently being rented by " + person);
		}
		bike.addRentalInfo(new RentalInfo(now, returnTime));
	}

	/**
	 * Called when a person returns a bike.
	 * Checks the arguments, computes the price, performs the payment and clears the rental info.
	 * Note that if the payment isn't completed, the rental info should not be cleared.
	 * @param person
	 * @param bike
	 * @param now the time the bike is returned
	 * @throws (some subclass of) RuntimeException if the bike isn't currently being rented by the person argument
	 * @throws (some subclass of) RuntimeException if person isn't near (within 30m of) a station
	 */
	public void returnBike(final Person person, final Bike bike, final LocalDateTime now) {
		if (bike.getRenter() != person) {
			throw new IllegalArgumentException(bike + " isn't currently being rented by " + person);
		}
		final GeoLocation place = getStationNearby(bike, 30.0);
		if (place == null) {
			throw new IllegalArgumentException(bike + " isn't near (enough) a bike place");
		}
		person.withdraw(computePrice(person, bike, now));
		bike.setRenter(null);
		bike.clearRentalInfos();
	}

	private PricePolicy pricePolicy;

	private int computePrice(final Person person, final Bike bike, final LocalDateTime returnTime) {
		PricePolicy pricePolicy = person.getPricePolicy();
		if (pricePolicy == null) {
			pricePolicy = this.pricePolicy;
		}
		final int price = pricePolicy.computePrice(person, bike, returnTime);
		return price;
	}
}
