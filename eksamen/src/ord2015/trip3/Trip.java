package ord2015.trip3;

import ord2015.trip1.Path;
import ord2015.trip1.Segment;

public class Trip extends AbstractTrip {

	protected Trip(Path path) {
		super(path);
	}

	@Override
	public double estimateTime(double distance, double duration) {
		double remainingTime = 0.0;
		for (Segment segment : path) {
			distance -= segment.getDistance();
			if (distance < 0) {
				remainingTime = segment.getSpeed() * (- distance);
			} else if (remainingTime > 0.0) {
				remainingTime += segment.getDuration();
			}
		}
		return remainingTime;
	}
}
