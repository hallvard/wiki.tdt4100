package ord2018.farkle.fx;

import java.util.Collection;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ord2018.farkle.part1.Dice;
import ord2018.farkle.part1.FarkleScoring;
import ord2018.farkle.part1.StandardFarkleScoring;

public class DiceController {

	@FXML
	private TextField dieCountInput;

	@FXML
	private Label diceOutput;

	private final FarkleScoring scoring = new StandardFarkleScoring();

	@FXML
	public void handleThrowDice() {
		final Dice dice = new Dice(Integer.valueOf(dieCountInput.getText()));
		computeFarkleScore(dice);
		diceOutput.setText(dice.toString());
	}

	private void computeFarkleScore(final Dice dice) {
		final Collection<Dice> scores = scoring.computeDiceScores(dice);
		dice.setScore(scores.stream().mapToInt(Dice::getScore).sum());
	}
}
