package kont2015.unit1;

/**
 * An interface representing a sequence of doubles, all with the same Unit.
 * Implementing classes may support adding and removing elements. If modifiable,
 * the class must support notifying ValuesListeners.
 * @author hal
 *
 */
public interface Values extends Iterable<Double> {

	/**
	 * @return this Values' Unit
	 */
	public Unit getUnit();

	/**
	 * @return the number of values in the sequence.
	 */
	public int size();
	
	/**
	 * Computes the average of this Values object's double values.
	 * @return the average as a double (implicitly in this Values' Unit).
	 */
	public double average();
	
	/**
	 * Creates a new Values object where each element is the sum of corresponding elements in this and the other Values object.
	 * @param other the other Values
	 * @return a new Values object that represents the sum of this and other.
	 */
	public Values add(Values other);
	
	// ValuesListener support

	/**
	 * Adds a ValuesListener to be notified of changes to this Values object. 
	 * @param listener
	 */
	public void addValuesListener(ValuesListener listener);

	/**
	 * Removes a previously added ValuesListener. 
	 * @param listener
	 */
	public void removeValuesListener(ValuesListener listener);
}
