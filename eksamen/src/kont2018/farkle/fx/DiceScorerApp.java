package kont2018.farkle.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DiceScorerApp extends Application {

	@Override
	public void start(final Stage primaryStage) throws Exception {
		final Parent parent = FXMLLoader.load(getClass().getResource("DiceScorer.fxml"));
		primaryStage.setScene(new Scene(parent, 400, 200));
		primaryStage.show();
	}

	public static void main(final String[] args) {
		Application.launch(args);
	}
}
