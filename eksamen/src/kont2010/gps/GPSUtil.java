package kont2010.gps;

public class GPSUtil {

	public static double distance(GPSPoint pt1, GPSPoint pt2) {
		double d1 = pt2.getV1() - pt1.getV1(), d2 = pt2.getV2() - pt1.getV2();
		return Math.sqrt(d1 * d1 + d2 * d2);
	}
}
