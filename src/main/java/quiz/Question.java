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
	
	public boolean isMultiple() {
		return questionType == Type.multiple;
	}
	
	public boolean isAnki() {
		return questionType == Type.anki;
	}
	
	public boolean isFill() {
		return questionType == Type.fill;
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
    
    public boolean checkAnswer(String answer) {
    	System.out.println("checking "+answer+" against "+correct+" on type "+questionType);
    	switch(questionType) {
    	case multiple:
    	case test:
    		if(answer.equals(correct)) {
    			return true;
    		}
    		break;
    	case fill:
    		/* todo: change to fuzzy match */
    		System.out.println("alength="+answers.size());
    		for(Answer a : answers) {
    				/* take the first multimedia in the answer to extract
    				 * its 'Text' filed which should be the right answer
    				 */
        			Multimedia m = a.getMultimedias().get(0);
        			if(m.getFilelocation().toLowerCase().equals(answer.toLowerCase())) {
        				return true;
        			} else {
        				System.out.println(answer.toLowerCase() +"!="+m.getFilelocation().toLowerCase());
        			}
        		
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
