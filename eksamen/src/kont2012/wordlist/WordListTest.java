package kont2012.wordlist;

import java.io.IOException;
import java.io.StringBufferInputStream;
import java.util.Arrays;
import java.util.Collection;

import junit.framework.TestCase;

public class WordListTest extends TestCase {

	protected WordList words;
	protected WordList mutableWords;

	protected void setUp() {
		setUpWords("one two three".split(" "), "four five".split(" "));
	}
	
	protected void setUpWords(String[] words, String[] mutableWords) {
		this.words = new WordList(words);
		this.mutableWords = new WordList();
		for (int i = 0; i < mutableWords.length; i++) {
			this.mutableWords.addWord(mutableWords[i]);
		}
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
		assertEquals(Arrays.asList("two", "three"), words.getWordsStartingWith("t"));
	}
	
	public void testAddWord() {
		assertTrue(mutableWords.addWord("six"));
		assertTrue(mutableWords.containsWord("six"));
		assertFalse(mutableWords.addWord("six"));
		assertTrue(mutableWords.containsWord("six"));
		mutableWords.addWord("seven");
		assertTrue(mutableWords.containsWord("seven"));
	}
	
	public void testRemoveWordsStartingWith() {
		mutableWords.removeWordStartingWith("t");
		assertTrue(mutableWords.containsWord("four"));
		assertTrue(mutableWords.containsWord("five"));
		mutableWords.removeWordStartingWith("f");
		assertFalse(mutableWords.containsWord("four"));
		assertFalse(mutableWords.containsWord("five"));
	}
	
	public void testFindPrefixes() {
		WordList wordList = new WordList();
		wordList.addWord("twenty-one");
		wordList.addWord("thirty-one");		
		wordList.addWord("thirty-two");		
		wordList.addWord("fourty-one");		
		wordList.addWord("fourty-two");		
		wordList.addWord("fourty-three");
		
		assertEquals(Arrays.asList("twenty-", "thirty-", "fourty-"), wordList.findPrefixes(Arrays.asList("one")));
		assertEquals(Arrays.asList("thirty-", "fourty-"), wordList.findPrefixes(Arrays.asList("one", "two")));
		assertEquals(Arrays.asList("fourty-"), wordList.findPrefixes(Arrays.asList("one", "two", "three")));
		assertEquals(Arrays.asList(), wordList.findPrefixes(Arrays.asList("one", "two", "three", "four")));
	}
	
	private void testWordList(WordList wordList, Collection<String> words) {
		assertEquals(words.size(), wordList.getWordCount());
		for (String word : words) {
			wordList.containsWord(word);
		}
	}
	
	private void testRead(String line, String... words) {
		WordList wordList = new WordList();
		try {
			wordList.read(new StringBufferInputStream(line));
		} catch (IOException e) {
			fail();
		}
	}

	public void testRead() {
		testRead("# kommentarlinje");
		testRead("java # enkeltordformat: legger “java” inn i lista", "java");
		testRead("2-1,2,3 # prefiks med liste med endelser, legger “21”, “22” og “23” inn i lista", "21", "22", "23");
		testRead("tretti - # prefiks med tom liste av endelser: legger “tretti” inn i lista", "tretti");
	}
}
