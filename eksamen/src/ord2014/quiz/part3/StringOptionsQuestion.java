package ord2014.quiz.part3;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringOptionsQuestion extends StringQuestion {

	private List<String> options;
	
	public StringOptionsQuestion(String question, String answer, Collection<String> options) {
		super(question, answer);
		this.options = new ArrayList<String>(options);
		if (! options.contains(answer)) {
			throw new IllegalArgumentException("Svaret er ikke et av alternativene!");
		}
		this.options = new ArrayList<String>(options);
	}

	@Override
	public void askQuestion(PrintStream out) {
		super.askQuestion(out);
		int num = 1;
		for (String option : options) {
			out.println(num + ". " + option);
			num++;
		}
	}

	@Override
	public boolean checkAnswer(String answer) {
		try {
			int num = Integer.valueOf(answer);
			if (super.checkAnswer(options.get(num - 1))) {
				return true;
			}
		} catch (IndexOutOfBoundsException e) {
		} catch (NumberFormatException e) {
		}
		return super.checkAnswer(answer);
	}
}
