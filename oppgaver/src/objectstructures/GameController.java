package objectstructures;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class GameController {
	
	
	@FXML
	TextArea console ; 
	
	@FXML
	TextField inputField ; 
	
	//Her må du deklarerere spillet ditt
	TicTacToe game ; 
	
	public void initialize(){
		//Her maa du opprette et objekt av spillet ditt med de variablene som behoves for det - resten av koden vil gå ut ifra at du har kalt den game
		game = new TicTacToe() ; 
		console.setText(game.toString());
	}
	
	
	@FXML
	public void sendInput(){
		String in = inputField.getText(); 
		System.out.println(in);
		game.getInput(in); 
		console.setText(game.toString());
		
	}
}
