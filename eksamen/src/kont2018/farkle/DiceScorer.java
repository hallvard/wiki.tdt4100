package kont2018.farkle;

/**
 * Interface for scoring rules, i.e.
 * logic for computing a core for a subset of dice in a Dice
 */
public interface DiceScorer {

	/**
	 * Computes a score for (a subset of) the dice in the Dice argument.
	 * The returned DiceScore object store the provided Dice object,
	 * another Dice object with those values that contributed to the score and
	 * of course the score itself.
	 * @param dice
	 * @return The result of applying the rule to the provided Dice, or null of the rule doesn't apply.
	 */
	DiceScore getScore(Dice dice);
}
