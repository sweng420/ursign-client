package ursign.ursign_client;

import java.util.List;

public class Presentation {
	
	private List<Page> pages;

	public List<Page> getPages(){
		return pages;
	}
	
	public void setPages(List<Page> pages) {
		  if (pages == null) {
		    throw new NullPointerException("pages must not be null");
		  }
		  this.pages = pages;
		}
	// https://stackoverflow.com/questions/33060592/getters-and-setters-for-arraylists-in-java
}
