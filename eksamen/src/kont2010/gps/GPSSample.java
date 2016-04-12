package kont2010.gps;

public class GPSSample {

	private final GPSPoint point;
	private final long timestamp;
	
	public GPSSample(GPSPoint point, long timestamp) {
		this.point = point;
		this.timestamp = timestamp;
	}
	public GPSSample(double v1, double v2, long timestamp) {
		this(new GPSPoint(v1, v2), timestamp);
	}

	public GPSPoint getGPSPoint() {
		return point;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public double distance(GPSPoint pt) {
		return GPSUtil.distance(this.point, pt);
	}

	public double distance(GPSSample sample) {
		return GPSUtil.distance(this.point, sample.point);
	}
}
