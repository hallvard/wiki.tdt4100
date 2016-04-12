package ord2014.quiz.part1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Quiz {

	private Collection<Question> questions = new ArrayList<Question>();
	
	public void addQuestion(Question question) {
		questions.add(question);
	}
	
	public void init() {
		addQuestion(new Question("Hva slags ost er månen laget av?", "Roquefort", "Camembert", "Roquefort", "Brie"));
		addQuestion(new Question("Hva heter hovedstaden i Norge?", "Oslo"));
		addQuestion(new Question("Hva er det tredje desimalet i pi?", "1"));
		addQuestion(new Question("Er dette vanskelig?", "ja", "ja", "nei"));
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);
		for (Question question : questions) {
			question.askQuestion(System.out);
			while (scanner.hasNextLine()) {
				String answer = scanner.nextLine();
				if (question.checkAnswer(answer)) {
					break;
				}
				System.out.println("Feil, prøv igjen");
			}
		}
		scanner.close();
	}

	public static void main(String[] args) {
		Quiz quiz = new Quiz();
		quiz.init();
		quiz.run();
	}
}
