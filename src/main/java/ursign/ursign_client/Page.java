package ursign.ursign_client;

import java.util.ArrayList;
import java.util.List;
public class Page {
	private List<Multimedia> multimedias;

	Page(){
		this.multimedias=new ArrayList<Multimedia>();
	}
	
	public List<Multimedia> getMultimedias(){
		return multimedias;
	}
	
	
	public void addMultimedia(Multimedia multimedia) {
		multimedias.add(multimedia);
	}
	
	
	public void setMultimedias(List<Multimedia> multimedias) {
		  if (multimedias == null) {
		    throw new NullPointerException("multimedias must not be null");
		  }
		  this.multimedias = multimedias;
		}
	
	public String toString() {
		return multimedias.toString();
	}
	// https://stackoverflow.com/questions/33060592/getters-and-setters-for-arraylists-in-java
}
