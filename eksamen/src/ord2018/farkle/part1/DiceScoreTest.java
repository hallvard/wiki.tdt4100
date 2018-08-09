package ord2018.farkle.part1;

import org.junit.Assert;
import org.junit.Test;

public class DiceScoreTest {

	private void checkDiceScore(final Dice score, final int expectedScore, final int... values) {
		Assert.assertEquals(expectedScore, score.getScore());
		if (values != null) {
			DiceTest.checkDieValues(score, values);
		}
	}

	@Test
	public void testThreeOrMoreOfAKind() {
		final DiceScorer threeOfAKind = new ThreeOrMoreOfAKindScorer();

		Assert.assertNull(threeOfAKind.getScore(DiceTest.dice(1, 2, 4, 2, 3)));

		final Dice dice12223 = DiceTest.dice(1, 2, 2, 2, 3);
		checkDiceScore(threeOfAKind.getScore(dice12223), 200, 2, 2, 2);

		final Dice dice22222 = DiceTest.dice(2, 2, 2, 2, 2);
		checkDiceScore(threeOfAKind.getScore(dice22222), 800, 2, 2, 2, 2, 2);

		final Dice dice222333 = DiceTest.dice(2, 2, 2, 3, 3, 3);
		checkDiceScore(threeOfAKind.getScore(dice222333), 300, 3, 3, 3);
	}

	@Test
	public void testNOrMoreOfAKind() {
		final DiceScorer threeOfAKind = new NOrMoreOfAKindScorer(3, false);
		final DiceScorer threeOfAKindDoubling = new NOrMoreOfAKindScorer(3, true);

		Assert.assertNull(threeOfAKind.getScore(DiceTest.dice(1, 2, 4, 2, 3)));

		final Dice dice12223 = DiceTest.dice(1, 2, 2, 2, 3);
		checkDiceScore(threeOfAKind.getScore(dice12223), 200, 2, 2, 2);

		final Dice dice22222 = DiceTest.dice(2, 2, 2, 2, 2);
		checkDiceScore(threeOfAKind.getScore(dice22222), 600, 2, 2, 2, 2, 2);
		checkDiceScore(threeOfAKindDoubling.getScore(dice22222), 800, null);

		final Dice dice11143 = DiceTest.dice(1, 1, 1, 4, 3);
		checkDiceScore(threeOfAKind.getScore(dice11143), 1000, null);

		final Dice dice222333 = DiceTest.dice(2, 2, 2, 3, 3, 3);
		checkDiceScore(threeOfAKind.getScore(dice222333), 300, 3, 3, 3);

		final Dice dice111333 = DiceTest.dice(1, 1, 1, 3, 3, 3);
		checkDiceScore(threeOfAKind.getScore(dice111333), 1000, 1, 1, 1);
	}

	@Test
	public void testValueScore() {
		final DiceScorer fiveScore = new ValueScorer(5, 50);
		final Dice dice246333 = DiceTest.dice(2, 4, 6, 3, 3, 3);
		final Dice dice151333 = DiceTest.dice(1, 5, 1, 3, 3, 3);
		final Dice dice155333 = DiceTest.dice(1, 5, 5, 3, 3, 3);

		Assert.assertNull(fiveScore.getScore(dice246333));
		checkDiceScore(fiveScore.getScore(dice151333), 50, 5);
		checkDiceScore(fiveScore.getScore(dice155333), 50, 5);

		final DiceScorer oneScore = new ValueScorer(1, 100);
		Assert.assertNull(oneScore.getScore(dice246333));
		checkDiceScore(oneScore.getScore(dice151333), 100, 1);
		checkDiceScore(oneScore.getScore(dice155333), 100, 1);
	}

	@Test
	public void testStraightScore() {
		final DiceScorer straightScore = new StraightScorer(500);
		final Dice dice12345 = DiceTest.dice(1, 2, 3, 4, 5);
		checkDiceScore(straightScore.getScore(dice12345), 500, 1, 2, 3, 4, 5);

		final Dice dice23456 = DiceTest.dice(2, 3, 4, 5, 6);
		checkDiceScore(straightScore.getScore(dice23456), 500, 2, 3, 4, 5, 6);

		final Dice dice123456 = DiceTest.dice(1, 2, 3, 4, 5, 6);
		checkDiceScore(straightScore.getScore(dice123456), 500, 1, 2, 3, 4, 5, 6);

		Assert.assertNull(straightScore.getScore(DiceTest.dice(1, 2, 3, 4, 6)));
		Assert.assertNull(straightScore.getScore(DiceTest.dice(1, 3, 4, 5, 6)));
		Assert.assertNull(straightScore.getScore(DiceTest.dice(1, 2, 3, 3, 4, 5)));
	}
}
