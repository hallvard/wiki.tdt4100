package kont2018.farkle.fx;

import java.util.ArrayList;
import java.util.Collection;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kont2018.farkle.Dice;
import kont2018.farkle.DiceScore;
import kont2018.farkle.DiceScorer;
import kont2018.farkle.NothingScorer;
import kont2018.farkle.SingleValueScorer;
import kont2018.farkle.StraightScorer;

public class DiceScorerController {

	private DiceScorer singleValue, straight, nothing;

	@FXML
	public void initialize() {
		singleValue = new SingleValueScorer(1, 100);
		straight = new StraightScorer(500);
		nothing = new NothingScorer(5, 1000, singleValue, straight);
	}

	@FXML
	private TextField dieValuesInput;

	@FXML
	private Label scoreOutput;

	@FXML
	private Label diceOutput;

	private Dice getDiceInput() {
		final Collection<Integer> dieValues = new ArrayList<>();
		for (final String dieValue : dieValuesInput.getText().split(" ")) {
			dieValues.add(Integer.valueOf(dieValue));
		}
		return new Dice(dieValues);
	}

	private void runDiceScorer(final DiceScorer scorer) {
		final DiceScore score = scorer.getScore(getDiceInput());
		if (score != null) {
			scoreOutput.setText(String.valueOf(score.getScore()));
			String dieValues = "";
			for (final int dieValue : score.getDice()) {
				dieValues += dieValue + " ";
			}
			diceOutput.setText(dieValues);
		} else {
			scoreOutput.setText("");
			diceOutput.setText("");
		}
	}

	@FXML
	public void testSingleValue() {
		runDiceScorer(singleValue);
	}

	@FXML
	public void testStraight() {
		runDiceScorer(straight);
	}

	@FXML
	public void testNothing() {
		runDiceScorer(nothing);
	}
}
