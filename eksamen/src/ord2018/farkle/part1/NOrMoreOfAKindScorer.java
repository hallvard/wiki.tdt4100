package ord2018.farkle.part1;

import java.util.ArrayList;
import java.util.Collection;

public class NOrMoreOfAKindScorer implements DiceScorer {

	private final int n;
	private final boolean doubling;

	public NOrMoreOfAKindScorer(final int n, final boolean doubling) {
		this.n = n;
		this.doubling = doubling;
	}

	private int getBaseScore(final int value) {
		return value == 1 ? 1000 : value * 100;
	}

	private int getScore(final int value, int count) {
		final int baseScore = getBaseScore(value);
		int score = baseScore;
		while (count > n) {
			score = (doubling ? score * 2 : score + baseScore);
			count--;
		}
		return score;
		// same as
		// return baseScore * (doubling ? (int) Math.pow(count - n, 2) : count - n + 1);
	}

	protected int getBestValue(final Dice dice) {
		int value = 0, max = 0;
		for (int i = 1; i <= 6; i++) {
			final int count = dice.getValueCount(i);
			final int score = getScore(i, count);
			if (count >= n && score > max) {
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
