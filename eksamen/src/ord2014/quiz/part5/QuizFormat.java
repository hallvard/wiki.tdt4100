package ord2014.quiz.part5;

import java.io.IOException;
import java.io.Reader;
import java.util.Collection;

import ord2014.quiz.part3.Question;

public interface QuizFormat {
	public Collection<Question> read(Reader input) throws IOException;
}
