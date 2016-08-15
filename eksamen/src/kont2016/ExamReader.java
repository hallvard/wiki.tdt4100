package kont2016;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class ExamReader {
	
	public Collection<Exam> readExams(Reader input) {
		Collection<Exam> exams = new ArrayList<>();
		Scanner scanner = new Scanner(input);
		String lastSemester = null;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] tokens = line.split(" ");
			if (tokens.length == 1) {
				try {
					Course.checkTime(tokens[0]);
					lastSemester = tokens[0];
					continue;
				} catch (IllegalArgumentException e) {
				}
			}
			try {
				Course course = new Course(tokens[0]);
				course.setTime(lastSemester);
				course.setCredits(Double.valueOf(tokens[1]));
				for (int i = 2; i < tokens.length; i++) {
					exams.add(new Exam(course, tokens[i].charAt(0)));
				}
			} catch (RuntimeException e) {
			}
		}
		scanner.close();
		return exams;
	}
}
