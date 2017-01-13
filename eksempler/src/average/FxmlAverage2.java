package average;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FxmlAverage2 {

	Average average = new Average();

	@FXML
	Label averageOutput;
	
	void updateAverageLabel() {
	}
	
	@FXML
	TextField numInput;
	
	@FXML
	void handleAnotherNumAction() {
		average.acceptValue(Double.valueOf(numInput.getText()));
		averageOutput.setText("Current average: " + average.getAverage());
	}
}
