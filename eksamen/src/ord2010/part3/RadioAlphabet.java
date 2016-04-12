package ord2010.part3;

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
 * }
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
		}
	}

	public void removeWord(char c) {
		int pos = pos(c);
		if (pos >= 0) {
			alphabet.remove(pos);
		}
	}
	
	/*
	 * @startuml
	 * class class1 {
	 *   -field1
	 *   ~field2
	 *    field3
	 *   #field4
	 *   +field5
	 *   -method1()
	 *   ~method2()
	 *    method3()
	 *   #method4()
	 *   +method5()
	 * }
	 * @enduml
	 */
}
