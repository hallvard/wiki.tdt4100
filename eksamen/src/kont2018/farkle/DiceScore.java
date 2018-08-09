package kont2018.farkle;

/**
 * Represents the score given to a particular Dice object
 */
public class DiceScore {

	private final Dice dice;
	private final int score;

	/**
	 * Initialises a DiceScore object with the provided data
	 * @param allDice the Dice object for which point are given
	 * @param dice the Dice object containing
	 * only the die values contibuting to the score
	 * @param score the score itself
	 */
	public DiceScore(final Dice dice, final int score) {
		this.dice = dice;
		this.score = score;
	}

	public Dice getDice() {
		return dice;
	}

	public int getScore() {
		return score;
	}
}
