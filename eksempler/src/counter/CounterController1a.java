package counter;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class CounterController1a {

	Counter counter;

	@FXML
	Text counterOutput;
	
	@FXML
	void initialize() {
		counter = new Counter(5);
	}
	
	@FXML
	void handleCountAction() {
		counter.count();
		counterOutput.setText("Current counter value: " + counter.getCounter());
	}
}
