package ord2012.wordlist;

import java.util.Arrays;

import junit.framework.TestCase;

public abstract class AbstractWordListTest extends TestCase {

	protected Words words;
	protected MutableWords mutableWords;

	protected abstract void setUpWords(String[] words, String[] mutableWords);
	
	protected void setUp() {
		setUpWords("one two three".split(" "), "four five".split(" "));
	}

	public void testContainsWord() {
		assertTrue(words.containsWord("one"));
		assertTrue(words.containsWord("two"));
		assertTrue(words.containsWord("three"));
		assertFalse(words.containsWord("x"));
		assertTrue(mutableWords.containsWord("four"));
		assertTrue(mutableWords.containsWord("five"));
		assertFalse(mutableWords.containsWord("x"));
	}
	
	public void testGetWordsStartingWith() {
		words.getWordsStartingWith("t", -1).equals(Arrays.asList("two", "three"));
		words.getWordsStartingWith("t", 3).equals(Arrays.asList("two"));
		words.getWordsStartingWith("t", 5).equals(Arrays.asList("two", "three"));

		words.getWordsStartingWith("t", -1).equals(Arrays.asList("two", "three"));
		words.getWordsStartingWith("t", 3).equals(Arrays.asList("two"));
		words.getWordsStartingWith("t", 5).equals(Arrays.asList("two", "three"));
	}
	
	public void testAddWord() {
		mutableWords.addWord("five");
		assertTrue(mutableWords.containsWord("five"));
		mutableWords.addWord("six");
		assertTrue(mutableWords.containsWord("six"));
	}
	
	public void testRemoveWordsStartingWith() {
		mutableWords.removeWordStartingWith("t");
		assertTrue(mutableWords.containsWord("four"));
		assertTrue(mutableWords.containsWord("five"));
		mutableWords.removeWordStartingWith("f");
		assertFalse(mutableWords.containsWord("four"));
		assertFalse(mutableWords.containsWord("five"));
	}
}
