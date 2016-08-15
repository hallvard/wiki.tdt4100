package kont2016;

/**
 * A Course has a course code (String) that identifies it.
 * A Course gives a number of credits (a double) if you pass the exam.
 * A Course is given in a specific year (int) and semester ('F' for Fall and 'S' for Spring)
 *
 */
public class Course implements Comparable<Course> {
	
	private final String code;

	public Course(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	private double credits;
	
	public double getCredits() {
		return credits;
	}
	
	public void setCredits(double credits) {
		this.credits = credits;
	}
	
	private int year;
	private char semester; // F(all) eller S(pring)
	
	public int getYear() {
		return year;
	}
	
	public char getSemester() {
		return semester;
	}

	/**
	 * Gets the time this Course is given, in the format <semester><year> ({@link setTime)
	 * E.g. if the semester is 'S' and the year is 2016, it should return S2016.
	 * @return The semester formatted as <semester><year>
	 */
	public String getTime() {
		return String.valueOf(getSemester()) + year;
	}

	public static void checkTime(String time) throws IllegalArgumentException {
		char c0 = Character.toUpperCase(time.charAt(0));
		if (c0 != 'S' && c0 != 'F') {
			throw new IllegalArgumentException("Semester must be either F(all) or S(pring)");
		}
		Integer.valueOf(time.substring(1));
	}
	
	/**
	 * Sets the time that this Course is given., i.e. both the semester and year.
	 * The format is the semester (character) followed by the year.
	 * The year may be shortened to two digits: If it is below 50 the year is in this millenium,
	 * otherwise it is in the previous.
	 * E.g. S16 means Spring 2016, while F86 means Fall 1986.
	 * @param time The time in the format <semester><year>
	 * @throws IllegalArgumentException if the format is incorrect
	 */
	public void setTime(String time) {
		checkTime(time);
		int year = Integer.valueOf(time.substring(1));
		if (year < 100) {
			year = (year < 50 ? 2000 : 1900) + year;
		}
		this.semester = Character.toUpperCase(time.charAt(0));
		this.year = year;
	}

	//
	
	/**
	 * Compares based on the time given, earlier means smaller.
	 */
	@Override
	public int compareTo(Course other) {
		if (year != other.year) {
			return year - other.year;
		}
		return (other.semester - semester);
	}
}
