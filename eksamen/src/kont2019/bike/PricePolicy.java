package kont2019.bike;

import java.time.LocalDateTime;

public interface PricePolicy {
	public int computePrice(Person person, Bike bike, LocalDateTime returnTime);
}
