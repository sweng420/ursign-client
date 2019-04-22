package ursign.ursign_client;

import java.util.ArrayList;
import java.util.List;
public class Question {
	enum Type{
		multiple,
		fill
	}
	private Type questionType;
	private String correct;
	private Content content;
	private ArrayList<Answer> answers;
	private int index;
	
	Question(){
		this.answers=new ArrayList<Answer>();
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

	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}

	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	
	// https://stackoverflow.com/questions/33060592/getters-and-setters-for-arraylists-in-java


}
