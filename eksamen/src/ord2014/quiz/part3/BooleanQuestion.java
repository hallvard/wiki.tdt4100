package ord2014.quiz.part3;

public class BooleanQuestion extends Question {
	
	private boolean answer;

	public BooleanQuestion(String question, boolean answer) {
		super(question);
		this.answer = answer;
	}

	@Override
	public boolean checkAnswer(String answer) {
		return (this.answer ? "yes" : "no").equals(answer);
	}
}
