package ord2018.farkle.part1;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

public class FarkleRoundTest {

	private Iterator<Integer> nextValues;

	private final FarkleScoring scoring = new StandardFarkleScoring();

	private void checkDiceScores(final Iterable<Dice> diceScores, final int... scores) {
		int i = 0;
		for (final Dice diceScore : diceScores) {
			Assert.assertEquals(scores[i], diceScore.getScore());
			i++;
		}
		Assert.assertEquals(i, scores.length);
	}

	@Test
	public void testDiceRound1() {
		nextValues = Arrays.asList(1, 1, 5, 2, 2).iterator();
		final FarkleRound round = new FarkleRound(5, scoring, () -> nextValues.next());
		DiceTest.checkDieValues(round.getCurrentDice(), 1, 1, 5, 2, 2);

		nextValues = Arrays.asList(1, 2).iterator();
		round.keepAndRoll(new Dice(Arrays.asList(1, 1, 5), -1));
		checkDiceScores(round, 100, 100, 50);
		Assert.assertFalse(round.isFinished());
		DiceTest.checkDieValues(round.getCurrentDice(), 1, 2);

		round.bank();
		Assert.assertEquals(350, round.getScore());
		checkRoundHasEnded(round);
	}

	private void checkRoundHasEnded(final FarkleRound round) {
		Assert.assertTrue(round.isFinished());
		try {
			round.bank();
			Assert.fail();
		} catch (final Exception e) {
			Assert.assertTrue(e instanceof IllegalStateException);
		}
		try {
			round.keepAndRoll(null);
			Assert.fail();
		} catch (final Exception e) {
			Assert.assertTrue(e instanceof IllegalStateException);
		}
	}
}
