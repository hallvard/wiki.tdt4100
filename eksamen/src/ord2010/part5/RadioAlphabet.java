package ord2010.part5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @startuml
 * skinparam visibilityModifier true
 * class RadioAlphabet {
 * +RadioAlphabet(String alphabet)
 * +boolean converts(char)
 * +String convert(char)
 * +String convert(String)
 * +void setWord(String)
 * +void removeWord(char)
 * +void addRadioAlphabetListener(RadioAlphabetListener)
 * +void removeRadioAlphabetListener(RadioAlphabetListener)
 * #void fireRadioAlphabetChanged(char)
 * -void fireRadioAlphabetChanged(char)
 * }
 * @enduml
 * 
 * @startuml
 * object "#1: Person" as p1
 * object "#2: Person" as p2
 * p1 -> p2
 * @enduml
 */

public class RadioAlphabet {
	
	private List<String> alphabet;
	
	public RadioAlphabet(String alphabet, String separator) {
		this.alphabet = new ArrayList<String>(Arrays.asList(alphabet.split(separator)));
	}

	private int pos(char c) {
		for (int i = 0; i < alphabet.size(); i++) {
			if (alphabet.get(i).charAt(0) == c) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean converts(char c) {
		return pos(c) >= 0;
	}
	
	public String convert(char c) {
		int pos = pos(c);
		return pos >= 0 ? alphabet.get(pos) : null;
	}
	
	public String convert(String word) {
		StringBuffer /* or StringBuilder */ buffer = new StringBuffer();
		for (int i = 0; i < word.length(); i++) {
			String converted = convert(word.charAt(i));
			if (converted != null) {
				buffer.append(converted);
				buffer.append(' ');
			}
		}
		return buffer.toString().trim();
	}

	public void setWord(String word) {
		int pos = pos(word.charAt(0));
		if (pos >= 0) {
			alphabet.set(pos, word);
			// added
			fireRadioAlphabet(word.charAt(0));
		}
	}

	public void removeWord(char c) {
		int pos = pos(c);
		if (pos >= 0) {
			alphabet.remove(pos);
			// added
			fireRadioAlphabet(c);
		}
	}
	
	//
	
	private List<RadioAlphabetListener> listeners = new ArrayList<RadioAlphabetListener>();
	
	public void addRadioAlphabetListener(RadioAlphabetListener listener) {
		listeners.add(listener);
	}

	public void removeRadioAlphabetListener(RadioAlphabetListener listener) {
		listeners.remove(listener);
	}
	
	protected void fireRadioAlphabet(char c) {
		for (RadioAlphabetListener listener : listeners) {
			listener.radioAlphabetChanged(this, c);
		}
	}
}
