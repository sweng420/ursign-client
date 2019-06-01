package quiz;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuizEngine {

    private static final int QUESTIONS = 3;

    private static ArrayList<QuizEngineFlashcard> testStrings = new ArrayList<>();
    private static Quiz cards = new Quiz(0);


    public static void main(String[] args) throws IOException {

        // create flashcards
        addCards();

        // create questions
        testStrings = cards.createQuestions(QUESTIONS);

        // ask questions and check answers
        for (int i = 0; i < QUESTIONS; i++) {

            // write answer for user to copy
            System.out.println( testStrings.get(i).getInteger() );

            // Read answer in from user
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String answer = reader.readLine();

            // check if user answer was right
            if (testStrings.get(i).checkStringAnswer(answer)) {
                System.out.println("RIGHT");
            } else {
                System.out.println("WRONG");
            }
        }

        testStrings.clear();

    }

    public static void addCards(){
        // create flashcards
        cards.getFlashcards().add(new Question());
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
        cards.getFlashcards().add(new QuizEngineFlashcard("eleven", 11, 3));
    }    

}