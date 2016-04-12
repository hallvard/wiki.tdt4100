package kont2013;

public class YatzyCombination extends CountCombination {

	public YatzyCombination() {
		super(5);
	}

	public String toString() {
		return "Yatzy";
	}

	public int evaluate(Dice dice) {
		int value = super.evaluate(dice);
		return (value > 0 ? 50 : 0);
	}
}
