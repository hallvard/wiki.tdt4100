package ord2018.farkle.part2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;

/**
 * Represents a set of die values.
 */
public interface Dice extends Iterable<Integer> {

	public static Collection<Integer> dieValues(final int dieCount, final Supplier<Integer> valueSupplier) {
		final Collection<Integer> dieValues = new ArrayList<>(dieCount);
		while (dieValues.size() < dieCount) {
			dieValues.add(valueSupplier.get());
		}
		return dieValues;
	}

	public final static Supplier<Integer> RANDOM_DIE_VALUE_SUPPLIER = () -> (int) (Math.random() * 6 + 1);

	public static Collection<Integer> randomDieValues(final int dieCount) {
		return dieValues(dieCount, RANDOM_DIE_VALUE_SUPPLIER);
	}

	/**
	 * Format: [die1, die2, ...] = score (score is omitted when < 0)
	 */
	@Override
	public String toString();

	/**
	 * @return the current score
	 */
	public int getScore();

	/**
	 * Sets the score, but only if it isn't already set to a non-negative value
	 * @param score
	 * @throws a suitable exception if score already is set to a non-negative value
	 */
	public void setScore(int score);

	/**
	 * @Return an Iterator for the die values
	 */
	@Override
	public Iterator<Integer> iterator();

	/**
	 * @return the number of die values
	 */
	public int getDieCount();

	/**
	 * @param dieNum
	 * @return the value of die number dieNum
	 */
	public int getDieValue(final int dieNum);

	/**
	 * @param value
	 * @return the number of dice with the provided value
	 */
	public int getValueCount(final int value);

	/**
	 * @param dices a Collection of Dice
	 * @return a new Dice instance with the all the die values from this Dice and the Dice argument
	 */
	public Dice add(final Dice dice);

	/**
	 * @param dice
	 * @return true if all die values in the argument appear in this Dice
	 */
	public boolean contains(final Dice dice);

	/**
	 * @param dice
	 * @return a new Dice instance with the die values from this Dice, but without those from the argument
	 */
	public Dice remove(final Dice dice);
}
