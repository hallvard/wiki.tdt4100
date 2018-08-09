package kont2018.farkle;

import java.util.Iterator;

public class RandomValuesIterator implements Iterator<Integer> {

	private int count;

	public RandomValuesIterator(final int count) {
		this.count = count;
	}

	@Override
	public boolean hasNext() {
		return count > 0;
	}

	@Override
	public Integer next() {
		count--;
		return (int)(Math.random() * 6 + 1);
	}
}
