package kont2009.fourinarow;

import java.util.ArrayList;
import java.util.List;

public class ArrayListBoard extends AbstractBoard {

	private List[] board;
	
	/**
	 * Initializes the board.
	 * Each column is represented by an ArrayList, and the board is an array of such columns.
	 */
	public ArrayListBoard() {
		board = new List[BOARD_WIDTH];
		for (int i = 0; i < board.length; i++) {
			board[i] = new ArrayList<Piece>(BOARD_HEIGHT);
		}
	}
	
	/**
	 * Returns the piece at position x, y or null of there is no piece there.
	 * x is the column and must be between 0 and 6.
	 * y is the row and must be between 0 and 5. Note that 0 is the bottom row and 5 is the top row. 
	 */
	public Piece getPiece(int x, int y) {
		if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {
			return null;
		}
		List<Piece> column = board[x];
		return (column.size() > y ? column.get(y) : null);
	}
	
	/**
	 * Returns the row that a piece dropped in column x will fall down to, or -1 if the column is full.
	 * E.g. if column 3 is empty getDropRow will return 0 (the bottom row).
	 */
	public int getDropRow(int x) {
		if (x < 0 || x >= BOARD_WIDTH) {
			return -1;
		}
		List<Piece> column = board[x];
		int y = column.size();
		return (y < BOARD_HEIGHT - 1 ? y : -1);
	}

	/**
	 * Returns true if it is legal to drop a piece in column x, false otherwise.
	 */
	public boolean isLegalDrop(int x) {
		return getDropRow(x) >= 0;
	}

	/**
	 * Drops piece into column x
	 */
	public int dropPiece(Piece piece, int x) {
		int y = getDropRow(x);
		if (y >= 0) {
			board[x].add(piece);
			fireBoardChanged(x, y);
		}
		return y;
	}

	/**
	 * Undoes a drop into column x at row y
	 */
	public void undropPiece(int x, int y) {
		if (x >= 0 && x < BOARD_WIDTH && y >= 0 && y < BOARD_HEIGHT) {
			List<Piece> column = board[x];
			if (column.size() == y + 1) {
				column.remove(y);
			}
		}
	}
}
