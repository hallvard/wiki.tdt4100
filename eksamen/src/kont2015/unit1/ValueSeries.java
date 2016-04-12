package kont2015.unit1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * A class representing series of doubles, all with the same (unmodifiable) Unit.
 * A Values object can only be modified by appending another value.
 * @author hal
 *
 */
public class ValueSeries implements Values {

	private final Unit unit;
	private Collection<Double> values  = new ArrayList<Double>();

	/**
	 * Constructs a new ValueSeries object, with the provided Unit.
	 * @param unit
	 */
	public ValueSeries(Unit unit) {
		this.unit = unit;
	}

	/**
	 * Appends the provided double to this ValueSeries. The double value is assumed to be in the Unit of this ValueSeries.
	 * @param value the double to append
	 */
	public void appendValue(double value) {
		values.add(value);
		fireValuesChanged();
	}

	/**
	 * Appends the provided Value to this ValueSeries, by first converting it to this ValueSeries' unit and then appending it.
	 * @param value the Value to append, after converting it to this ValueSeries' Unit.
	 */
	public void appendValue(Value value) {
		appendValue(value.getUnit().convert(value.getValue(), getUnit()));
	}

	@Override
	public int size() {
		return values.size();
	}
	
	@Override
	public double average() {
		double sum = 0.0;
		for (double value : values) {
			sum += value;
		}
		return sum / values.size();
	}

	@Override
	public Unit getUnit() {
		return unit;
	}

	@Override
	public Iterator<Double> iterator() {
		return values.iterator();
	}
	
	//
	
	@Override
	public Values add(Values other) {
		Unit base = this.unit.findCommonBaseUnit(other.getUnit());
		ValueSeries result = new ValueSeries(base);
		Iterator<Double> otherDoubles = other.iterator();
		for (double value : values) {
			double otherDouble = otherDoubles.next();
			double sum = this.unit.convert(value, base) + other.getUnit().convert(otherDouble, base);
			result.appendValue(sum);
		}
		return result;
	}
	
	// ValuesListener support
	
	private Collection<ValuesListener> listeners = new ArrayList<ValuesListener>();

	@Override
	public void addValuesListener(ValuesListener listener) {
		listeners.add(listener);
	}
	
	@Override
	public void removeValuesListener(ValuesListener listener) {
		listeners.remove(listener);
	}

	protected void fireValuesChanged() {
		for (ValuesListener listener : listeners) {
			listener.valuesChanged(this);
		}
	}
}
