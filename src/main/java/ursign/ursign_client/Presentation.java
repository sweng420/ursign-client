package ursign.ursign_client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Presentation {
	
	Presentation(int id){
		this.id = id;
		this.pages=new ArrayList<Page>();
	}
	
	private List<Page> pages;
	private int id;

	public List<Page> getPages(){
		return pages;
	}
	
	public void setPages(List<Page> pages) {
		  if (pages == null) {
		    throw new NullPointerException("pages must not be null");
		  }
		  this.pages = pages;
		}
	
	public void addPage(Page page) {
		pages.add(page);
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	@Override 
	public String toString() {
		
		return id+pages.toString();
	}
	// https://stackoverflow.com/questions/33060592/getters-and-setters-for-arraylists-in-java
}
