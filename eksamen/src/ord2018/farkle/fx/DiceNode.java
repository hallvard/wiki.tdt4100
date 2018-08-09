package ord2018.farkle.fx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class DiceNode extends Canvas {

	public DiceNode() {
		dieSizeProperty.addListener((prop, oldValue, newValue) -> {
			update();
		});
	}

	private void update() {
		setWidth(prefWidth(0));
		setHeight(prefHeight(0));
		draw();
	}

	private final List<Integer> dieValues = new ArrayList<Integer>();

	public void setDieValues(final Iterable<Integer> newDieValues) {
		dieValues.clear();
		if (newDieValues != null) {
			newDieValues.forEach(dieValues::add);
		}
		update();
	}

	//

	private final IntegerProperty dieSizeProperty = new SimpleIntegerProperty(this, "dieValue", 50);

	public IntegerProperty dieSizeProperty() {
		return dieSizeProperty;
	}

	public int getDieSize() {
		return dieSizeProperty.get();
	}

	public void setDieSize(final int size) {
		dieSizeProperty.set(size);
	}

	//

	private final DoubleProperty dotSizeProperty = new SimpleDoubleProperty(this, "dotSize", 9);

	public DoubleProperty dotSizeProperty() {
		return dotSizeProperty;
	}

	public double getDotSize() {
		return dotSizeProperty.get();
	}

	public void setDotSize(final double size) {
		dotSizeProperty.set(size);
	}

	//

	private final ObjectProperty<Paint> dotColorProperty = new SimpleObjectProperty<Paint>(this, "dotColor", null);

	public IntegerProperty dotColorProperty() {
		return dieSizeProperty;
	}

	public Paint getDotColor() {
		return dotColorProperty.get();
	}

	public void setDotColor(final Paint color) {
		dotColorProperty.set(color);
	}

	//

	@Override
	public double prefWidth(final double height) {
		return getDieSize() * dieValues.size();
	}

	@Override
	public double prefHeight(final double width) {
		return getDieSize();
	}

	@Override
	public boolean isResizable() {
		return true;
	}

	private final static double df = 0.23d;

	private final double[][] dotPositions = {
			{ 0.5d,0.5d },
			{ df,df, 1.0d - df,1.0d - df },
			{ df,df, 0.5d,0.5d, 1.0d - df,1.0d - df },
			{ df,df, df,1.0d - df, 1.0d - df,df, 1.0d - df,1.0d - df },
			{ df,df, df,1.0d - df, 0.5d,0.5d, 1.0d - df,df, 1.0d - df,1.0d - df },
			{ df,df, df,0.5d, df,1.0d - df, 1.0d - df,df, 1.0d - df,0.5d, 1.0d - df,1.0d - df },
	};

	private void draw() {
		final GraphicsContext gc = getGraphicsContext2D();
		final Paint dotColor = getDotColor();
		final Paint oldColor = gc.getFill();
		final double w = getWidth(), h = getHeight();
		gc.clearRect(0, 0, w, h);
		if (dotColor != null) {
			gc.setFill(dotColor);
		}
		for (int num = 0; num < dieValues.size(); num++) {
			final int value = dieValues.get(num);
			drawDie(num, value,  w / dieValues.size() / getDieSize(), h / getDieSize(), gc);
		}
		gc.setFill(oldColor);
	}

	private void drawDie(final int num, final int value, final double wf, final double hf, final GraphicsContext gc) {
		final double dieSize = getDieSize(), dotSize = getDotSize(), w = dieSize * wf, h = dieSize * hf;
		final double dw = wf * (dotSize < 1.0d ? w * dotSize : dotSize), dh = hf * (dotSize < 1.0d ? h * dotSize : dotSize);
		gc.strokeRoundRect(w * num, 0, w, h, dw, dh);
		final int positionsPos = value - 1;
		for (int pos = 0; pos < dotPositions[positionsPos].length; pos += 2) {
			final double cx = dotPositions[positionsPos][pos], cy = dotPositions[positionsPos][pos + 1];
			gc.fillOval(w * num + w * cx - dw / 2, h * cy - dh / 2, dw, dh);
		}
	}

	public static class DiceNodeApp extends Application {

		@Override
		public void start(final Stage primaryStage) throws Exception {
			final DiceNode diceNode = new DiceNode();
			diceNode.setDieValues(Arrays.asList(1, 2, 3, 4, 5, 6));
			final Parent root = new Pane(diceNode);
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		}

		public static void main(final String[] args) {
			launch(args);
		}
	}
}
