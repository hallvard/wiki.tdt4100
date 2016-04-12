package kont2009.part1;

import junit.framework.TestCase;

public class PalindromeTest extends TestCase {

	public void testIsPalindrome1() {
		assertFalse(Palindrome.isPalindrome1("Java", false));
		assertFalse(Palindrome.isPalindrome1("java", false));
		assertFalse(Palindrome.isPalindrome1("Agnes i senga", false));
		assertTrue(Palindrome.isPalindrome1("agnes i senga", false));

		assertFalse(Palindrome.isPalindrome1("Agnes i senga", false));
		assertTrue(Palindrome.isPalindrome1("Agnes i senga", true));
		assertTrue(Palindrome.isPalindrome1("agnes i senga", false));

		assertFalse(Palindrome.isPalindrome1("Agnes i senga", false));
		assertTrue(Palindrome.isPalindrome1("Agnes i senga", true));
		assertTrue(Palindrome.isPalindrome1("agnes i senga", false));

		assertTrue(Palindrome.isPalindrome1(" ", false));
		assertTrue(Palindrome.isPalindrome1(" ", true));
		assertTrue(Palindrome.isPalindrome1("", false));
		assertTrue(Palindrome.isPalindrome1("", true));
	}
}
