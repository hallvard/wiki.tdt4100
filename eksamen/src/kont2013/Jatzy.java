package kont2013;

import java.util.Scanner;

import kont2013.ScoreCard.Combinations;

public class Jatzy {

	private ScoreCard[] scoreCards;
	
	private boolean freeMode = true;

	public Jatzy(boolean freeMode, int playerCount) {
		this.freeMode = freeMode;
		this.scoreCards = new ScoreCard[playerCount];
		for (int i = 0; i < scoreCards.length; i++) {
			scoreCards[i] = new ScoreCard(freeMode);
		}
	}

	public void run() {
		Dice dice = new ValuesDice(5);
		for (Combinations combination : Combinations.values()) {
			for (int player = 0; player < scoreCards.length; player++) {
				println("Round #" + (combination.ordinal() + 1) + ", player #" + (player + 1));
				ScoreCard scoreCard = scoreCards[player];
				print(scoreCard);
				int[] rollValues = null;
				println();
				dice.roll();
				println("Dice: " + dice);
				for (int rollCount = 0; rollCount < 2; rollCount++) {
					String line = readLine("Throw #" + (rollCount + 1) + (freeMode ? "" : " for " + combination) + ". Enter which dice (values) to roll: ");
					rollValues = new int[line.length()];
					for (int i = 0; i < line.length(); i++) {
						rollValues[i] = line.charAt(i) - '0';
					}
					dice.roll(rollValues);
					println("Dice: " + dice);
				}
				Combinations scoreCombination = (freeMode ? null : combination);
				outer: while (combination == null) {
					String line = readLine("Choose combination by entering the first letter(s): ").toLowerCase();
					for (Combinations comb : Combinations.values()) {
						if (comb.name().toLowerCase().startsWith(line)) {
							int score = scoreCard.getScore(dice, comb);
							if (freeMode && score == 0) {
								if (! readBoolean("Are you sure you want to clear " + comb + "?")) {
									continue outer;
								}
							}
							scoreCombination = comb;
							break;
						}
					}
				}
				int score = scoreCard.getScore(dice, scoreCombination);
				scoreCard.setScore(dice, scoreCombination);
				println("Score " + score + " for " + scoreCombination + "!");
			}
		}
		for (int player = 0; player < scoreCards.length; player++) {
			println("Results for player #" + (player + 1));
			print(scoreCards[player]);
		}
	}

	// ACM compatibility methods
	
	private void print(Object o) {
		print(o.toString());
	}
	private void println(Object o) {
		println(o.toString());
	}

	private void print(String s) {
		System.out.print(s);
	}
	private void println(String s) {
		System.out.println(s);
	}

	private void println() {
		System.out.println();
	}
	
	private Scanner scanner = new Scanner(System.in);
	
	private String readLine(String s) {
		print(s);
		return scanner.nextLine();
	}

	private boolean readBoolean(String s) {
		print(s);
		return scanner.nextBoolean();
	}
	
	//
	
	public static void main(String[] args) {
		new Jatzy(false, 1).run();
	}
}
