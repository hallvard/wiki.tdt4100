package ord2015.trip1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Path implements Iterable<Segment> {

	private final List<Segment> segments;
	
	public Path(Segment... segments) {
		this.segments = new ArrayList<Segment>(Arrays.asList(segments));
	}

	public int getSegmentCount() {
		return segments.size();
	}

	// not actually used
	public Segment getSegment(int i) {
		return segments.get(i);
	}

	@Override
	public Iterator<Segment> iterator() {
		return segments.iterator();
	}
	
	public void addSegment(Segment segment) {
		segments.add(segment);
	}

	public void addSegment(double distance, double duration) {
		addSegment(new Segment(distance, duration));
	}

	//

	public double getDuration() {
		double duration = 0.0;
		for (Segment segment : segments) {
			duration += segment.getDuration();
		}
		return duration;
	}

	public double getDistance(Segment fromSegment, Segment uptoSegment) {
		if ((fromSegment == null || segments.contains(fromSegment)) &&
			(uptoSegment == null || segments.contains(uptoSegment))) {
			double distance = 0.0;
			for (Segment segment : segments) {
				if (segment == uptoSegment) {
					return distance;
				} else if (fromSegment == null || segment == fromSegment || distance > 0.0) {
					distance += segment.getDistance();
				}
			}
			return distance;
		} else {
			throw new IllegalArgumentException("Path doesn't contain both segments.");
		}
	}

	public Segment getSegmentAt(double distance, boolean next) {
		for (Segment segment : segments) {
			if (distance == 0.0) {
				return (next ? segment : null);
			}
			distance -= segment.getDistance();
			if (distance < 0.0 || (distance == 0.0 && (! next))) {
				return segment;
			}
		}
		return null;
	}
}
