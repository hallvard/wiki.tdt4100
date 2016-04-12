package kont2009.fourinarow;

public interface Board {

	public int BOARD_WIDTH = 7;
	public int BOARD_HEIGHT = 6;
	
	public Piece getPiece(int x, int y);
	
	public boolean isLegalDrop(int x);
	
	public int dropPiece(Piece piece, int x);
	public void undropPiece(int x, int y);

	public int count(Piece piece, int x, int y, Direction direction);
}
