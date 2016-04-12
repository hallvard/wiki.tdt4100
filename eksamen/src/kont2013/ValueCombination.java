package kont2013;

public class ValueCombination implements Combination {

	private int value;

	public ValueCombination(int value) {
		this.value = value;
	}

	public String toString() {
		return String.valueOf(value);
	}
	
	public int evaluate(Dice dice) {
		return dice.getValueCount(value) * value;
	}
}
