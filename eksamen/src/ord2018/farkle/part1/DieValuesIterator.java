package ord2018.farkle.part1;

import java.util.Iterator;

public class DieValuesIterator implements Iterator<Integer> {

	private final Dice dice;
	private int dieNum = 0;

	public DieValuesIterator(final Dice dice) {
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
