package kont2013;


public abstract class AbstractDice implements Dice {

	private DieRoller dieRoller;
	
	protected AbstractDice(DieRoller dieRoller) {
		this.dieRoller = dieRoller;
	}
	protected AbstractDice() {
		this(new RandomDieRoller());
	}

	protected int getRandomDieValue() {
		return dieRoller.getDieValue();
	}

	public int getHighestValueOfSame(int count, int butNot) {
		for (int value = ValuesDice.MAX_DIE_VALUE; value >= 1; value--) {
			if (value != butNot && getValueCount(value) >= count) {
				return value;
			}
		}
		return 0;
	}

	public int getDualCountScore(int count1, int count2) {
		int value1 = getHighestValueOfSame(count1, 0);
		int value2 = getHighestValueOfSame(count2, value1);
		return (value1 > 0 && value2 > 0 ? value1 * count1 + value2 * count2 : 0);
	}

	public int getStraightSum(int startValue, int endValue) {
		int sum = 0;
		for (int value = startValue; value <= endValue; value++) {
			if (getValueCount(value) < 1) {
				return 0;
			}
			sum += value;
		}
		return sum;
	}
}
