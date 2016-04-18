package games.game2048;

import games.imagegrid.ImageGridGame;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Game2048 extends ImageGridGame<Integer> {

	private int emptyCount = 16;
	private int points = 0;
	
	@FXML
	protected void initialize() {
		super.initialize();
		newAction();
	}

	@FXML
	private void newAction() {
		fillGrid(0);
		emptyCount = 16;
		points = 0;
		putRandom();
		putRandom();
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		for (int y = 0; y < imageGrid.getRowCount(); y++) {
			for (int x = 0; x < imageGrid.getColumnCount(); x++) {
				int value = getCell(x, y);
				buffer.append(String.format(" %1$4s", value));
			}
		}
		return buffer.toString();
	}
	
	private int[] putRandom() {
		int pos = (int) (Math.random() * emptyCount);
		for (int y = 0; y < imageGrid.getRowCount(); y++) {
			for (int x = 0; x < imageGrid.getColumnCount(); x++) {
				if (getCell(x, y) == 0) {
					if (pos == 0) {
						setCell(x, y, 2);
						return new int[]{x, y};
					}
					pos--;
				}
			}
		}
		return null;
	}

	private int shiftRow(int dx, boolean justCheck) {
		int shiftCount = 0;
		for (int y = 0; y < imageGrid.getRowCount(); y++) {
			int emptyCount = 0;
			boolean first = true, mergedLast = false;
			for (int x = (dx < 0 ? 0 : imageGrid.getColumnCount() - 1); x >= 0 && x < imageGrid.getColumnCount(); x -= dx) {
				boolean[] shiftStep = shiftStep(x, y, dx, 0, emptyCount, first, mergedLast, justCheck);
				if (shiftStep == null) {
					return -1;
				}
				shiftCount += (shiftStep[0] ? 1 : 0);
				emptyCount += (shiftStep[1] ? 1 : 0);
				mergedLast = (shiftStep[0] && shiftStep[1]);
				first = false;
			}
		}
		return shiftCount;
	}

	private boolean[] shiftStep(int x, int y, int dx, int dy, int emptyCount, boolean first, boolean mergedLast, boolean justCheck) {
		int value = getCell(x, y);
		int nextX = x + (emptyCount + 1) * dx, nextY = y + (emptyCount + 1) * dy;
		if (value == 0) {
			return (justCheck ? null : new boolean[]{false, true});
		} else if (first) {
		} else if ((! mergedLast) && isValidXY(nextX, nextY) && value == getCell(nextX, nextY)) {
			if (justCheck) {
				return null;
			}
			setCell(x + (emptyCount + 1) * dx, y + (emptyCount + 1) * dy, value * 2);
			points += value * 2;
			setCell(x, y, 0);
			return new boolean[]{true, true};
		} else if (emptyCount > 0) {
			setCell(x + emptyCount * dx, y + emptyCount * dy, value);
			setCell(x, y, 0);
			return new boolean[]{true, false};
		}
		return new boolean[]{false, false};
	}

	private int shiftColumn(int dy, boolean justCheck) {
		int shiftCount = 0;
		for (int x = 0; x < imageGrid.getColumnCount(); x++) {
			int emptyCount = 0;
			boolean first = true, mergedLast = false;
			for (int y = (dy < 0 ? 0 : imageGrid.getRowCount() - 1); y >= 0 && y < imageGrid.getRowCount(); y -= dy) {
				boolean[] shiftStep = shiftStep(x, y, 0, dy, emptyCount, first, mergedLast, justCheck);
				if (shiftStep == null) {
					return -1;
				}
				shiftCount += (shiftStep[0] ? 1 : 0);
				emptyCount += (shiftStep[1] ? 1 : 0);
				mergedLast = (shiftStep[0] && shiftStep[1]);
				first = false;
			}
		}
		return shiftCount;
	}
	
	@FXML
	private Label statusLabel;

	private void updateStatus() {
		statusLabel.setText("Point: " + points);
	}

	@Override
	protected boolean keyPressed(int dx, int dy) {
		int shiftCount = 0;
		if (dx * dy == 0) {
			if (dx != 0) {
				shiftCount = shiftRow(dx, false);
			} else if (dy != 0) {
				shiftCount = shiftColumn(dy, false);
			}
		}
		updateStatus();
		if (shiftCount > 0) {
			putRandom();
		}
		return true;
	}

	protected boolean isFinished() {
		return emptyCount == 0 && shiftRow(-1, true) == 0 && shiftRow(1, true) == 0 && shiftColumn(-1, true) == 0 && shiftColumn(-1, true) == 0;
	}

	public static void main(String[] args) throws Exception {
		launch(Game2048.class);
	}
}
