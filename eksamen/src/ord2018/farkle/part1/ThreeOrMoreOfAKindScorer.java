package ord2018.farkle.part1;

import java.util.ArrayList;
import java.util.Collection;

public class ThreeOrMoreOfAKindScorer implements DiceScorer {

	private int getScore(final int value, int count) {
		int score = value * 100;
		while (count > 3) {
			score = score * 2;
			count--;
		}
		return score;
		// same as
		// return value * 100 * (int) Math.pow(count - 3, 2);
	}

	protected int getBestValue(final Dice dice) {
		int value = 0, max = 0;
		for (int i = 1; i <= 6; i++) {
			final int count = dice.getValueCount(i);
			final int score = getScore(i, count);
			if (count >= 3 && score > max) {
				value = i;
				max = score;
			}
		}
		return value;
	}

	@Override
	public Dice getScore(final Dice dice) {
		final int best = getBestValue(dice);
		if (best == 0) {
			return null;
		}
		final int count = dice.getValueCount(best);
		final Collection<Integer> dieValues = new ArrayList<Integer>(count);
		for (int i = 0; i < count; i++) {
			dieValues.add(best);
		}
		return new Dice(dieValues, getScore(best, count));
	}
}
