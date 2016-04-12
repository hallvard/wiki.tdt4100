package kont2009.part1;

public class Palindrome {

	public static boolean isPalindrome1(String s, boolean ignoreCase) {
		String reversed = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			reversed += s.charAt(i);
		}
		return (ignoreCase ? s.equalsIgnoreCase(reversed) : s.equals(reversed));
	}

	public static boolean isPalindrome2(String s, boolean ignoreCase) {
		if (ignoreCase) {
			s = s.toLowerCase();
		}
		StringBuffer reversed = new StringBuffer(s).reverse();
		return reversed.toString().equals(s);
	}
}
