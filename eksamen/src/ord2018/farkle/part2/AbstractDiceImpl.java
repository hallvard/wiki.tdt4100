package ord2018.farkle.part2;

import java.util.Iterator;

/**
 * Represents a set of die values.
 */
public abstract class AbstractDiceImpl implements Dice {

	private int score = -1;

	protected AbstractDiceImpl(final int score) {
		this.score = score;
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
	 * @return the current score
	 */
	@Override
	public int getScore() {
		return score;
	}

	/**
	 * Sets the score, but only if it isn't already set to a non-negative value
	 * @param score
	 * @throws a suitable exception if score already is set to a non-negative value
	 */
	@Override
	public void setScore(final int score) {
		if (this.score >= 0) {
			throw new IllegalStateException("Cannot set score more than once");
		}
		this.score = score;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new DieValuesIterator(this);
	}
}
