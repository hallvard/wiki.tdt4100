package counter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CounterController2 {

	Counter counter = null;

	@FXML
	TextField endInput;

	@FXML
	Label counterOutput;
	
	@FXML
	void initialize() {
		counterOutput.setText("No counter");
	}
	
	void updateCounterOutput() {
		counterOutput.setText("Current counter value: " + counter.getCounter());
	}

	@FXML
	void handleNewCounterAction() {
		counter = new Counter(Integer.valueOf(endInput.getText()));
		updateCounterOutput();
	}

	@FXML
	void handleCountAction() {
		counter.count();
		updateCounterOutput();
	}
}
