package ord2018.farkle.part2;

import java.util.function.Supplier;

public class RandomDieValues implements Supplier<Integer> {

	@Override
	public Integer get() {
		return (int)(Math.random() * 6 + 1);
	}
}
