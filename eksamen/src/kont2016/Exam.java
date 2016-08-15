package kont2016;

import java.util.Comparator;

/**
 * An Exam is for a specific Course.
 * The Exam's grade is a char 'A'-'F', or the default '\0' if no result is registered, yet.
 *
 */
public class Exam implements Comparable<Exam> {

	private final Course course;
	private char grade;
	
	/**
	 * Initialises an Exam, by setting the course and grade.
	 * The grade can only be set to one of the characters 'A'-'F'.
	 * @param course
	 * @param grade (lower case must be converted to upper)
	 * @throws IllegalArgumentException if the grade is not legal
	 */
	public Exam(Course course, char grade) {
		this.course = course;
		grade = Character.toUpperCase(grade);
		if ("ABCDEF".indexOf(grade) < 0) {
			throw new IllegalArgumentException("Result must be one of the characters A-F");
		}
		this.grade = grade;
	}

	public Course getCourse() {
		return course;
	}	
	
	public char getGrade() {
		return grade;
	}
	
	/**
	 * Tells whether this Exam has a result that is a passing grade.
	 * @return true of this Exam's grade is a passing grade, false otherwise.
	 */
	public boolean isPass() {
		return grade != 'F';
	}
	
	//

	/**
	 * Compares based on the time the Course was given, i.e. same as Course.
	 */
	@Override
	public int compareTo(Exam other) {
		return course.compareTo(other.getCourse());
	}
	
	//

	/**
	 * A Comparator for Exams that compares their grades.
	 */
	public final static Comparator<Exam> EXAM_COMPARATOR = (exam1, exam2) -> exam1.grade - exam2.grade;
}
