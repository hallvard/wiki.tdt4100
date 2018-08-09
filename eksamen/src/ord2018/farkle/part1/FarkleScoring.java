package ord2018.farkle.part1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Handles computation of scores for a Dice
 */
public class FarkleScoring {

	private final Collection<DiceScorer> diceScorers;

	public FarkleScoring(final DiceScorer... diceScorers) {
		this.diceScorers = new ArrayList<>(Arrays.asList(diceScorers));
	}

	/**
	 * Computes a set of Dice with scores for the provided Dice.
	 * E.g. of the argument is a Dice with values 1, 1, 1, 5, 3,
	 * the result should be two Dice instances, [1, 1, 1] = 1000 and [5] = 50 (in toString format).
	 * @param dice
	 * @return the set of Dice with die values and corresponding scores.
	 */
	public Collection<Dice> computeDiceScores(Dice dice) {
		final Collection<Dice> result = new ArrayList<>();
		while (dice.getDieCount() > 0) {
			Dice best = null;
			for (final DiceScorer diceScorer : diceScorers) {
				final Dice score = diceScorer.getScore(dice);
				if (score != null && (best == null || score.getScore() > best.getScore())) {
					best = score;
				}
			}
			if (best == null) {
				break;
			} else {
				result.add(best);
				dice = dice.remove(best);
			}
		}
		return result;
	}
}
