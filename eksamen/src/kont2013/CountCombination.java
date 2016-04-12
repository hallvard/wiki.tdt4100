package kont2013;

public class CountCombination implements Combination {

	private int count;

	public CountCombination(int count) {
		this.count = count;
	}

	public String toString() {
		return String.valueOf(count + " of a kind");
	}

	public int evaluate(Dice dice) {
		return dice.getHighestValueOfSame(count, 0) * count;
	}
}
