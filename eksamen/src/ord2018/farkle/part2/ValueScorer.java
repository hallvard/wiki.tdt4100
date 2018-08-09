package ord2018.farkle.part2;

import java.util.Collections;

public class ValueScorer implements DiceScorer {

	private final int value, score;

	public ValueScorer(final int value, final int score) {
		this.value = value;
		this.score = score;
	}

	@Override
	public Dice getScore(final Dice dice) {
		if (dice.getValueCount(value) <= 0) {
			return null;
		}
		return new DiceImpl1(Collections.singleton(value), score);
	}
}
