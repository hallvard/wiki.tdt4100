package ord2014.quiz.part3;

public class StringQuestion extends Question {

	private String answer;
	
	public StringQuestion(String question, String answer) {
		super(question);
		this.answer = answer;
	}

	@Override
	public boolean checkAnswer(String answer) {
		return this.answer.equals(answer);
	}
}
