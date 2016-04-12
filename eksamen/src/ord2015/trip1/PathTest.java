package ord2015.trip1;

import junit.framework.TestCase;

public class PathTest extends TestCase {

	private static final double S1 = 20000.0, S2 = 80000.0, S3 = 10000.0;
	private static final double T1 = 1200.0, T2 = 3600.0, T3 = 550.0;
	private Segment SEG1, SEG2, SEG3, segments[];
	private Path path;
	
	@Override
	protected void setUp() throws Exception {
		segments = new Segment[]{
			SEG1 = new Segment(S1, T1),
			SEG2 = new Segment(S2, T2),
			SEG3 = new Segment(S3, T3)
		};
		path = new Path(segments);
	}
	
	public void testGetSegmentCount() {
		assertEquals(3, path.getSegmentCount());
	}
	
	public void testGetSegment() {
		assertEquals(SEG1, path.getSegment(0));
		assertEquals(SEG2, path.getSegment(1));
		assertEquals(SEG3, path.getSegment(2));
	}
	
	public void testGetDuration() {
		assertEquals(T1 + T2 + T3, path.getDuration());
	}
	
	public void testGetDistance() {
		assertEquals(S1 + S2, path.getDistance(SEG1, SEG3));
		assertEquals(S1, path.getDistance(null, SEG2));
		assertEquals(S2 + S3, path.getDistance(SEG2, null));
		assertEquals(S1 + S2 + S3, path.getDistance(null, null));
	}
	
	public void testGetSegmentAt() {
		assertEquals(null, path.getSegmentAt(0.0, false));
		assertEquals(SEG1, path.getSegmentAt(0.0, true));

		assertEquals(SEG1, path.getSegmentAt(S1, false));
		assertEquals(SEG2, path.getSegmentAt(S1, true));
		
		assertEquals(SEG2, path.getSegmentAt(S1 + S2 / 2, false));
		assertEquals(SEG2, path.getSegmentAt(S1 + S2 / 2, true));

		assertEquals(SEG2, path.getSegmentAt(S1 + S2, false));
		assertEquals(SEG3, path.getSegmentAt(S1 + S2, true));
		
		assertEquals(SEG3, path.getSegmentAt(S1 + S2 + S3 / 2, false));
		assertEquals(SEG3, path.getSegmentAt(S1 + S2 + S3 / 2, true));
		
		assertEquals(SEG3, path.getSegmentAt(S1 + S2 + S3, false));
		assertEquals(null, path.getSegmentAt(S1 + S2 + S3, true));

		assertEquals(null, path.getSegmentAt(S1 + S2 + S3 + 1, false));
	}
}
