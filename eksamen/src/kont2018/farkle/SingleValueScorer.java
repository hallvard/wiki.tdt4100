package kont2018.farkle;

import java.util.Arrays;

/**
 * Implementation of DiceScorer that gives a specific score til a specific die value.
 * In Farkle it is used for giving 50 to fives and 100 to ones.
 */
public class SingleValueScorer extends AbstractScorer {

	private final int value;

	/**
	 * Initializes this ValueScorer with the (die) value and the corresponding score.
	 * In Farkle you will typically create two of these, with
	 * new ValueScorer(5, 50) and new ValueScorer(1, 100)
	 * @param value
	 * @param score
	 */
	public SingleValueScorer(final int value, final int score) {
		super(score);
		this.value = value;
	}

	/**
	 * Looks for at least one of the specific value and
	 * if found, returns a DiceScore object with the dice itself,
	 * another Dice with the value contributing to the score and
	 * the corresponding score.
	 */
	@Override
	public DiceScore getScore(final Dice dice) {
		if (dice.getValueCount(value) > 0) {
			return new DiceScore(new Dice(Arrays.asList(value)), getScore());
		}
		return null;
	}
}
