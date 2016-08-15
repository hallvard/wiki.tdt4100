package kont2016;

/**
 * Represents a requirement concerning an Exam.
 */
public interface IExamRequirement {

	public boolean accepts(Exam exam);

	//

	public static IExamRequirement atLeastCInJave =
			(exam) -> "TDT4100".equals(exam.getCourse().getCode()) && exam.getGrade() >= 'C';
}
