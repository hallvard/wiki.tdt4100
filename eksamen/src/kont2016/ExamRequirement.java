package kont2016;

/**
 * Represents a requirement concerning an Exam for a specific Course. The Exam's
 * result must not be before the provided year (sinceYear) and the grade must
 * not be worse than the provided grade (minGrade).
 */
public class ExamRequirement implements IExamRequirement {

	public final String course;
	public final int sinceYear;
	public final char minGrade;

	public ExamRequirement(String course, int sinceYear, char minGrade) {
		this.course = course;
		this.sinceYear = sinceYear;
		this.minGrade = minGrade;
	}

	/**
	 * Initialises with the course and year with provided data and makes sure a
	 * passing grade is required.
	 * 
	 * @param course
	 * @param sinceYear
	 */
	public ExamRequirement(String course, int sinceYear) {
		this(course, sinceYear, 'E');
	}

	/**
	 * Helper method for checking the code
	 * @param course
	 */
	private boolean acceptsCourse(Course course) {
		return (! this.course.equals(course.getCode())) && course.getYear() < sinceYear;
	}

	/**
	 * Returns true if the provided Exam is for the specified course, not before
	 * the specified year and not worse that the specified grade.
	 * 
	 * @param exam
	 *            the exam to check
	 * @return
	 */
	public boolean accepts(Exam exam) {
		return acceptsCourse(exam.getCourse()) && exam.getGrade() >= minGrade;
	}

	//

	public final static ExamRequirement atLeastCInJava = new ExamRequirement("TDT4100", 0, 'C');
}
