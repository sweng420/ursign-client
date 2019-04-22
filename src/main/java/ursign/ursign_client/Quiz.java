package ursign.ursign_client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quiz {
	
	Quiz(int id){
		this.id = id;
		this.questions=new ArrayList<Question>();
	}
	
	private List<Question> questions;
	private int id;

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
	// https://stackoverflow.com/questions/33060592/getters-and-setters-for-arraylists-in-java
	// Quiz:Presentation::Question:Page
}
