package kont2018.farkle;

/**
 * Implementation of DiceScorer that gives a specific score til a so-called straight,
 * which is when all values give a series of consecutive values, e.g. 1, 2, 3.
 * All the die values must be used, so with six dice, the only possibility is 1, 2, 3, 4, 5, 6.
 * With five dice, there are two possibilities, 1, 2, 3, 4, 5 and 2, 3, 4, 5, 6.
 */
public class StraightScorer extends AbstractScorer {

	/**
	 * Initializes this StraightScorer with the specific score.
	 * @param score
	 */
	public StraightScorer(final int score) {
		super(score);
	}

	/**
	 * Checks that all die values in the provided Dice form a series of consecutive values.
	 * If this is the case returns a DiceScore object with the dice itself,
	 * a Dice with the values contributing to the score (necessarily all of them) and
	 * the corresponding score.
	 */
	@Override
	public DiceScore getScore(final Dice dice) {
		final int dieCount = dice.getDieCount();
		int start = 1;
		// find start of series
		while (start <= 6) {
			final int count = dice.getValueCount(start);
			if (count > 1) {
				return null;
			} else if (count == 1) {
				break;
			}
			start = start + 1;
		}
		// find end of series
		int end = start + 1;
		while (end <= 6) {
			final int count = dice.getValueCount(end);
			if (count > 1) {
				return null;
			} else if (count == 0) {
				break;
			}
			end = end + 1;
		}
		if (end - start != dieCount) {
			return null;
		}

		return new DiceScore(dice, getScore());
	}
}
