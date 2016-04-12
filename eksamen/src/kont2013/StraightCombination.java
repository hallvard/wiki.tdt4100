package kont2013;

public class StraightCombination implements Combination {

	private int start, end;

	public StraightCombination(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	public String toString() {
		return start + " - " + end;
	}

	public int evaluate(Dice dice) {
		return dice.getStraightSum(start, end);
	}
}
