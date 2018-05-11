package ord2015.trip1;

public class Trip {

	private final Path path;

	public Trip(final Path path) {
		this.path = path;
	}

	private Path getExpectedPath() {
		return path;
	}

	private final Path actualPath = new Path();

	public void registerSegment(final double distance, final double duration) {
		if (actualPath.getSegmentCount() >= path.getSegmentCount()) {
			throw new IllegalArgumentException("Already registered enough segments");
		}
		final Segment pathSegment = path.getSegmentAt(distance, true);
		final double expectedDistance = path.getDistance(null, pathSegment);
		if (expectedDistance != distance) {
			throw new IllegalArgumentException("Illegal distance, should have been " + expectedDistance + ", but was " + distance);
		}
		final double segmentDistance = expectedDistance - path.getDistance(null, path.getSegmentAt(distance, false));
		final double segmentDuration = duration - actualPath.getDuration();
		actualPath.addSegment(segmentDistance, segmentDuration);
	}

	public double estimateTime(double distance, final double duration) {
		double remainingTime = 0.0;
		for (final Segment segment : this.getExpectedPath()) {
			distance -= segment.getDistance();
			// hvis vi har begynt å akkumulere
			if (remainingTime > 0.0) {
				// akkumuler
				remainingTime += segment.getDuration();
			} // hvis turen har kommet til dette segmentet
			else if (distance < 0) {
				// begynn å akkumulere
				remainingTime = -distance / segment.getSpeed();
			}
		}
		return remainingTime;
	}
}
