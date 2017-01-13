package average;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FxmlAverage1 {

	int valueCount = 0;
	double sum = 0.0;
	
	void acceptValue(double num) {
		sum = sum + num;
		valueCount = valueCount + 1;
	}
	
	double getAverage() {
		return sum / valueCount;
	}

	@FXML
	Label averageOutput;
	
	@FXML
	TextField numInput;
	
	@FXML
	void handleAnotherNumAction() {
		acceptValue(Double.valueOf(numInput.getText()));
		averageOutput.setText("Current average: " + getAverage());
	}
}
