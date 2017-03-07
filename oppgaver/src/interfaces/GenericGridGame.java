package interfaces;

import java.io.IOException;

public interface GenericGridGame<T> {
	
	public void getInput(String s);

	public boolean canUndo();
	public void undo();

	public boolean canRedo();
	public void redo();

	public void load(String fileName) throws IOException;
	public void save(String fileName) throws IOException;

	public int getColumnCount();
	public int getRowCount();
	
	public T getCell(int col, int row);
	public String[] getImageNames(T t);
}
