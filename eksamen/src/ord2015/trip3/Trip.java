package ord2015.trip3;

import ord2015.trip1.Path;
import ord2015.trip1.Segment;

public class Trip extends AbstractTrip {

	protected Trip(final Path path) {
		super(path);
	}

	@Override
	public double estimateTime(double distance, final double duration) {
		double remainingTime = 0.0;
		for (final Segment segment : path) {
			distance -= segment.getDistance();
			if (distance < 0) {
				remainingTime = -distance / segment.getSpeed();
			} else if (remainingTime > 0.0) {
				remainingTime += segment.getDuration();
			}
		}
		return remainingTime;
	}
}
