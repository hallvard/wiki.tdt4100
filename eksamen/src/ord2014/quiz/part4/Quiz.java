package ord2014.quiz.part4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import ord2014.quiz.part3.BooleanQuestion;
import ord2014.quiz.part3.Question;
import ord2014.quiz.part3.StringOptionsQuestion;
import ord2014.quiz.part3.StringQuestion;

public class Quiz {

	private Collection<Question> questions = new ArrayList<Question>();
	
	public void addQuestion(Question question) {
		questions.add(question);
	}
	
	public void init(Reader input) throws IOException {
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
				addQuestion(new BooleanQuestion(question, true));
			} else if (answer.equals("no")) {
				addQuestion(new BooleanQuestion(question, false));
			} else if (options.size() > 0) {
				addQuestion(new StringOptionsQuestion(question, answer, options));
			} else {
				addQuestion(new StringQuestion(question, answer));
			}
		}
	}

	private boolean mode;
	private PrintStream out;
	private Scanner scanner;
	private int correctCount;
	private List<Question> remaining;

	public void start(boolean mode, PrintStream out, InputStream in) {
		this.out = out;
		scanner = new Scanner(in);
		correctCount = 0;
		remaining = new ArrayList<Question>(questions);
	}

	public boolean doQuestion() {
		Question question = remaining.remove(0);
		question.askQuestion(out);
		String answer = scanner.nextLine();
		if (question.checkAnswer(answer)) {
			System.out.println("Riktig");
			correctCount++;
		} else {
			System.out.println("Feil");
			remaining.add(mode ? remaining.size() : 0, question);
		}
		return (remaining != null && remaining.size() == 0);
	}
	
	public int stop() {
		scanner.close();
		return correctCount;
	}
	
	public static void main(String[] args) {
		Quiz quiz = new Quiz();
		try {
			quiz.init(new InputStreamReader(Question.class.getResourceAsStream("sample.txt")));
			quiz.start(true, System.out, System.in);
			while (! quiz.doQuestion());
		} catch (IOException e) {
		} finally {
			quiz.stop();
		}
	}
}
