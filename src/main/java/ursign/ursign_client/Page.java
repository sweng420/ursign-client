package ursign.ursign_client;

import java.util.List;
public class Page {
	private List<Multimedia> multimedias;

	public List<Multimedia> getMultimedias(){
		return multimedias;
	}
	
	
	//public void setMultimedias()
	public void setMultimedias(List<Multimedia> multimedias) {
		  if (multimedias == null) {
		    throw new NullPointerException("multimedias must not be null");
		  }
		  this.multimedias = multimedias;
		}
	// https://stackoverflow.com/questions/33060592/getters-and-setters-for-arraylists-in-java
}
