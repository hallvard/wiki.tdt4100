package kont2012.wordlist;

import java.util.Collection;

public interface Words {
	public boolean containsWord(String s);
	public Collection<String> getWordsStartingWith(String s);
	
	public boolean addWord(String s);
	public boolean removeWord(String s);
}
