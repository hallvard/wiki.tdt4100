package kont2013;

public class DualCountCombination implements Combination {

	private int count1, count2;

	public DualCountCombination(int count1, int count2) {
		this.count1 = count1;
		this.count2 = count2;
	}

	public String toString() {
		return String.valueOf(count1 + " + " + count2);
	}

	public int evaluate(Dice dice) {
		return dice.getDualCountScore(count1, count2);
	}
}
