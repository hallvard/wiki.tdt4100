package ord2010.part5;

import junit.framework.TestCase;

public class MorseRadioAlphabetTest extends TestCase {

	private MorseAlphabet radioAlphabet;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		radioAlphabet = new MorseAlphabet();
	}

	public void testConvertChar() {
		assertEquals(".", radioAlphabet.convert('e'));
		assertEquals("---", radioAlphabet.convert('o'));
		assertEquals(null, radioAlphabet.convert(','));
	}
	
	public void testConvertString() {
		assertEquals("- . ... -", radioAlphabet.convert("test"));
		assertEquals("... --- ...", radioAlphabet.convert("sos"));
		assertEquals("... --- ...", radioAlphabet.convert("sos!"));
	}

	public void testSetWord() {
		assertEquals("... --- ...", radioAlphabet.convert("sos"));
		radioAlphabet.setWord('s', "..");
		assertEquals(".. --- ..", radioAlphabet.convert("sos"));
	}

	public void testRemoveWord() {
		assertEquals("... --- ...", radioAlphabet.convert("sos"));
		radioAlphabet.removeWord('s');
		assertEquals("---", radioAlphabet.convert("sos"));
	}
}
