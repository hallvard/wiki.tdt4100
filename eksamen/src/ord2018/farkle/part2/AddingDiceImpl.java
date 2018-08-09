package ord2018.farkle.part2;

public class AddingDiceImpl extends AbstractDiceImpl {

	private final Dice dice1, dice2;

	public AddingDiceImpl(final Dice dice1, final Dice dice2, final int score) {
		super(score);
		this.dice1 = dice1;
		this.dice2 = dice2;
	}

	public AddingDiceImpl(final Dice dice1, final Dice dice2) {
		this(dice1, dice2, -1);
	}

	@Override
	public int getDieCount() {
		return dice1.getDieCount() + dice2.getDieCount();
	}

	@Override
	public int getDieValue(final int dieNum) {
		final int dieCount1 = dice1.getDieCount();
		return (dieNum < dieCount1 ? dice1.getDieValue(dieNum) : dice2.getDieValue(dieNum - dieCount1));
	}

	@Override
	public int getValueCount(final int value) {
		return dice1.getValueCount(value) + dice2.getValueCount(value);
	}

	@Override
	public Dice add(final Dice dice) {
		return new AddingDiceImpl(this, dice, -1);
	}

	@Override
	public boolean contains(final Dice dice) {
		for (int dieValue = 1; dieValue <= 6; dieValue++) {
			if (dice.getValueCount(dieValue) > getValueCount(dieValue)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Dice remove(final Dice dice) {
		final int[] valueCounters = new int[6];
		for (int dieValue = 1; dieValue <= 6; dieValue++) {
			valueCounters[dieValue - 1] = Math.max(0, this.getValueCount(dieValue) - dice.getValueCount(dieValue));
		}
		return new DiceImpl2(valueCounters, -1);
	}
}
