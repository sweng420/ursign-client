package quiz;

import java.util.ArrayList;
import java.util.List;

import ursign.ursign_client.Multimedia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuizEngine {

    private static final int QUESTIONS = 6;

    private static List<Question> setup = new ArrayList<>();
    private static Quiz cards = new Quiz(0);

    public static void main(String[] args) throws IOException {

        // create flashcards
        // addCards();
    	QuizParser qp = new QuizParser("SignBasicsQuiz.xml");
    	cards = qp.getQuiz();

        // create questions
        setup = cards.createQuestions(QUESTIONS);

        // ask questions and check answers
        for (int i = 0; i < QUESTIONS; i++) {

            // write answer for user to copy
        	System.out.println(setup);
            for(Multimedia m : setup.get(i).getContent()) {
            	System.out.println(m.toString());
            }

            // Read answer in from user
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String answer = reader.readLine();

            // check if user answer was right
            if (setup.get(i).checkAnswer(answer)) {
                System.out.println("RIGHT");
            } else {
                System.out.println("WRONG");
            }
        }

        setup.clear();

    }

    public static void addCards(){
    	
        // create flashcards
        /*cards.getFlashcards().add(new Question());
        cards.getFlashcards().add(new Question("one", 1, 3));
        cards.getFlashcards().add(new QuizEngineFlashcard("two", 2, 1));
        cards.getFlashcards().add(new QuizEngineFlashcard("three", 3, 3));
        cards.getFlashcards().add(new QuizEngineFlashcard("four", 4, 3));
        cards.getFlashcards().add(new QuizEngineFlashcard("five", 5, 3));
        cards.getFlashcards().add(new QuizEngineFlashcard("six", 6, 3));
        cards.getFlashcards().add(new QuizEngineFlashcard("seven", 7, 3));
        cards.getFlashcards().add(new QuizEngineFlashcard("eight", 8, 3));
        cards.getFlashcards().add(new QuizEngineFlashcard("nine", 9, 3));
        cards.getFlashcards().add(new QuizEngineFlashcard("ten", 10, 3));
        cards.getFlashcards().add(new QuizEngineFlashcard("eleven", 11, 3));*/
    }    

}