package kont2013;

import kont2013.ScoreCard.Combinations;

public class ScoreCardTest extends AbstractDiceTest {

	private ScoreCard scoreCard;
	
	protected void setUp() {
		super.setUp(new ValuesDice(this, 5));
		scoreCard = new ScoreCard(true);
	}

	private void assertEquals(int score, Combinations combination) {
		assertEquals(score, scoreCard.getScore(dice, combination));
	}
	
	private void assertEquals(int score, Combinations combination, int... values) {
		roll(values);
		assertEquals(score, combination);
	}
	
	public void testGetScore2() {
		roll(1, 2, 3, 4, 5);
		for (int i = 0; i < 5; i++) {
			assertEquals(i + 1, scoreCard.getScore(dice, Combinations.values()[i]));
		}
		assertEquals(0, scoreCard.getScore(dice, Combinations.SIXES));
		assertEquals(0, scoreCard.getScore(dice, Combinations.PAIR));
		assertEquals(0, scoreCard.getScore(dice, Combinations.TWO_PAIRS));
		assertEquals(0, scoreCard.getScore(dice, Combinations.THREE_OF_A_KIND));
		assertEquals(0, scoreCard.getScore(dice, Combinations.FOUR_OF_A_KIND));
		assertEquals(15, scoreCard.getScore(dice, Combinations.SMALL_STRAIGHT));
		assertEquals(0, scoreCard.getScore(dice, Combinations.LARGE_STRAIGHT));
		assertEquals(0, scoreCard.getScore(dice, Combinations.FULL_HOUSE));
		assertEquals(15, scoreCard.getScore(dice, Combinations.CHANCE));
		assertEquals(0, scoreCard.getScore(dice, Combinations.YATZY));
		
		assertEquals(3, Combinations.ONES, 1, 1, 1, 2, 3);
		assertEquals(4, Combinations.TWOS, 2, 1, 1, 2, 3);
		assertEquals(12, Combinations.THREES, 3, 3, 3, 2, 3);

		roll(6, 3, 3, 3, 6);
		assertEquals(12, Combinations.PAIR);
		assertEquals(18, Combinations.TWO_PAIRS);
		assertEquals(9, Combinations.THREE_OF_A_KIND);
		assertEquals(21, Combinations.FULL_HOUSE);
		assertEquals(21, Combinations.CHANCE);
	}
}
