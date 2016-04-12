package ord2014.quiz.part5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;

import ord2014.quiz.part3.BooleanQuestion;
import ord2014.quiz.part3.Question;
import ord2014.quiz.part3.StringOptionsQuestion;
import ord2014.quiz.part3.StringQuestion;

public class StandardQuizFormat implements QuizFormat {

	public Collection<Question> read(Reader input) throws IOException {
		Collection<Question> questions = new ArrayList<Question>();
		BufferedReader reader = new BufferedReader(input);
		while (reader.ready()) {
			String question = reader.readLine();
			if (question == null || question.trim().length() == 0) {
				break;
			}
			String answer = reader.readLine();
			Collection<String> options = new ArrayList<String>();
			while (reader.ready()) {
				String line = reader.readLine();
				if (line == null || line.trim().length() == 0) {
					break;
				}
				options.add(line);
			}
			if (answer.equals("yes")) {
				questions.add(new BooleanQuestion(question, true));
			} else if (answer.equals("no")) {
				questions.add(new BooleanQuestion(question, false));
			} else if (options.size() > 0) {
				questions.add(new StringOptionsQuestion(question, answer, options));
			} else {
				questions.add(new StringQuestion(question, answer));
			}
		}
		return questions;
	}
}
