package kont2011;

public class Song {

	private static String vowels = "aeiouy¾¿Œ";
	
	public static boolean isVowel(char c) {
		return vowels.indexOf(Character.toLowerCase(c)) >= 0;
	}

	public static String computeVerse(String originalVerse, char v) {
		if (! isVowel(v)) {
			throw new IllegalArgumentException(v + " is not a vowel");
		}
		String verse = "";
		for (int i = 0; i < originalVerse.length(); i++) {
			char c = originalVerse.charAt(i);
			if (! isVowel(c)) {
				verse += c;
			} else if (Character.isUpperCase(c)) {
				verse += Character.toUpperCase(v);
			} else {
				verse += Character.toLowerCase(v);
			}
		}
		return verse;
	}

	public static void writeSong(String originalVerse) {
		for (int i = 0; i < vowels.length(); i++) {
			System.out.println(computeVerse(originalVerse, vowels.charAt(i)));
		}
	}
}
