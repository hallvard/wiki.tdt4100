package ord2014.quiz.part1;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Question {

	private String question;
	private String answer;
	private List<String> options;

	public Question(String question, String answer, Collection<String> options) {
		this.question = question;
		this.answer = answer;
		if (! options.contains(answer)) {
			throw new IllegalArgumentException("Svaret er ikke et av alternativene!");
		}
		this.options = new ArrayList<String>();
		if (options != null) {
			this.options.addAll(options);
		}
	}

	public Question(String question, String answer, String... options) {
		this(question, answer, Arrays.asList(options));
	}

	public void askQuestion(PrintStream out) {
		out.print(question);
		if (options.size() > 0) {
			out.println("Alternativer:");
			int num = 1;
			for (String option : options) {
				out.println(num + ". " + option);
				num++;
			}
		}
	}
	
	public boolean checkAnswer(String answer) {
		if (options.size() > 0) {
			try {
				int num = Integer.valueOf(answer);
				if (this.answer.equals(options.get(num - 1))) {
					return true;
				}
			} catch (IndexOutOfBoundsException e) {
			} catch (NumberFormatException e) {
			}
		}
		return this.answer.equals(answer);
	}
}
