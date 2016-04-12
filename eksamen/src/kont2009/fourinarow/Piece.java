package kont2009.fourinarow;

public enum Piece {
	LIGHT, DARK;
	
	public Piece other() {
		switch (this) {
		case LIGHT: return DARK;
		case DARK: return LIGHT;
		}
		return null;
	}
}
