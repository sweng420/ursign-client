package ursign.ursign_client;

import java.util.ArrayList;

public class QuizEngineDeck {
	private static ArrayList<QuizEngineFlashcard> Flashcards = new ArrayList<>();
	private static Integer sum = 0;

	public ArrayList<QuizEngineFlashcard> getFlashcards() {
		return Flashcards;
	}

	public static void setFlashcards(ArrayList<QuizEngineFlashcard> flashcards) {
		Flashcards = flashcards;
	}
	
	private static void getSum() {
        // Sum up all box numbers
        for (int i = 0; i < Flashcards.size(); i++) {
            sum = sum + Flashcards.get(i).getBox();
        }
    }
	
	public static ArrayList<QuizEngineFlashcard> createQuestions(Integer questions) {
		ArrayList<QuizEngineFlashcard> questionList = new ArrayList<>();
		// Sum up all box numbers
        getSum();

        // populate testStrings with weighted flashcards
        for (int i = 0; i < questions; i++) {

            int randomWeight = (int) (Math.random() * sum + 1);

            for (int j = 0; j < Flashcards.size(); j++) {
                randomWeight = randomWeight - Flashcards.get(j).getBox();
                // don't add duplicate questions
                if (randomWeight <= 0 && !questionList.contains(Flashcards.get(j))) {
                	questionList.add(Flashcards.get(j));
                    break;
                }
            }
        }
        
        return questionList;
	}
	
}
