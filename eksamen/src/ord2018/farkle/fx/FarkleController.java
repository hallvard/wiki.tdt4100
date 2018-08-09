package ord2018.farkle.fx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import ord2018.farkle.part1.Dice;
import ord2018.farkle.part1.FarkleRound;
import ord2018.farkle.part1.FarkleScoring;
import ord2018.farkle.part1.StandardFarkleScoring;

public class FarkleController {

	@FXML
	private BorderPane borderPane;

	@FXML
	private TextField numPlayersText;

	private List<FarkleRound> rounds;
	private FarkleRound currentRound;

	@FXML
	private ListView<Dice> keptDiceList;

	@FXML
	private ListView<Dice> dieList;

	@FXML
	public void initialize() {
		keptDiceList.setEditable(false);
		dieList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		final Callback<ListView<Dice>, ListCell<Dice>> cellFactory = cell -> {
			return new ListCell<Dice>() {
				private final DiceNode diceNode = new DiceNode();
				@Override
				protected void updateItem(final Dice item, final boolean empty) {
					super.updateItem(item, empty);
					diceNode.setDieValues(item != null ? item : null);
					setGraphic(diceNode);
				}
			};
		};
		keptDiceList.setCellFactory(cellFactory);
		dieList.setCellFactory(cellFactory);
	}

	private List<VBox> playerVBoxes;

	private void startGame(final int numPlayers) {
		rounds = new ArrayList<FarkleRound>();
		final HBox pane = new HBox();
		playerVBoxes = new ArrayList<>();
		for (int num = 0; num < numPlayers; num++) {
			final VBox vbox = new VBox();
			vbox.getChildren().add(new Text("Player #" + (num + 1)));
			vbox.getChildren().add(new Text("= 0"));
			pane.getChildren().add(vbox);
			playerVBoxes.add(vbox);
		}
		borderPane.setCenter(pane);
		nextRound();
	}

	@FXML
	public void handleStartGame() {
		startGame(Integer.valueOf(numPlayersText.getText()));
	}

	private final FarkleScoring scoring = new StandardFarkleScoring();

	@FXML
	private Text currentPlayerNum;

	private void nextRound() {
		final int playerCount = playerVBoxes.size();
		int playerNum = rounds.size() % playerCount;
		if (currentRound != null) {
			final VBox vbox = playerVBoxes.get(playerNum);
			rounds.add(currentRound);
			vbox.getChildren().remove(vbox.getChildren().size() - 1);
			vbox.getChildren().add(new Text("+ " + currentRound.getScore()));
			int sum = 0, num = 0;
			for (final FarkleRound round : rounds) {
				if (num % playerCount == playerNum) {
					sum += round.getScore();
				}
				num++;
			}
			vbox.getChildren().add(new Text("= " + sum));
		}
		playerNum = rounds.size() % playerCount;
		currentPlayerNum.setText("Player #" + (playerNum + 1));
		keptDiceList.getItems().clear();

		currentRound = new FarkleRound(5, scoring);
		updateDieScores();
	}

	private void updateDieScores() {
		if (currentRound.isFinished()) {
			nextRound();
		} else {
			dieList.getItems().clear();
			for (final int dieValue : currentRound.getCurrentDice()) {
				dieList.getItems().add(new Dice(Collections.singleton(dieValue), -1));
			}
		}
	}

	private Dice concat(final Collection<Dice> dices) {
		Dice result = new Dice(Collections.emptyList(), -1);
		for (final Dice dice : dices) {
			result = result.add(dice);
		}
		return result;
	}

	@FXML
	public void handleKeepAllAndRoll() {
		handleKeepAndRoll(concat(scoring.computeDiceScores(currentRound.getCurrentDice())));
	}

	@FXML
	public void handleKeepAndRoll() {
		handleKeepAndRoll(concat(dieList.getSelectionModel().getSelectedItems()));
	}

	private void handleKeepAndRoll(final Dice dice) {
		if (currentRound != null) {
			currentRound.keepAndRoll(dice);
			keptDiceList.getItems().clear();
			for (final Dice diceScore : currentRound) {
				keptDiceList.getItems().add(diceScore);
			}
			updateDieScores();
		}
	}

	@FXML
	public void handleSaveScore() {
		if (currentRound != null) {
			currentRound.bank();
			nextRound();
		}
	}
}
