package ord2015.trip2;

import ord2015.trip1.Path;
import ord2015.trip1.Segment;

public class DefaultTimeEstimator implements TimeEstimator {

	@Override
	public double estimateTime(Path path, Path actualPath, double distance, double duration) {
		double time = 0.0;
		for (Segment segment : path) {
			distance -= segment.getDistance();
			if (distance < 0) {
				time = segment.getSpeed() * (- distance);
			} else if (time > 0.0) {
				time += segment.getDuration();
			}
		}
		return time;
	}

}
