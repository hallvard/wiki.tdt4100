package ord2012.wordlist;

import java.util.Collection;

public interface Words {
	public boolean containsWord(String s);
	public Collection<String> getWordsStartingWith(String s, int maxLength);
}
