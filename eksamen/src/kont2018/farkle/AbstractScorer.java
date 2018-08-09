package kont2018.farkle;

/**
 * Abstract implementation of DiceScorer that stores a specific score.
 * Such a score is typically used when the score does not depend on the die values.
 */
public abstract class AbstractScorer implements DiceScorer {

	private final int score;

	/**
	 * Initializes this AbstractScorer with the specific score.
	 * @param score
	 */
	protected AbstractScorer(final int score) {
		this.score = score;
	}

	protected int getScore() {
		return score;
	}
}
