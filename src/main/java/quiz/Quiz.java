package quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Quiz {
	private List<Question> questions;
	private int id;

	Quiz(int id){
		this.id = id;
		this.questions=new ArrayList<Question>();
	}
	
	public List<Question> getQuestions(){
		return questions;
	}
	
	public void setQuestions(List<Question> questions) {
		  if (questions == null) {
		    throw new NullPointerException("questions must not be null");
		  }
		  this.questions = questions;
		}
	
	public void addQuestion(Question question) {
		questions.add(question);
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	@Override 
	public String toString() {
		
		return id+questions.toString();
	}

	private Integer sum = 0;

	
	private void getSum() {
        // Sum up all box numbers
        for (int i = 0; i < questions.size(); i++) {
            sum = sum + questions.get(i).getBox();
        }
    }
	
	public ArrayList<Question> createQuestions(Integer qNum) {
		ArrayList<Question> questionList = new ArrayList<>();
		// Sum up all box numbers
        getSum();

        // populate testStrings with weighted flashcards
        for (int i = 0; i < qNum; i++) {

            int randomWeight = (int) (Math.random() * sum + 1);

            for (int j = 0; j < questions.size(); j++) {
                randomWeight = randomWeight - questions.get(j).getBox();
                // don't add duplicate questions
                if (randomWeight <= 0 && !questionList.contains(questions.get(j))) {
                	System.out.println("adding "+questions.get(j));
                	questionList.add(questions.get(j));
                    break;
                }
            }
        }
        while(questionList.size() != qNum) {
        	Collections.shuffle(questions);
        	for(Question q : questions) {
        		if (!questionList.contains(q)) {
                	System.out.println("adding "+q);
                	questionList.add(q);
                }
        	}
        }
        
        return questionList;
	}
	
}
