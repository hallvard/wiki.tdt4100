package ord2010.part5;

/*
 * @startuml
 * RadioAlphabet <|-- MorseAlphabet
 * @enduml
 */
public class MorseAlphabet extends RadioAlphabet {
	
	private final static String morseAlphabet = "s... o--- e. t-";
	
	public MorseAlphabet() {
		super(morseAlphabet, " ");
	}

	public String convert(char c) {
		String word = super.convert(c);
		return (word != null ? word.substring(1) : null);
	}

	public void setWord(char c, String word) {
		super.setWord(c + word);
	}
}
