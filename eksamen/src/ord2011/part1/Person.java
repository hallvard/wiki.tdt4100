package ord2011.part1;

import java.util.Date;

public class Person {
	
	private Gender gender;
	private Date date;
	
	public Person(Gender gender) {
		this.gender = gender;
	}
	
	public Gender getGender() {
		return gender;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) throws Exception {
		if (pnr != null) {
			throw new Exception("Kan ikke endre dato når pnr er satt");
		}
		this.date = date;
	}

	private String pnr;

	public String getPnr() {
		return pnr;
	}
	
	public void setPnr(String pnr) {
		if (isValidPnr(pnr)) {
			this.pnr = pnr;
		}
	}

	private boolean isValidPnr(String pnr2) {
		return isValidPnr1(pnr2) && checkDatePart(pnr2) && checkGenderPart(pnr2) && isValidPnr4(pnr2);
	}
	private boolean isValidPnr1(String pnr2) {
		return pnr.length() == 11;
	}
	private boolean checkDigits(String s, int pos, int n) {
		return (toDigit(s.charAt(pos)) == n / 10 && (s.charAt(pos + 1) - '0') == (n % 10));
	}
	private boolean checkDatePart(String pnr2) {
		return checkDigits(pnr2, 0, date.getDay()) && checkDigits(pnr2, 2, date.getMonth()) && checkDigits(pnr2, 4, date.getYear());
	}
	private boolean checkGenderPart(String pnr2) {
		if (isOddetall(pnr2.charAt(8)) == 0) {
			return gender == Gender.FEMALE;
		} else {
			return gender == Gender.MALE;
		}
	}
	protected int isOddetall(char c) {
		return toDigit(c) % 2;
	}
	protected int toDigit(char c) {
		return c - '0';
	}
	
	private int[] fer = {3, 7 ,6, 1, 8, 9, 4, 5, 2};
	private int[] ger = {3, 7 ,6, 1, 8, 9, 4, 5, 2};
	
	private boolean isValidPnr4(String pnr2) {
		int k1 = knr(pnr2, fer);
		int k2 = knr(pnr2, ger);
		return k1 < 10 && k2 < 10;
	}
	private int knr(String s, int[] ints) {
		return 11 - (vs(s, ints) % 11);
	}
	private int vs(String s, int[] ints) {
		int sum = 0;
		for (int i = 0; i < ints.length; i++) {
			sum = sum + toDigit(s.charAt(i)) * ints[i];
		}
		return sum;
	}
	
	public static void main(String[] args) throws Exception {
		Person person = new Person(Gender.FEMALE);
		person.setPnr("12345678");
		try {
			person.setDate(null);
		} catch (Exception e) {
		}
	}
}
