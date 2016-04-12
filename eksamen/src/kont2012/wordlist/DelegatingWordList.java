package kont2012.wordlist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DelegatingWordList implements Words {

	private Words words1, words2;
	
	public DelegatingWordList(Words words1, Words words2) {
		this.words1 = words1;
		this.words2 = words2;
	}

	public boolean containsWord(String s) {
		return words1.containsWord(s) || words2.containsWord(s);
	}

	public Collection<String> getWordsStartingWith(String s) {
		List<String> matchingWords = new ArrayList<String>();
		matchingWords.addAll(words1.getWordsStartingWith(s));
		matchingWords.addAll(words2.getWordsStartingWith(s));
		return matchingWords;
	}

	public boolean addWord(String s) {
		if (! containsWord(s)) {
			words1.addWord(s);
			return true;
		}
		return false;
	}

	public boolean removeWord(String s) {
		return words1.removeWord(s) | words2.removeWord(s);
	}
}
