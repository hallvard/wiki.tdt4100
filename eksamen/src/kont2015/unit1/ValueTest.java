package kont2015.unit1;

import junit.framework.TestCase;

public class ValueTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testValueOf() {
		Value value = Value.valueOf("2.0m");
		checkValue(value, "m", 2.0);
	}

	protected void checkValue(Value value, String unit, double d) {
		assertEquals(Unit.valueOf(unit), value.getUnit());
		assertEquals(d, value.getValue());
	}
	
	public void testAdd() {
		Value value1 = Value.valueOf("2.0m"), value2 = Value.valueOf("3.5km");
		Value sum1 = value1.add(value2);
		checkValue(sum1, "m", 3502.0);
		Value sum2 = value2.add(value1);
		checkValue(sum2, "m", 3502.0);
	}
	
	public void testMult() {
		Value value = Value.valueOf("2.0m");
		Value product = value.mult(Math.PI);
		checkValue(product, "m", value.getValue() * Math.PI);
	}
}
