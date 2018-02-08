package encapsulation;

import javafx.scene.control.Button;

public class Kalkulator {
	String valueText = "";
	
	private double memory;
	
	private String operator;
	
	public String getValueText() {
		return valueText;
	}
	
	public void clearValueText() {
		valueText = "";
	}
	
	public void appendDigit(String digit) {
		valueText += digit;
	}
	
	public double getMemory() {
		return memory;
	}
	
	public void addDecimalPoint() {
		if (!valueText.contains(".")) {
			valueText += ".";
		}
	}
	
	public void computeAndStoreValue(String action) {
		if (valueText.isEmpty()) {
			return;
		}
		
		double value = Double.valueOf(valueText);
		
		if (this.operator == null) {
			memory = value;
		} else if (this.operator.equals("+")) {
			memory += value;
		} else if (this.operator.equals("-")) {
			memory -= value;
		} else if (this.operator.equals("*")) {
			memory *= value;
		} else if (this.operator.equals("/")) {
			memory /= value;
		}

		if (action.equals("=")) {
			this.operator = null;
			valueText = String.valueOf(memory);
		} else {
			this.operator = action;
			clearValueText();
		}
	}
}
