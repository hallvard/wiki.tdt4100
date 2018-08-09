package kont2018.farkle;

import java.util.Iterator;

public class DiceIterator implements Iterator<Integer> {

	private final Dice dice;
	private int dieNum = 0;

	public DiceIterator(final Dice dice) {
		this.dice = dice;
	}

	@Override
	public boolean hasNext() {
		return dieNum < dice.getDieCount();
	}

	@Override
	public Integer next() {
		final int value = dice.getDieValue(dieNum);
		dieNum++;
		return value;
	}
}
