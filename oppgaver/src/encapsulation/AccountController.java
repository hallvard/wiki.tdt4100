package encapsulation;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class AccountController {
	
	private Account account;
	
	@FXML
	private Text toStringText;
	
	@FXML
	private TextField getBalanceTextField;
	@FXML
	private TextField getInterestRateTextField;
	
	@FXML
	private TextField depositTextField;
	@FXML
	private TextField withdrawTextField; 
	@FXML
	private TextField setInterestRateTextField;
	
	@FXML private TextArea exceptionTextArea;

	@FXML
	private void initialize() {
		getBalanceTextField.setDisable(true);
		getInterestRateTextField.setDisable(true);
		account = new Account();
		update();
	}
	
	private void update() {
		toStringText.setText(account.toString());
		getBalanceTextField.setText(Double.toString(account.getBalance()));
		getInterestRateTextField.setText(Double.toString(account.getInterestRate()));
	}
	
	
	
	private double getDoubleFromTextField(TextField textField) {
		return Double.parseDouble(textField.getText());
	}
	
	private void showException(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		pw.write(e.getMessage() != null ? e.getMessage() : "No message");
		pw.write("\n\n");
		e.printStackTrace(pw);
		exceptionTextArea.setText(sw.toString());
	}
	
	private void clearException() {
		exceptionTextArea.setText("");
	}
	

	
	@FXML
	private void handleDeposit() {
		double amount = getDoubleFromTextField(depositTextField);
		account.deposit(amount);
		update();
	}
	
	
	
	
	
	
	@FXML
	private void handleSetInterestRate() {
		double interestRate = getDoubleFromTextField(setInterestRateTextField);
		account.setInterestRate(interestRate);
		update();
	}
	
	@FXML
	private void handleAddInterest() {
		account.addInterest();
		update();
	}
	
	@FXML
	private void handleWithdrawal() {
		double amount = getDoubleFromTextField(withdrawTextField);
		account.withdraw(amount);
		update();
	}
}
