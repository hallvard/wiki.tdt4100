package kont2013;

public class RandomDieRoller implements DieRoller {

	public int getDieValue() {
		return (int)(Math.random() * Dice.MAX_DIE_VALUE + 1);
	}
}
