package ord2014.quiz.part3;

import java.io.PrintStream;

public abstract class Question {

	private String question;

	protected Question(String question) {
		this.question = question;
	}

	public void askQuestion(PrintStream out) {
		out.print(question);
	}
	
	public abstract boolean checkAnswer(String answer);
}
