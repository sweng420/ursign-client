package quiz;

import ursign.ursign_client.Multimedia;

public class Answer {
	
	Answer(String id){
		this.id = id;
	}
	

	private Multimedia multimedia;
	private String id;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Multimedia getMultimedia(){
		return multimedia;
	}
	public void setMultimedia(Multimedia multimedia) {
		this.multimedia = multimedia;
	}
	
}
