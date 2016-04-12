package kont2013;


public class ValuesDice extends AbstractDice implements Dice {

	private final int[] dice;

	public ValuesDice(int diceCount) {
		super();
		dice = new int[diceCount];
	}
	public ValuesDice(DieRoller dieRoller, int diceCount) {
		super(dieRoller);
		dice = new int[diceCount];
	}

	public String toString() {
		String result = "";
		for (int value : dice) {
			if (result.length() > 0) {
				result += "-";
			}
			result += value;
		}
		return result;
	}
	
	public int getValueCount(int value) {
		int count = 0;
		for (int v : dice) {
			if (v == value) {
				count++;
			}
		}
		return count;
	}
	
	private void roll(boolean[] indices) {
		for (int die = 0; die < dice.length; die++) {
			if (indices == null || indices[die]) {
				dice[die] = getRandomDieValue();
			}
		}
	}
	
	public void roll() {
		roll((boolean[]) null);
	}

	public void roll(int[] values) {
		boolean[] indices = new boolean[dice.length];
		for (int value : values) {
			int die = 0;
			while (die < dice.length) {
				if ((! indices[die]) && value == dice[die]) {
					break;
				}
				die++;
			}
			if (die < dice.length) {
				indices[die] = true;
			} else {
				throw new IllegalArgumentException("No die with value " + value);
			}
		}
		roll(indices);
	}
}
