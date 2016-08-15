package kont2016;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents a person.
 * A Person is (or has been) enrolled in zero, one or more Courses. 
 * A Person has taken zero, one or more Exams for the Courses s/he is enrolled in.
 * The last Exam registered for a Course is the one that counts!
 */
public class Person {

	private final String name;
	
	public Person(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "[Person " + name + "]";
	}

	public String getName() {
		return name;
	}
	
	private List<Course> courses = new ArrayList<Course>();
	
	/**
	 * Adds a Course to this Person,
	 * if no Course is registered for the same year and semester. 
	 * @param course
	 * @return true if the course was added
	 */
	public boolean addCourse(Course course) {
		for (Course existing : courses) {
			if (course.getTime().equals(existing.getTime())) {
				return false;
			}
		}
		courses.add(course);
		return true;
	}
	
	/**
	 * Returns whether this Person has a Course with the given code.
	 * @param code
	 */
	public boolean hasCourse(String code) {
		for (Course course : courses) {
			if (code.equals(course.getCode())) {
				return true;
			}
		}
		return false;
	}

	private Collection<Exam> exams = new ArrayList<Exam>();

	/**
	 * Creates and adds an exam to this Person for the provided Course,
	 * but only if this Person has this Course and
	 * no Exam is already registered for that Course.
	 * @param course
	 * @param grade
	 * @return the newly created and added Exam, or null
	 */
	public Exam addExam(Course course, char grade) {
		if (courses.contains(course)) {
			for (Exam exam : exams) {
				if (exam.getCourse() == course && exam.isPass()) {
					return null;
				}
			}
			Exam exam = new Exam(course, grade);
			exams.add(exam);
			return exam;
		}
		return null;
	}

	/**
	 * Gets the exam that was registered last for the provided course code.
	 * This is the exam that counts for that course!
	 * @param course
	 */
	public Exam getLastExam(String code) {
		Exam lastExam = null;
		for (Exam exam : exams) {
			if (code.equals(exam.getCourse().getCode())) {
				lastExam = exam;
			}
		}
		return lastExam;
	}

	/**
	 * Returns true of this Person has passed the Course for the provided code.
	 * @param code
	 */
	public boolean hasPassed(String code) {
		Exam exam = getLastExam(code);
		return exam != null && exam.isPass();
	}

	/**
	 * Counts the credits this Person has earned.
	 * @param code
	 */
	public double countCredits() {
		double sum = 0;
		Collection<String> codes = new ArrayList<String>();
		for (Exam exam : exams) {
			String code = exam.getCourse().getCode();
			if (! codes.contains(code)) {
				codes.add(code);
			}
		}
		for (String code : codes) {
			Exam exam = getLastExam(code);
			if (exam.isPass()) {
				sum += exam.getCourse().getCredits();
			}
		}
		return sum;
	}
}
