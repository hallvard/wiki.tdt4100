package kont2009.fourinarow;


public class ArrayBoard extends AbstractBoard {

	private Piece[][] board;
	
	public ArrayBoard() {
		board = new Piece[BOARD_HEIGHT][BOARD_WIDTH];
	}
	
	public Piece getPiece(int x, int y) {
		if (x >= 0 && x < BOARD_WIDTH && y >= 0 && y < BOARD_HEIGHT) {
			return board[y][x];
		} else {
			return null;
		}
	}
	
	public int getDropRow(int x) {
		if (x < 0 || x >= BOARD_WIDTH) {
			return -1;
		}
		for (int y = 0; y < board.length; y++) {
			if (board[y][x] == null) {
				return y;
			}
		}
		return -1;
	}

	public boolean isLegalDrop(int x) {
		return getDropRow(x) >= 0;
	}

	public int dropPiece(Piece piece, int x) {
		int y = getDropRow(x);
		if (y >= 0) {
			board[y][x] = piece;
			fireBoardChanged(x, y);
		}
		return y;
	}

	public void undropPiece(int x, int y) {
		if (x >= 0 && x < BOARD_WIDTH && y >= 0 && y < BOARD_HEIGHT) {
			board[y][x] = null;
		}
	}
}
