package ord2012.wordlist;


public class ReadOnlyWordList extends AbstractWordList {
	
	public ReadOnlyWordList(String[] words) {
		for (int i = 0; i < words.length; i++) {
			addWord(words[i]);
		}
	}
}
