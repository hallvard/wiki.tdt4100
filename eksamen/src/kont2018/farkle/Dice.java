package kont2018.farkle;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Represents a set of die values.
 */
public class Dice implements Iterable<Integer> {

	/**
	 * Counters for each possible die value.
	 * The counter at index i (0-5) is the counter for the die value i+1 (1-6).
	 * I.e. the value at index 2 is the counter for die value 3.
	 */
	private final int[] valueCounters;

	/**
	 * Initializes this Dice with the values in dieValues.
	 * @param dieValues sequence of die values, not counter values
	 */
	public Dice(final Iterator<Integer> dieValues) {
		this.valueCounters = new int[6];
		while (dieValues.hasNext()) {
			final int dieValue = dieValues.next();
			if (! (dieValue >= 1 && dieValue <= 6)) {
				throw new IllegalArgumentException("A die value must be in the range 1-6");
			}
			this.valueCounters[dieValue - 1]++;
		}
	}

	/**
	 * Initializes this Dice with the values in dieValues.
	 * @param dieValues sequence of die values, not counter values
	 */
	public Dice(final Iterable<Integer> dieValues) {
		this(dieValues.iterator());
	}

	@Override
	public Iterator<Integer> iterator() {
		return new DiceIterator(this);
	}

	/**
	 * @return the number of die values
	 */
	public int getDieCount() {
		int count = 0;
		for (final int counter : valueCounters) {
			count += counter;
		}
		return count;
	}

	/**
	 * @param dieNum
	 * @return the value of die number dieNum
	 */
	public int getDieValue(int dieNum) {
		// throw IllegalArgumentException?
		if (dieNum < 0 || dieNum >= getDieCount()) {
			throw new IllegalArgumentException(dieNum + " is out of range");
		}
		for (int dieValue = 1; dieValue <= 6; dieValue++) {
			final int counter = valueCounters[dieValue - 1];
			if (dieNum < counter) {
				return dieValue;
			}
			dieNum -= counter;
		}
		// should never come here
		throw new IllegalArgumentException();
	}

	/**
	 * @param value
	 * @return the number of dice with the provided value
	 */
	public int getValueCount(final int value) {
		return valueCounters[value - 1];
	}

	/**
	 * @param dices a Collection of Dice
	 * @return a new Dice instance with the all the die values this Dice and all Dice in the argument
	 */
	public Dice add(final Dice dice) {
		final Dice result = new Dice(Arrays.asList());
		for (int dieValue = 1; dieValue <= 6; dieValue++) {
			result.valueCounters[dieValue - 1] = this.valueCounters[dieValue - 1] + dice.valueCounters[dieValue - 1];
		}
		return result;
	}

	/**
	 * @param dice
	 * @return true if all die values in the argument appear in this Dice
	 */
	public boolean contains(final Dice dice) {
		for (int dieValue = 1; dieValue <= 6; dieValue++) {
			if (this.valueCounters[dieValue - 1] < dice.getValueCount(dieValue)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param dice
	 * @return true if this Dice and the one provided have exactly the same die values
	 */
	public boolean isSame(final Dice dice) {
		return contains(dice) && dice.contains(this);
	}

	/**
	 * @param dice
	 * @return a new Dice instance with the die values from this Dice, but without those from the argument
	 */
	public Dice remove(final Dice dice) {
		final Dice result = new Dice(Arrays.asList());
		for (int dieValue = 1; dieValue <= 6; dieValue++) {
			result.valueCounters[dieValue - 1] = Math.max(0, this.valueCounters[dieValue - 1] - dice.getValueCount(dieValue));
		}
		return result;
	}
}
