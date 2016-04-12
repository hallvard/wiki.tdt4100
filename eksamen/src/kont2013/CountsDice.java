package kont2013;


public class CountsDice extends AbstractDice implements Dice {

	private final int diceCount;
	private final int[] counts;

	public CountsDice(DieRoller dieRoller, int diceCount) {
		super(dieRoller);
		this.diceCount = diceCount; 
		counts = new int[MAX_DIE_VALUE];
	}
	public CountsDice(int diceCount) {
		this(new RandomDieRoller(), diceCount);
	}

	public String toString() {
		String result = "";
		for (int value = 0; value < counts.length; value++) {
			for (int i = 0; i < counts[value]; i++) {
				if (result.length() > 0) {
					result += "-";
				}
				result += (value + 1);
			}
		}
		return result;
	}
	
	public int getValueCount(int value) {
		return counts[value - 1];
	}

	private void roll(int n) {
		for (int i = 0; i < n; i++) {
			int newValue = getRandomDieValue();
			counts[newValue - 1]++;
		}
	}

	public void roll() {
		for (int value = 0; value < counts.length; value++) {
			counts[value] = 0;
		}
		roll(diceCount);
	}

	public void roll(int[] values) {
		if (values.length > diceCount) {
			throw new IllegalArgumentException("To many die values: " + values.length);
		}
		for (int value : values) {
			counts[value - 1]--;
			if (counts[value - 1] < 0) {
				throw new IllegalArgumentException("No die with value " + value);
			}
		}
		roll(values.length);
	}
}
