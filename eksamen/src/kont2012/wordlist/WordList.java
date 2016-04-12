package kont2012.wordlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WordList implements Words {

	private Collection<String> wordList = new ArrayList<String>();

	public WordList(String...words) {
		for (int i = 0; i < words.length; i++) {
			addWord(words[i]);
		}
	}

	public int getWordCount() {
		return wordList.size();
	}
	
	public boolean containsWord(String s) {
		return wordList.contains(s);
	}

	public Collection<String> getWordsStartingWith(String s) {
		Collection<String> matchingWords = new ArrayList<String>();
		for (String word : wordList) {
			if (word.startsWith(s)) {
				matchingWords.add(word);
			}
		}
		return matchingWords;
	}

	public boolean addWord(String s) {
		s = s.trim();
		return (s.length() > 0 && (! wordList.contains(s)) ? wordList.add(s) : false);
	}

	public boolean removeWord(String s) {
		return wordList.remove(s);
	}

	public boolean removeWordStartingWith(String s) {
		return wordList.removeAll(getWordsStartingWith(s));
	}

	public String getPrefix(String word, String suffix) {
		if (word.endsWith(suffix)) {
			return word.substring(0, word.length() - suffix.length());
		}
		return null;
	}
	
	public boolean hasSuffixes(String prefix, Collection<String> suffixes) {
		for (String suffix : suffixes) {
			if (! containsWord(prefix + suffix)) {
				return false;
			}
		}
		return true;
	}

	public List<String> findPrefixes(List<String> suffixes) {
		List<String> prefixes = new ArrayList<String>();
		for (String word : wordList) {
			String prefix = getPrefix(word, suffixes.get(0));
			if (prefix != null && hasSuffixes(prefix, suffixes)) {
				prefixes.add(prefix);
			}
		}
		return prefixes;
	}
	
	public void read(InputStream input) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String line = null;
		while ((line = reader.readLine()) != null) {
			int pos = line.indexOf('#');
			if (pos >= 0) {
				line = line.substring(0, pos);
			}
			pos = line.length();
			pos = line.indexOf('-');
			if (pos < 0) {
				addWord(line);
			} else {
				String prefix = line.substring(0, pos).trim();
				String[] suffixes = line.substring(pos + 1).split(",");
				for (int i = 0; i < suffixes.length; i++) {
					addWord(prefix + suffixes[i].trim());
				}
			}
		}
	}
}
