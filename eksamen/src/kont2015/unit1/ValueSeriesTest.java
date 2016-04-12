package kont2015.unit1;

import junit.framework.TestCase;

public class ValueSeriesTest extends TestCase implements ValuesListener {

	private Unit m, km;
	private ValueSeries values;
	
	protected void setUp() throws Exception {
		super.setUp();
		m = new Unit("m");
		km = new Unit("km", m, 1000.0);
		values = new ValueSeries(m);
		values.addValuesListener(this);
	}

	public void testAverage() {
		values.appendValue(11);
		values.appendValue(new Value(km, 0.1));
		assertEquals((11.0 + 100.0) / 2, values.average());
	}

	public void testValuesChanged() {
		values.appendValue(11);
		assertEquals(values, notified);
		notified = null;
		values.removeValuesListener(this);
		values.appendValue(12);
		assertEquals(null, notified);
	}
	
	private Values notified = null;
	
	@Override
	public void valuesChanged(Values values) {
		notified = values;
	}
}
