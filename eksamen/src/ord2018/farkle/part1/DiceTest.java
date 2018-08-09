package ord2018.farkle.part1;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class DiceTest {

	static Dice dice(final Integer... values) {
		return new Dice(Arrays.asList(values), -1);
	}

	protected Dice dice2(final Integer... values) {
		return new Dice(Arrays.asList(values), -1);
	}

	@Test
	public void testToString() {
		final Dice dice = dice(1, 1, 2);
		assertEquals("[1, 1, 2]", dice.toString());
		dice.setScore(0);
		assertEquals("[1, 1, 2] = 0", dice.toString());
	}

	@Test
	public void testValueOf() {
		final Dice dice1 = Dice.valueOf("[1, 1, 2]");
		checkDieValues(dice1, 1, 1, 2);
		Assert.assertEquals(-1, dice1.getScore());

		final Dice dice2 = Dice.valueOf("[1, 1, 2] = 200");
		checkDieValues(dice2, 1, 1, 2);
		Assert.assertEquals(200, dice2.getScore());

		try {
			Dice.valueOf("1, 5, 2 = 200");
			Assert.fail();
		} catch (final Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		try {
			Dice.valueOf("1, x, 2 = 200");
			Assert.fail();
		} catch (final Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		try {
			Dice.valueOf("1, 5, 2 = x");
			Assert.fail();
		} catch (final Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testSetScore() {
		final Dice dice = dice(1, 1, 2);
		assertEquals(-1, dice.getScore());
		dice.setScore(0);
		assertEquals(0, dice.getScore());
		try {
			dice.setScore(10);
			Assert.fail();
		} catch (final Exception e) {
			Assert.assertTrue(e instanceof IllegalStateException);
		}
	}

	static void checkDieValues(final Dice dice, final int... vals) {
		int i = 0;
		for (final Integer dieValue : dice) {
			Assert.assertEquals("Value " + (i + 1) + " is not as expected", vals[i], dieValue.intValue());
			i++;
		}
		Assert.assertEquals(vals.length, i);
	}

	static void checkValueCounts(final Dice dice, final int... vals) {
		for (int i = 0; i < vals.length; i++) {
			Assert.assertEquals("Count for " + (i + 1) + " is not as expected", vals[i], dice.getValueCount(i + 1));
		}
	}

	@Test
	public void testDice1() {
		final Dice dice = dice(1, 1, 2, 4, 3);
		checkDieValues(dice, 1, 1, 2, 4, 3);
		checkValueCounts(dice, 2, 1, 1, 1, 0, 0);
		try {
			dice(0);
			Assert.fail();
		} catch (final Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		try {
			dice(7);
			Assert.fail();
		} catch (final Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testDice2() {
		final Dice dice = dice2(1, 1, 2, 4, 3);
		checkDieValues(dice, 1, 1, 2, 4, 3);
		checkValueCounts(dice, 2, 1, 1, 1, 0, 0);
	}

	@Test
	public void testAdd() {
		final Dice dice3 = dice(1, 1, 2).add(dice(1)).add(dice(4)).add(dice(3));
		checkDieValues(dice3, 1, 1, 2, 1, 4, 3);
		checkValueCounts(dice3, 3, 1, 1, 1, 0, 0);
	}

	@Test
	public void testContains() {
		final Dice dice1 = dice(1, 1, 2, 4, 3);
		Assert.assertTrue(dice1.contains(dice(1, 1, 3)));
		Assert.assertFalse(dice1.contains(dice(1, 3, 5)));
	}

	@Test
	public void testRemove() {
		final Dice dice1 = dice(1, 1, 2, 4, 3);
		final Dice dice2 = dice(1, 3, 5);
		final Dice dice3 = dice1.remove(dice2);
		checkDieValues(dice3, 1, 2, 4);
		checkValueCounts(dice3, 1, 1, 0, 1, 0, 0);
	}
}
