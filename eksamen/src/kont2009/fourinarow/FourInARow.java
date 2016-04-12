package kont2009.fourinarow;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FourInARow {

	private Board board;
	private Piece turn;

	public FourInARow() {
		board = new ArrayBoard();
		turn = Piece.LIGHT;
	}

	public boolean hasWon(Piece piece, int x, int y) {
		for (Direction direction: Direction.values()) {
			if (board.count(piece, x, y, direction) + board.count(piece, x, y, direction.opposite()) >= 3) {
				return true;
			}
		}
		return false;
	}

	private void printState() {
		System.out.println(" 0123456");
		for (int y = Board.BOARD_HEIGHT - 1; y >= 0; y--) {
			System.out.print(y);
			for (int x = 0; x < Board.BOARD_WIDTH; x++) {
				Piece piece = board.getPiece(x, y);
				System.out.print(piece == null ? '.' : (piece == Piece.LIGHT ? 'o' : 'x'));
			}
			System.out.println();
		}
		System.out.println(" 0123456");
		System.out.println("It is " + turn + "'s turn");
	}

	private boolean dropPiece(int x) {
		int y = board.dropPiece(turn, x);
		if (y >= 0) {
			changes.add(new Drop(x, y));
			if (hasWon(turn, x, y)) {
				return true;
			}
		}
		turn = turn.other();
		return false;
	}

	private List<Drop> changes = new ArrayList<Drop>();
	
	private void undo() {
		if (changes.size() > 0) {
			turn = turn.other();
			Drop lastChange = changes.remove(changes.size() - 1);
			board.undropPiece(lastChange.x, lastChange.y);
		}
	}
	
	public static void main(String[] args) {
		FourInARow fourInARow = new FourInARow();
		Scanner scanner = new Scanner(System.in);
		do {
			fourInARow.printState();
			if (! scanner.hasNextLine()) {
				break;
			}
			String line = scanner.nextLine().trim();
			if (line.length() == 0) {
				break;
			}
			if ("undo".equals(line)) {
				fourInARow.undo();
			} else if (line.length() == 1) {
				int x = line.charAt(0) - '0';
				if (! fourInARow.board.isLegalDrop(x)) {
					System.out.println(line + " is not a valid drop column");
				} else {
					boolean hasWon = fourInARow.dropPiece(x);
					if (hasWon) {
						System.out.println(fourInARow.turn + " has won!");
						break;
					}
				}
			} else {
				System.out.println(line + " is not a legal command");
			}
		} while (true);
	}
}
