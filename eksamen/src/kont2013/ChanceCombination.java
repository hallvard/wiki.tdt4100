package kont2013;

public class ChanceCombination implements Combination {

	public int evaluate(Dice dice) {
		int sum = 0;
		for (int value = 1; value <= Dice.MAX_DIE_VALUE; value++) {
			sum += value * dice.getValueCount(value);
		}
		return sum;
	}
	
	public String toString() {
		return String.valueOf("Chance");
	}
}
