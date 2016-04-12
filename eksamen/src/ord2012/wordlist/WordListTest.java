package ord2012.wordlist;

public class WordListTest extends AbstractWordListTest {

	@Override
	protected void setUpWords(String[] words, String[] mutableWords) {
		this.words = new ReadOnlyWordList(words);
		this.mutableWords = new WordList();
		for (int i = 0; i < mutableWords.length; i++) {
			this.mutableWords.addWord(mutableWords[i]);
		}
	}
}
