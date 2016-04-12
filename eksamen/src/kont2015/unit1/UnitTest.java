package kont2015.unit1;

import junit.framework.TestCase;

public class UnitTest extends TestCase {

	private Unit m, km, dm, cm;
	private Unit kelwin, celcius, fahrenheit;
	private final static double ZERO_CELSIUS = 273.15;
	
	protected void setUp() throws Exception {
		super.setUp();
		m = new Unit("m");
		km = new Unit("km", m, 1000.0);
		dm = new Unit("dm", m, 0.1);
		cm = new Unit("cm", dm, 0.1);
		
		kelwin = new Unit("K");
		celcius = new Unit("C", kelwin, 1.0, ZERO_CELSIUS);
		fahrenheit = new Unit("F", celcius, 1 / 1.8, - 32.0 / 1.8);
	}

	public void testIllegalSymbol() {
		try {
			new Unit("10%");
			fail();
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			fail();
		}
	}
	
	public void testConvert() {
		assertEquals(2000.0, km.convert(2, m));
		assertEquals(0.2, m.convert(200, km));
		assertEquals(0.1, cm.convert(10000, km));
		assertEquals(0.1, cm.convert(10, m));
		
		assertEquals(ZERO_CELSIUS + 10, celcius.convert(10, kelwin));
		assertEquals(50.0, kelwin.convert(ZERO_CELSIUS + 10, fahrenheit));
	}
	
	public void testValueOf() {
		assertEquals(2000.0, Unit.valueOf("km").convert(2, Unit.valueOf("m")));
		assertEquals(0.2, Unit.valueOf("m").convert(200, Unit.valueOf("km")));
		assertEquals(0.1, Unit.valueOf("cm").convert(10000, Unit.valueOf("km")));
		assertEquals(0.1, Unit.valueOf("cm").convert(10, Unit.valueOf("m")));
	}
}
