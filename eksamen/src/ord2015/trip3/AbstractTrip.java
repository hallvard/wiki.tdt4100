package ord2015.trip3;

import ord2015.trip1.Path;
import ord2015.trip1.Segment;

public abstract class AbstractTrip {

	protected final Path path;
	
	protected AbstractTrip(Path path) {
		this.path = path;
	}

	protected Path actualPath = new Path();

	public void registerSegment(double distance, double duration) {
		if (actualPath.getSegmentCount() >= path.getSegmentCount()) {
			throw new IllegalArgumentException("Already registered enough segments");
		}
		Segment pathSegment = path.getSegmentAt(distance, true);
		double expectedDistance = path.getDistance(null, pathSegment);
		if (expectedDistance != distance) {
			throw new IllegalArgumentException("Illegal distance, should have been " + expectedDistance + ", but was " + distance);
		}
		double segmentDistance = expectedDistance - path.getDistance(null, path.getSegmentAt(distance, false));
		double segmentDuration = duration - actualPath.getDuration();
		actualPath.addSegment(segmentDistance, segmentDuration);
	}
	
	public abstract double estimateTime(double distance, double duration);
}
