package kont2013;

import java.util.Arrays;

import junit.framework.TestCase;

public class AbstractDiceTest extends TestCase implements DieRoller {

	protected Dice dice;
	
	protected void setUp(Dice dice) {
		this.dice = dice;
	}
	
	private int nextValues[] = {1, 2, 3, 4, 5, 6}, pos = 0;
	
	public int getDieValue() {
		return nextValues[pos++ % nextValues.length];
	}

	protected void setDice(int... values) {
		nextValues = values;
		pos = 0;
	}
	
	protected void roll(int... values) {
		setDice(values);
		dice.roll();
	}

	protected void assertEquals(String toString, int... values) {
		String[] tokens = toString.split("-");
		Arrays.sort(tokens);
		for (int i = 0; i < 5; i++) {
			assertEquals(String.valueOf(values[i]), tokens[i]);
		}
	}
	
	protected void assertDiceEquals(int... values) {
		assertEquals(dice.toString(), values);
	}
}
