package ord2018.farkle.part2;

public class StraightScorer implements DiceScorer {

	private final int score;

	public StraightScorer(final int score) {
		this.score = score;
	}

	@Override
	public Dice getScore(final Dice dice) {
		int first = 0, last = 0;
		for (int i = 1; i <= 6; i++) {
			final int count = dice.getValueCount(i);
			if (count > 1) {
				return null;
			} else if (count == 1) {
				if (first == 0) {
					first = i;
				}
				last = i;
			} else if (last > 0) {
				break;
			}
		}
		if (last - first + 1 != dice.getDieCount()) {
			return null;
		}
		return new DiceImpl1(dice, score);
	}
}
