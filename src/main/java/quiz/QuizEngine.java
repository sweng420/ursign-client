package quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizEngine {

	private List<Question> setup = new ArrayList<>();
	private int state = 0;
	
	public QuizEngine(int qCount, String file) {
	    Quiz cards = new Quiz(0);
	
	        // create flashcards
	        // addCards();
	    	QuizParser qp = new QuizParser(file);
	    	qp.parse();
	    	cards = qp.getQuiz();
	    	//System.out.println("kkk"+cards.getQuestions().size());
	        // create questions
	        setup = cards.createQuestions(qCount);
	        System.out.println("lll");
	}
	
	public List<Question> getSetup() {
		return setup;
	}
	
	public Question getCurrentQuestion() {
		return setup.get(state-1);
	}
	
	public Question getNextQuestion() {
		if(!isOnLast()){
			return setup.get(state++);
		}
		return setup.get(0);
	}
	
	public boolean isOnLast() {
		return state == setup.size();
	}
}