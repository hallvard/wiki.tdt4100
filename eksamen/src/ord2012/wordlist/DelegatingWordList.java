package ord2012.wordlist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DelegatingWordList extends WordList implements MutableWords {

	private Words words;
	private MutableWords mutableWords;
	
	public DelegatingWordList(Words words, MutableWords mutableWords) {
		this.words = words;
		this.mutableWords = mutableWords;
	}

	public boolean containsWord(String s) {
		return (words.containsWord(s) && (! removedWords.contains(s))) || mutableWords.containsWord(s);
	}

	public Collection<String> getWordsStartingWith(String s, int maxLength) {
		List<String> matchingWords = new ArrayList<String>();
		matchingWords.addAll(words.getWordsStartingWith(s, maxLength));
		matchingWords.addAll(mutableWords.getWordsStartingWith(s, maxLength));
		return matchingWords;
	}

	public void addWord(String s) {
		mutableWords.addWord(s);
		removedWords.remove(s);
	}

	private List<String> removedWords = new ArrayList<String>();
	
	public void removeWordStartingWith(String s) {
		mutableWords.removeWordStartingWith(s);
		removedWords.addAll(getWordsStartingWith(s, -1));
	}
}
