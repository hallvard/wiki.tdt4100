package ord2012.wordlist;

public interface MutableWords extends Words {
	public void addWord(String s);
	public void removeWordStartingWith(String s);
}
