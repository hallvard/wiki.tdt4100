package ord2010.part5;

import ord2010.part3.RadioAlphabet;
import junit.framework.TestCase;

public class RadioAlphabetTest extends TestCase {

	private static String alphabet = "hotel-alfa-bravo-charlie-delta-echo-foxtrot-golf-india-juliet-kilo-lima-mike-november-oscar-papa-quebec-romeo-sierra-tango-uniform-victor-whiskey-xray-yankee-zulu";
	
	private RadioAlphabet radioAlphabet;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		radioAlphabet = new RadioAlphabet(alphabet, "-");
	}

	public void testConvertChar() {
		assertEquals("alfa", radioAlphabet.convert('a'));
		assertEquals("hotel", radioAlphabet.convert('h'));
		assertEquals("zulu", radioAlphabet.convert('z'));
		assertNull(radioAlphabet.convert('!'));
	}
	
	public void testConvertString() {
		assertEquals("hotel alfa lima lima victor alfa romeo delta", radioAlphabet.convert("hallvard"));
		assertEquals("hotel alfa lima alfa lima", radioAlphabet.convert("!h!a!l!a!l?"));
	}

	public void testSetWord() {
		assertEquals("hotel alfa lima lima victor alfa romeo delta", radioAlphabet.convert("hallvard"));
		radioAlphabet.setWord("lala");
		assertEquals("hotel alfa lala lala victor alfa romeo delta", radioAlphabet.convert("hallvard"));
		radioAlphabet.setWord("åring");
		assertEquals("hotel victor alfa romeo delta", radioAlphabet.convert("håvard"));
	}

	public void testRemoveWord() {
		assertEquals("hotel alfa lima lima victor alfa romeo delta", radioAlphabet.convert("hallvard"));
		radioAlphabet.removeWord('l');
		assertEquals("hotel alfa victor alfa romeo delta", radioAlphabet.convert("hallvard"));
	}
}
