package kont2019.bike;

import java.time.LocalDateTime;

public class RentalInfo {

	private final LocalDateTime startTime;
	private final LocalDateTime endTime;

	public RentalInfo(final LocalDateTime startTime, final LocalDateTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}
}
