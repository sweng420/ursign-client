package quiz;

import java.util.ArrayList;
import java.util.List;

import ursign.ursign_client.Multimedia;

public class Answer {
	private List<Multimedia> multimedias;
	private String id;
	
	Answer(String id){
		this.id = id;
		multimedias = new ArrayList<Multimedia>();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public void setMultimedias(List<Multimedia> ms){
		this.multimedias = ms;
	}
	public void addMultimedia(Multimedia m) {
		this.multimedias.add(m);
	}
	public List<Multimedia> getMultimedias() {
		return multimedias;
	}

	
}
