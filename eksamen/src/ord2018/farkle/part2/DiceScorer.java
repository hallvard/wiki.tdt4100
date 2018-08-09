package ord2018.farkle.part2;

/**
 * Interface for scoring rules, i.e.
 * logic for computing a core for a subset of dice in a Dice
 */
public interface DiceScorer {

	/**
	 * Computes a score for (a subset of) the dice in the Dice argument.
	 * The return value includes those dice that gives the score, and
	 * of course the score itself.
	 * @param dice
	 * @return The dice for which the rule computes a score, and the score itself.
	 */
	Dice getScore(Dice dice);
}
