package kont2018.farkle;

/**
 * Implementation of DiceScorer that gives a specific score when no other
 * DiceScore object applies. Requires that a certain number of dice have been
 * thrown.
 */
public class NothingScorer extends AbstractScorer {

	private final int numDice;
	private final DiceScorer[] scorers;

	/**
	 * Initializes this NothingScorer with the minimum required number of dice,
	 * the specific score given and the other DiceScorer objects.    
	 * @param numDice the min. number of dice required for this rule to apply    
	 * @param score the specific score to give
	 * @param diceScorers the (other) rules to check    
	 */
	public NothingScorer(final int numDice, final int score, final DiceScorer... diceScorers) {
		super(score);
		this.numDice = numDice;
		this.scorers = diceScorers;
	}

	/**
	 * Checks that no (other) DiceScore object applies,
	 * in case a specific score is given.
	 */
	@Override
	public DiceScore getScore(final Dice dice) {
		if (dice.getDieCount() >= numDice) {
			for (final DiceScorer diceScorer : scorers) {
				if (diceScorer.getScore(dice) != null) {
					return null;
				}
			}
		}
		return new DiceScore(dice, getScore());
	}
}
