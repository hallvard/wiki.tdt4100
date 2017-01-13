package counter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CounterController1b {

	/**
	 * The upper limit (inclusive) for the counter).
	 */
	int end;
	
	/**
	 * The counter, with initial value.
	 */
	int counter = 0;

	void count() {
		if (counter < end) {
			counter = counter + 1;
		}
	}

	@FXML
	Label counterOutput;
	
	@FXML
	void initialize() {
		end = 5;
	}

	@FXML
	void handleCountAction() {
		count();
		counterOutput.setText("Current counter value: " + counter);
	}
}
