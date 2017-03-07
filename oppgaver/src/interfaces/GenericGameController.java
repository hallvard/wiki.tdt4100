package interfaces;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class GenericGameController {
	
	@FXML TextArea console; 
	
	@FXML TextField inputField;
	@FXML TextField fileName;
	
	GenericGridGame<Character> game; 
	
	public void initialize() {
		//Her maa du opprette et objekt av spillet ditt med de argumentene som behoves for det - resten av koden vil gaa ut ifra at du har kalt den game
		game = new TicTacToe(); 
		update();
	}
	
	private void update() {
		console.setText(game.toString());
		undoButton.setDisable(! game.canUndo());
		redoButton.setDisable(! game.canRedo());
		updateGrid();
	}
	
	@FXML
	public void sendInput() {
		String in = inputField.getText(); 
		game.getInput(in); 
		update();
	}
	
	@FXML Button undoButton;
	@FXML Button redoButton;
	
	@FXML
	public void undo() {
		game.undo();
		update();
	}
	
	@FXML
	public void redo() {
		game.redo();
		update();
	}
	
	@FXML
	public void save() throws IOException {
		game.save(fileName.getText());
		update();
	}
	
	@FXML
	public void load() throws IOException {
		game.load(fileName.getText());
		update();
	}
	
	@FXML GridPane gameGrid;
	
	private Map<String, Image> images = new HashMap<String, Image>();
	
	private Image getImage(String imageName) {
		Image image = images.get(imageName);
		if (image == null) {
			String urlString = game.getClass().getResource(imageName + ".png").toString();
			image = new Image(urlString);
			images.put(imageName, image);
		}
		return image;
	}

	private void updateGrid() {
		gameGrid.getChildren().clear();
		for (int y = 0; y < game.getRowCount(); y++) {
			for (int x = 0; x < game.getColumnCount(); x++) {
				Character c = game.getCell(x, y);
				String[] imageNames = game.getImageNames(c);
				ImageView[] imageViews = new ImageView[imageNames.length];
				for (int imageNum = imageNames.length - 1; imageNum >= 0; imageNum--) {
					ImageView imageView = new ImageView(getImage(imageNames[imageNum]));
					imageViews[imageNum] = imageView;
				}
				Node node = null;
				if (imageViews.length == 1) {
					node = imageViews[0];
				} else {
					node = new StackPane(imageViews);
				}
				gameGrid.getChildren().add(node);
				GridPane.setRowIndex(node, y);
				GridPane.setColumnIndex(node, x);
			}
		}
	}
}
