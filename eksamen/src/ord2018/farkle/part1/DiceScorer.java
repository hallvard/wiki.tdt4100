package ord2018.farkle.part1;

import java.util.function.Predicate;

/**
 * Interface for scoring rules, i.e.
 * logic for computing a score for a subset of dice in a Dice
 */
public interface DiceScorer extends Predicate<Dice> {

	/**
	 * Computes a score for (a subset of) the dice in the Dice argument.
	 * The return value includes those dice that gives the score, and
	 * of course the score itself.
	 * @param dice
	 * @return The dice for which the rule computes a score, with the score set, or null if the dice don't match the rule.
	 */
	Dice getScore(Dice dice);

	@Override
	default boolean test(final Dice dice) {
		return getScore(dice) != null;
	}
}
