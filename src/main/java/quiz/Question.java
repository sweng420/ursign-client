package quiz;

import java.util.ArrayList;
import java.util.List;

import ursign.ursign_client.Multimedia;
import ursign.ursign_client.Util;

public class Question {
	enum Type{
		multiple,
		fill,
		anki,
		test
	}
	private Type questionType;
	private String correct;
	private List<Multimedia> content;
	private List<Answer> answers;
	private int index;
	
	private static final int BOXES = 5;

    private String string;
    private Integer integer;
    // BOX THREE IS EASIEST, DECREMENT BOX WHEN PLAYER GETS QUESTION RIGHT
    private Integer box = BOXES;
    
	Question(Type t, int box){
		this.questionType = t;
		this.answers=new ArrayList<Answer>();
		this.content=new ArrayList<Multimedia>();
		if (box < 1){
            this.box = 1;
        } else if (box > BOXES) {
            this.box = BOXES;
        } else {
            this.box = box;
        }
	}
	
	Question(Type t, int box, List<Answer> answers, List<Multimedia> content){
		this.questionType = t;
		this.answers=answers;
		this.content=content;
		if (box < 1){
            this.box = 1;
        } else if (box > BOXES) {
            this.box = BOXES;
        } else {
            this.box = box;
        }
	}
	
	public Type getQuestionType() {
		return questionType;
	}
	public void setQuestionType(Type questionType) {
		this.questionType = questionType;
	}
	
	public String getCorrect() {
		return correct;
	}
	public void setCorrect(String correct) {
		this.correct = correct;
	}

	public List<Multimedia> getContent() {
		return content;
	}
	
	public void setContent(List<Multimedia> content) {
		this.content = content;
	}

	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}
	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

    public Integer getBox() {
        return box;
    }

    public void incBox() {
        if (this.box > 1) { this.box--; };
    }

    public void resetBox(){
        this.box = BOXES;
    }
    
    /* todo: change this to fuzzy match */
    public boolean hasMatch(String answer) {
    	for(Answer a : answers) {
    		Multimedia answerContent = a.getMultimedia();
    		if(answerContent.getType().equals("text")) {
    			if(answerContent.getFilelocation().equals(answer)) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    public boolean checkAnswer(String answer) {
    	switch(questionType) {
    	case multiple:
    	case test:
    		if(answer.equals(correct)) {
    			return true;
    		}
    		break;
    	case fill:
    		if(hasMatch(answer)) {
    			return true;
    		}
    		break;
    	case anki:
    		if(answer.equals("easy")) {
    			return true;
    		}
    		break;
    	}
    	return false;
    }
	// https://stackoverflow.com/questions/33060592/getters-and-setters-for-arraylists-in-java


}
