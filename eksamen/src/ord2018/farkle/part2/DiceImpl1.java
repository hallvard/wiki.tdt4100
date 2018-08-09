package ord2018.farkle.part2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a set of die values.
 */
public class DiceImpl1 extends AbstractDiceImpl implements Dice {

	private final List<Integer> dieValues = new ArrayList<>();

	/**
	 * Initializes this Dice with the values in dieValues, and a score.
	 * @param dieValues
	 * @param score the score to set, may be -1 for yet unknown
	 */
	public DiceImpl1(final Iterable<Integer> dieValues, final int score) {
		super(score);
		for (final int dieValue : dieValues) {
			if (! (dieValue >= 1 && dieValue <= 6)) {
				throw new IllegalArgumentException("A die value must be in the range 1-6");
			}
			this.dieValues.add(dieValue);
		}
	}

	/**
	 * Initializes this Dice with dieCount random values (using Math.random())
	 * @param dieCount
	 * @param valueSupplier
	 */
	public DiceImpl1(final int dieCount) {
		this(Dice.randomDieValues(dieCount), -1);
	}

	/**
	 * Initializes this Dice with the values in dieValues, and a score
	 * @param dieValues
	 * @param score the score to set, may be -1 for yet unknown
	 */
	public DiceImpl1(final Dice dice, final int score) {
		this((Iterable<Integer>) dice, score);
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
		return new DiceImpl1(dieValues, score);
	}

	@Override
	public Iterator<Integer> iterator() {
		return this.dieValues.iterator();
	}

	/**
	 * @return the number of die values
	 */
	@Override
	public int getDieCount() {
		return this.dieValues.size();
	}

	/**
	 * @param dieNum
	 * @return the value of die number dieNum
	 */
	@Override
	public int getDieValue(final int dieNum) {
		// throw IllegalArgumentException?
		return this.dieValues.get(dieNum);
	}

	/**
	 * @param value
	 * @return the number of dice with the provided value
	 */
	@Override
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
	 * @param dices a Collection of Dice
	 * @return a new Dice instance with the all the die values this Dice and all Dice in the argument
	 */
	@Override
	public Dice add(final Dice dice) {
		final Collection<Integer> dieValues = new ArrayList<>(this.dieValues);
		for (final int dieValue : dice) {
			dieValues.add(dieValue);
		}
		return new DiceImpl1(dieValues, -1);
	}

	/**
	 * @param dice
	 * @return true if all die values in the argument appear in this Dice
	 */
	@Override
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
	 * @param dice
	 * @return a new Dice instance with the die values from this Dice, but without those from the argument
	 */
	@Override
	public Dice remove(final Dice dice) {
		final List<Integer> dieValues = new ArrayList<>(this.dieValues);
		for (final int dieValue : dice) {
			final int pos = dieValues.indexOf(dieValue);
			if (pos >= 0) {
				dieValues.remove(pos);
			}
		}
		return new DiceImpl1(dieValues, -1);
	}
}
