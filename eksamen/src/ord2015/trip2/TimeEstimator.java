package ord2015.trip2;

import ord2015.trip1.Path;

public interface TimeEstimator {
	public double estimateTime(Path path, Path actualPath, double distance, double duration);
}
