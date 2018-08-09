package kont2018.farkle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Represents a round of Farkle, where a player throws and keeps dice,
 * until s/he either "bank", i.e. save your points, or
 * farkle, i.e. get no points in a throw and hence looses all gathered points.
 * During and after a finished round, kept sets of dice and their scores are available.
 * During a round, the remaining Dice is also available.
 */
public class FarkleRound implements Iterable<DiceScore> {

	private final int dieCount;
	private final FarkleScoring scoring;

	private final Collection<DiceScore> kept = new ArrayList<>();
	private Dice currentDice;

	FarkleRound(final int dieCount, final FarkleScoring scoring) {
		this.dieCount = dieCount;
		this.scoring = scoring;
		roll(dieCount);
	}

	/**
	 * Support iteration over the set of kept Dice
	 */
	@Override
	public Iterator<DiceScore> iterator() {
		return kept.iterator();
	}

	/**
	 * @return current or remaining Dice, null if the round is finished
	 */
	public Dice getCurrentDice() {
		return currentDice;
	}

	private void roll(final int dieCount) {
		this.currentDice = new Dice(new RandomValuesIterator(dieCount));
		if (scoring.computeDiceScores(currentDice).isEmpty()) {
			this.kept.clear();
			this.currentDice = null;
		}
	}

	/**
	 * @return true of the round is finised, false otherwise
	 */
	public boolean isFinished() {
		return this.currentDice == null;
	}

	/**
	 * @return the (current) score, as the sum of score of the kept dice
	 */
	public int getScore() {
		int sum = 0;
		for (final DiceScore diceScore : kept) {
			sum += diceScore.getScore();
		}
		return sum;
		//		return kept.stream().mapToInt(Dice::getScore).sum();
	}

	/**
	 * Called when the player decides to stop and secure points.
	 * Finishes the round, by keeping all scoring Dice, as computed by the scoring object.
	 */
	public void bank() {
		if (isFinished()) {
			throw new IllegalStateException("Game has ended");
		}
		this.kept.addAll(scoring.computeDiceScores(currentDice));
		this.currentDice = null;
	}

	/**
	 * Called when the player decides to keep specific dice and roll the rest. All the dice kept must be
	 * scoring ones, but not all scoring dice need to be kept.
	 * @param dice the dice to keep, the rest of the current dice should be thrown.
	 * @throws IllegalStateException if the round has already finished
	 * @throws IllegalArgumentException if the dice in the argument are not contained in the current dice.
	 * @throws IllegalArgumentException if you not all dice are scoring (as computed by the scoring object).
	 */
	public void keepAndRoll(final Dice dice) {
		if (isFinished()) {
			throw new IllegalStateException("Round already finished");
		}
		if (! currentDice.contains(dice)) {
			throw new IllegalArgumentException("Dice argument is not contained in the current dice");
		}
		final Collection<DiceScore> scores = scoring.computeDiceScores(dice);
		if (scores.stream().map(DiceScore::getDice).mapToInt(Dice::getDieCount).sum() != dice.getDieCount()) {
			throw new IllegalArgumentException("You can only set aside dice with a score");
		}
		kept.addAll(scores);
		currentDice = currentDice.remove(dice);
		final int dieCount2 = currentDice.getDieCount();
		roll(dieCount2 == 0 ? this.dieCount : dieCount2);
	}
}
