package kont2009.fourinarow;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBoard implements Board {

	protected boolean isLegal(int x, int y) {
		return x >= 0 && x < BOARD_WIDTH && y >= 0 && y < BOARD_HEIGHT;
	}
	
	public int count(Piece piece, int x, int y, Direction direction) {
		int count = 0;
		x += direction.dx;
		y += direction.dy;
		while (isLegal(x, y) && getPiece(x, y) == piece) {
			count++;
			x += direction.dx;
			y += direction.dy;
		}
		return count;
	}

	private List<BoardListener> listeners = new ArrayList<BoardListener>();
	
	public void addBoardListener(BoardListener listener) {
		listeners.add(listener);
	}

	public void removeBoardListener(BoardListener listener) {
		listeners.remove(listener);
	}
	
	protected void fireBoardChanged(int x, int y) {
		for (BoardListener listener: listeners) {
			listener.boardChanged(x, y, this);
		}
	}	
}
