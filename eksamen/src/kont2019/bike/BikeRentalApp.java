package kont2019.bike;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BikeRentalApp extends Application {

	@Override
	public void start(final Stage primaryStage) throws Exception {
		final Parent parent = FXMLLoader.load(getClass().getResource("BikeRental.fxml"));
		primaryStage.setScene(new Scene(parent, 400, 525));
		primaryStage.show();
	}

	public static void main(final String[] args) {
		Application.launch(args);
	}
}
