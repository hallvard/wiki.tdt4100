package kont2019.bike;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class DefaultPricePolicy implements PricePolicy {

	protected int pricePrRental = 0, pricePrHour = 10, pricePrExtension = 5, pricePrLate = 10;

	protected void setPricePrRental(final int pricePrRental) {
		this.pricePrRental = pricePrRental;
	}

	protected void setPricePrHour(final int pricePrHour) {
		this.pricePrHour = pricePrHour;
	}

	protected void setPricePrExtension(final int pricePrExtension) {
		this.pricePrExtension = pricePrExtension;
	}

	protected void setPricePrLate(final int pricePrLate) {
		this.pricePrLate = pricePrLate;
	}

	@Override
	public int computePrice(final Person person, final Bike bike, final LocalDateTime returnTime) {
		final List<RentalInfo> rentalInfos = bike.getRentalInfos();
		int price = 0;
		LocalDateTime lastEndTime = null;
		for (final RentalInfo info : rentalInfos) {
			if (lastEndTime == null) {
				price = pricePrRental + pricePrHour * computeHours(info.getStartTime(), returnTime);
			} else if (info.getEndTime().compareTo(lastEndTime) > 0) {
				price += pricePrExtension;
				if (info.getStartTime().compareTo(lastEndTime) > 0) {
					price += pricePrLate;
				}
			}
			lastEndTime = info.getEndTime();
		}
		if (returnTime.compareTo(lastEndTime) > 0) {
			price += pricePrExtension;
			if (returnTime.compareTo(lastEndTime) > 0) {
				price += pricePrLate;
			}
		}
		return price;
	}

	private int computeHours(final LocalDateTime startTime, final LocalDateTime endTime) {
		return (int) Duration.between(startTime, endTime).toHours() + 1;
	}
}
