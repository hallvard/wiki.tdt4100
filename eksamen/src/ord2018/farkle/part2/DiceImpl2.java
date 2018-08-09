package ord2018.farkle.part2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Represents a set of die values.
 */
public class DiceImpl2 extends AbstractDiceImpl implements Dice {

	private final int[] valueCounters;

	/**
	 * Initializes this Dice with the values in valueCounters, and a score.
	 * @param valueCounters
	 * @param score the score to set, may be -1 for yet unknown
	 */
	public DiceImpl2(final int[] valueCounters, final int score) {
		super(score);
		this.valueCounters = valueCounters.clone();
	}

	/**
	 * Initializes this Dice with the values in dieValues, and a score.
	 * @param dieValues
	 * @param score the score to set, may be -1 for yet unknown
	 */
	public DiceImpl2(final Iterable<Integer> dieValues, final int score) {
		super(score);
		this.valueCounters = new int[6];
		for (final int dieValue : dieValues) {
			if (! (dieValue >= 1 && dieValue <= 6)) {
				throw new IllegalArgumentException("A die value must be in the range 1-6");
			}
			this.valueCounters[dieValue - 1]++;
		}
	}

	/**
	 * Initializes this Dice with dieCount random values (using Math.random())
	 * @param dieCount
	 * @param valueSupplier
	 */
	public DiceImpl2(final int dieCount) {
		this(Dice.randomDieValues(dieCount), -1);
	}

	/**
	 * Initializes this Dice with the values in dieValues, and a score
	 * @param dieValues
	 * @param score the score to set, may be -1 for yet unknown
	 */
	public DiceImpl2(final Dice dice, final int score) {
		this((Iterable<Integer>) dice, score);
	}

	/**
	 * Parses a string using the toString format (see above) and returns a corresponding Dice.
	 * @param s
	 * @return a new Dice instance initialized with die values and score from the String argument
	 */
	public static DiceImpl2 valueOf(String s) {
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
		return new DiceImpl2(dieValues, score);
	}

	/**
	 * @return the number of die values
	 */
	@Override
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
	@Override
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
	@Override
	public int getValueCount(final int value) {
		return valueCounters[value - 1];
	}

	/**
	 * @param dices a Collection of Dice
	 * @return a new Dice instance with the all the die values this Dice and all Dice in the argument
	 */
	@Override
	public Dice add(final Dice dice) {
		final DiceImpl2 result = new DiceImpl2(Collections.emptyList(), -1);
		for (int dieValue = 1; dieValue <= 6; dieValue++) {
			result.valueCounters[dieValue - 1] += this.getValueCount(dieValue);
			result.valueCounters[dieValue - 1] += dice.getValueCount(dieValue);
		}
		return result;
	}

	/**
	 * @param dice
	 * @return true if all die values in the argument appear in this Dice
	 */
	@Override
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
	 * @return a new Dice instance with the die values from this Dice, but without those from the argument
	 */
	@Override
	public Dice remove(final Dice dice) {
		final DiceImpl2 result = new DiceImpl2(Collections.emptyList(), -1);
		for (int dieValue = 1; dieValue <= 6; dieValue++) {
			result.valueCounters[dieValue - 1] = Math.max(0, this.valueCounters[dieValue - 1] - dice.getValueCount(dieValue));
		}
		return result;
	}
}
