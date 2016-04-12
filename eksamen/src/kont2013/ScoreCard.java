package kont2013;

import java.util.ArrayList;
import java.util.List;

public class ScoreCard {

	enum Combinations {
		ONES, TWOS, THREES, FOURS, FIVES, SIXES, PAIR, TWO_PAIRS, THREE_OF_A_KIND, FOUR_OF_A_KIND, SMALL_STRAIGHT, LARGE_STRAIGHT, FULL_HOUSE, CHANCE, YATZY;
	}

	private boolean freeMode;
	private Integer[] scores;

	public ScoreCard(boolean freeMode) {
		this.freeMode = freeMode;
		this.scores = new Integer[Combinations.values().length];
	}
	
	public String toString() {
		String result = "";
		int sum = 0;
		for (Combinations combination : Combinations.values()) {
			result += combination + ": ";
			Integer score = scores[combination.ordinal()];
			if (score == null) {
				result += "\n";
			} else {
				sum += score;
				result += (freeMode && score == 0 ? "-" : String.valueOf(score)) + "\n";
			}
			if (combination.ordinal() == Dice.MAX_DIE_VALUE) {
				result += "Sum: " + sum + "\n";
				int bonus = (sum >= (1 + 2 + 3 + 4 + 5 + 6) * (freeMode ? 3 : 2) ? 50 : 0);
				result += "Bonus: " + bonus + "\n";
				sum += bonus;
			}
		}
		result += "Total: " + sum + "\n";
		return result;
	}

	public Integer getScore(Combinations combination) {
		return scores[combination.ordinal()];
	}

	public int getScore(Dice dice, Combinations combination) {
		switch (combination) {
			case PAIR: 		return dice.getHighestValueOfSame(2, 0) * 2;
			case TWO_PAIRS: return dice.getDualCountScore(2, 2);
			case THREE_OF_A_KIND: 	return dice.getHighestValueOfSame(3, 0) * 3;
			case FOUR_OF_A_KIND: 	return dice.getHighestValueOfSame(4, 0) * 4;
			case SMALL_STRAIGHT: return dice.getStraightSum(1, 5);
			case LARGE_STRAIGHT: return dice.getStraightSum(2, 6);
			case FULL_HOUSE: return dice.getDualCountScore(3, 2);
			case CHANCE: {
				int sum = 0;
				for (int value = 1; value <= Dice.MAX_DIE_VALUE; value++) {
					sum += value * dice.getValueCount(value);
				}
				return sum;
			}
			case YATZY: return dice.getHighestValueOfSame(5, 0) > 0 ? 50 : 0;
			default: {
				int value = combination.ordinal() + 1;
				return dice.getValueCount(value) * value;
			}
		}
	}

	public void setScore(Dice dice, Combinations combination) {
		int score = getScore(dice, combination);
		scores[combination.ordinal()] = score;
		fireScoreBoardChanged(dice, combination);
	}
	
	// listener support

	private List<ScoreCardListener> listeners = new ArrayList<ScoreCardListener>();
	
	public void addScoreBoardListener(ScoreCardListener listener) {
		listeners.add(listener);
	}
	
	public void removeScoreBoardListener(ScoreCardListener listener) {
		listeners.remove(listener);
	}
	
	private void fireScoreBoardChanged(Dice dice, Combinations combination) {
		for (ScoreCardListener listener : listeners) {
			listener.scoreCardChanged(this, combination);
		}
	}
}
