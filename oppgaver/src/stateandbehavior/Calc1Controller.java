package stateandbehavior;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Calc1Controller {

	Calc1 calc;
	
	@FXML
	void initialize() {
		calc = new Calc1();
	}
	
	@FXML
	Text valueOutput;
	
	@FXML
	TextField operandInput;

	void updateView() {
		valueOutput.setText(String.valueOf(calc.getValue()));
	}
	
	double getOperand() {
		return Double.valueOf(operandInput.getText());
	}
	
	@FXML public void handleSet() {
		calc.setValue(getOperand());
		updateView();
	}

	@FXML public void handleAdd() {
		calc.add(getOperand());
		updateView();
	}

	@FXML public void handleSubtract() {
		calc.subtract(getOperand());
		updateView();		
	}

	@FXML public void handleMultiply() {
		calc.multiply(getOperand());
		updateView();		
	}

	@FXML public void handleDivide() {
		calc.divide(getOperand());
		updateView();		
	}

	@FXML public void handlePercent() {
		calc.percent(getOperand());
		updateView();		
	}

	@FXML public void handleNegate() {
		calc.negate();
		updateView();		
	}

	@FXML public void handlePi() {
		calc.pi();
		updateView();		
	}
}
