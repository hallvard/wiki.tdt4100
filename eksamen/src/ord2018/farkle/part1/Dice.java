package ord2018.farkle.part1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

/**
 * Represents a set of die values. A die has six possible values 1-6,
 * but the number of dice may vary from Dice instance to Dice instance.
 */
public class Dice implements Iterable<Integer> {

	public static Collection<Integer> dieValues(final int dieCount, final Supplier<Integer> valueSupplier) {
		final Collection<Integer> dieValues = new ArrayList<>(dieCount);
		while (dieValues.size() < dieCount) {
			dieValues.add(valueSupplier.get());
		}
		return dieValues;
	}

	public final static Supplier<Integer> RANDOM_DIE_VALUE_SUPPLIER = () -> (int) (Math.random() * 6 + 1);

	/**
	 * @param dieCount
	 * @return a collection of random integer values in the range 1-6
	 */
	public static Collection<Integer> randomDieValues(final int dieCount) {
		return dieValues(dieCount, RANDOM_DIE_VALUE_SUPPLIER);
	}

	//

	private final List<Integer> dieValues = new ArrayList<>();
	private int score = -1;

	/**
	 * Initializes this Dice with the values in dieValues, and a score.
	 * @param dieValues
	 * @param score the score to set, may be -1 for yet unknown
	 * @throws a suitable exception if the die values are outside the valid range
	 */
	public Dice(final Iterable<Integer> dieValues, final int score) {
		for (final int dieValue : dieValues) {
			if (! (dieValue >= 1 && dieValue <= 6)) {
				throw new IllegalArgumentException("A die value must be in the range 1-6");
			}
			this.dieValues.add(dieValue);
		}
		this.score = score;
	}

	/**
	 * Initializes this Dice with dieCount random values (using Math.random())
	 * @param dieCount
	 * @param valueSupplier
	 */
	public Dice(final int dieCount) {
		this(Dice.randomDieValues(dieCount), -1);
	}

	/**
	 * Initializes this Dice with the values in dieValues, and a score
	 * @param dieValues
	 * @param score the score to set, may be -1 for yet unknown
	 */
	public Dice(final Dice dice, final int score) {
		this((Iterable<Integer>) dice, score);
	}

	/**
	 * Format: [die1, die2, ...] = score (score is omitted when < 0)
	 */
	@Override
	public String toString() {
		String result = "[";
		for (final int dieValue : this) {
			if (result.length() > 1) {
				result += ", ";
			}
			result += dieValue;
		}
		result = result + "]";
		if (getScore() >= 0) {
			result += " = " + getScore();
		}
		return result;
	}

	/**
	 * Parses a string using the toString format (see above) and returns a corresponding Dice.
	 * @param s
	 * @return a new Dice instance initialized with die values and score from the String argument
	 */
	public static Dice valueOf(String s) {
		int score = -1;
		final int scorePos = s.indexOf("=");
		if (scorePos >= 0) {
			score = Integer.valueOf(s.substring(scorePos + 1).trim());
			s = s.substring(0, scorePos).trim();
		}
		if (s.startsWith("[") && s.endsWith("]")) {
			s = s.substring(1, s.length() - 1);
		} else {
			throw new IllegalArgumentException("Illegal format");
		}
		final String[] dieValueStrings = s.split(",");
		final Collection<Integer> dieValues = new ArrayList<>();
		for (int i = 0; i < dieValueStrings.length; i++) {
			dieValues.add(Integer.valueOf(dieValueStrings[i].trim()));
		}
		return new Dice(dieValues, score);
	}

	/**
	 * @return the current score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the score, but only if it isn't already set to a non-negative value
	 * @param score
	 * @throws a suitable exception if score already is set to a non-negative value
	 */
	public void setScore(final int score) {
		if (this.score >= 0) {
			throw new IllegalStateException("Cannot set score more than once");
		}
		this.score = score;
	}

	@Override
	public Iterator<Integer> iterator() {
		return this.dieValues.iterator();
	}

	/**
	 * @return the number of die values
	 */
	public int getDieCount() {
		return this.dieValues.size();
	}

	/**
	 * @param dieNum
	 * @return the value of die number dieNum
	 */
	public int getDieValue(final int dieNum) {
		// throw IllegalArgumentException?
		return this.dieValues.get(dieNum - 1);
	}

	/**
	 * @param value
	 * @return the number of dice with the provided value
	 */
	public int getValueCount(final int value) {
		int count = 0;
		for (final int dieValue : this.dieValues) {
			if (dieValue == value) {
				count++;
			}
		}
		return count;
		//		return (int) this.dieValues.stream().filter(die -> value == die).count();
	}

	/**
	 * @param dice
	 * @return true if all die values in the argument appear in this Dice
	 */
	public boolean contains(final Dice dice) {
		final List<Integer> dieValues = new ArrayList<>(this.dieValues);
		for (final int dieValue : dice) {
			final int pos = dieValues.indexOf(dieValue);
			if (pos < 0) {
				return false;
			}
			dieValues.remove(pos);
		}
		return true;
	}

	/**
	 * @param dices a Collection of Dice
	 * @return a new Dice instance with the all the die values this Dice and all Dice in the argument, without any specific order
	 */
	public Dice add(final Dice dice) {
		final Collection<Integer> dieValues = new ArrayList<>(this.dieValues);
		for (final int dieValue : dice) {
			dieValues.add(dieValue);
		}
		return new Dice(dieValues, -1);
	}

	/**
	 * Combines the die values in both this and the argument, into a new Dice instance.
	 * Note that the order of values in the result is not specified.
	 * @param dice
	 * @return a new Dice instance with the die values from this Dice, but without those from the argument, without any specific order
	 */
	public Dice remove(final Dice dice) {
		final List<Integer> dieValues = new ArrayList<>(this.dieValues);
		for (final int dieValue : dice) {
			final int pos = dieValues.indexOf(dieValue);
			if (pos >= 0) {
				dieValues.remove(pos);
			}
		}
		return new Dice(dieValues, -1);
	}
}
