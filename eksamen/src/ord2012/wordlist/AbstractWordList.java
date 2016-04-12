package ord2012.wordlist;

import java.util.ArrayList;
import java.util.Collection;

public class AbstractWordList implements Words {

	private Collection<String> wordList = new ArrayList<String>();
	
	public boolean containsWord(String s) {
		return wordList.contains(s);
	}

	public Collection<String> getWordsStartingWith(String s, int maxLength) {
		Collection<String> matchingWords = new ArrayList<String>();
		for (String word : wordList) {
			if (word.startsWith(s) && (maxLength < 0 || word.length() <= maxLength)) {
				matchingWords.add(word);
			}
		}
		return matchingWords;
	}

	protected void addWord(String s) {
		wordList.add(s);
	}

	protected void removeWordStartingWith(String s) {
		wordList.removeAll(getWordsStartingWith(s, -1));
	}
}
